import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import gaiasource.dao.Field;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;

import static java.util.stream.Collectors.toList;

/**
 * Data Importer for Gaia Source into Cassandra database. Following phases are executed until finished: <ul>
 * <li>Download the next compressed CSV source file from Gaia archive</li> <li>Decompress the the CSV source file</li>
 * <li>Delete compressed CSV source file (no longer needed)</li> <li>Parse all rows of the CSV source file into
 * memory</li> <li>Delete decompressed CSV source file (no longer needed)</li> <li>Insert the rows into the Cassandra
 * database</li> </ul>
 * <p>
 * Given the program argument "-restart true", any previously imported data will be dropped and the data import is
 * started anew. In case the restart argument is set to false or no argument is given, the data import is checking the
 * "imported.csv" file for already imported data source files and excludes those. This mode allows the data import to be
 * interrupted, stopped and started again and provides some fault tolerance.
 * <p>
 * Note that no checks are made for completeness or data integrity.
 */
public class DataImport {

    private final static Logger LOG = Logger.getLogger(DataImport.class);

    private static final String GAIA_SOURCE_CSV_URL = "http://cdn.gea.esac.esa.int/gdr1/gaia_source/csv";
    private static final String FILE_PREFIX = "GaiaSource_";
    private static final String FILE_ENDING = ".csv.gz";
    private static final String DATA_DIRECTORY = "src/main/resources/data";
    private static final String IMPORTED_CSV_FILENAME = "imported.csv";

    public static void main(String[] args) {
        new DataImport().run(args);
    }

    public void run(String[] args) {

        boolean restart = getRestartArgument(args, false);

        String address = Config.cassandra().address();
        String keyspace = Config.cassandra().keyspace();
        String table = Config.cassandra().gaiasource();

        Cluster cluster = Cluster.builder().addContactPoint(address).build();
        Session session = prepareKeyspace(cluster, keyspace, table, restart);

        try {
            checkDataDirectory();

            List<String> importedFileLinks = new ArrayList<>();
            if (!restart) {
                importedFileLinks = parseForImportedFileLinks();
            }

            LOG.info("Started parsing archive page for download links");
            List<String> fileLinks = parseForFileLinks();
            LOG.info("Finished parsing archive page for download links. Found: " + fileLinks.size());

            fileLinks.removeAll(importedFileLinks);
            if (!restart) {
                LOG.info("Already imported: " + importedFileLinks.size() + ", left to import: " + fileLinks.size());
            }

            for (String fileNameGZ : fileLinks) {

                LOG.info("Started downloading file: " + fileNameGZ);
                File fileGZ = download(fileNameGZ);
                LOG.info("Finished downloading file: " + fileNameGZ);

                LOG.info("Started decompressing file: " + fileNameGZ);
                File fileCSV = unzip(fileGZ);
                LOG.info("Finished decompressing file: " + fileNameGZ + " to file: " + fileCSV.getName());

                FileUtils.forceDelete(fileGZ);
                LOG.info("Deleted file: " + fileGZ.getName());

                LOG.info("Started parsing CSV file: " + fileCSV.getName());
                List<String[]> rowsCSV = parseCSV(fileCSV, true);
                LOG.info("Finished parsing CSV file: " + fileCSV.getName() + " Found rows: " + rowsCSV.size());

                FileUtils.forceDelete(fileCSV);
                LOG.info("Deleted file: " + fileCSV.getName());

                LOG.info("Started inserting into Cassandra");
                rowsCSVToCassandra(session, keyspace, table, rowsCSV);
                LOG.info("Finished inserting into Cassandra");

                if (!restart) {
                    persistImportedFileLinks(fileNameGZ);
                }

            }

        }
        catch (URISyntaxException | IOException e) {
            LOG.error("Exception during data import", e);
        }
        finally {
            session.close();
            cluster.close();
        }
    }

    private static boolean getRestartArgument(String[] args, boolean defaultRestart) {
        if (args != null && args.length >= 2 && args[0].equals("-restart")
            && (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false"))) {
            return Boolean.valueOf(args[1]);
        }
        return defaultRestart;
    }

    private static Session prepareKeyspace(Cluster cluster, String keyspace, String table, boolean recreateKeyspace) {
        Session session = cluster.newSession();
        if (recreateKeyspace) {
            recreateKeyspace(session, keyspace);
            session = useKeyspace(cluster, keyspace);
            createTable(session, table);
        }
        useKeyspace(cluster, keyspace);
        return session;
    }

    private static void recreateKeyspace(Session session, String keyspace) {
        String dropKeyspaceStatement = "DROP KEYSPACE IF EXISTS " + keyspace + ";";
        session.execute(dropKeyspaceStatement);
        LOG.info("Executed: " + dropKeyspaceStatement);
        String createKeyspaceStatement = "CREATE KEYSPACE " + keyspace + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };";
        session.execute(createKeyspaceStatement);
        LOG.info("Executed: " + createKeyspaceStatement);
    }

    private static Session useKeyspace(Cluster cluster, String keyspace) {
        return cluster.connect(keyspace);
    }

    private static void createTable(Session session, String table) {
        String[] tableColumns = Arrays.stream(Field.values())
                                      .map(field -> field.getColumnName() + " " + getCassandraDataType(field))
                                      .toArray(String[]::new);
        String tableColumnsString = String.join(", ", tableColumns);
        String primaryKey1 = Field.SOLUTION_ID.getColumnName();
        String primaryKey2 = Field.SOURCE_ID.getColumnName();
        String createTableStatement = "CREATE TABLE " + table + " (" + tableColumnsString + ", PRIMARY KEY (" + primaryKey1 + ", " + primaryKey2 + "));";
        session.execute(createTableStatement);
        LOG.info("Executed: " + createTableStatement);
    }

    private static String getCassandraDataType(Field<?> field) {
        Class<?> dataType = field.getDataType();
        if (dataType.equals(Integer.class)) {
            return "int";
        }
        else if (dataType.equals(Long.class)) {
            return "bigint";
        }
        else if (dataType.equals(Double.class)) {
            return "double";
        }
        else if (dataType.equals(Float.class)) {
            return "float";
        }
        else if (dataType.equals(Boolean.class)) {
            return "boolean";
        }
        else if (dataType.equals(String.class)) {
            return "varchar";
        }

        throw new RuntimeException("Unknown Cassandra datatype: " + dataType);
    }

    private static void checkDataDirectory() throws IOException, URISyntaxException {
        File directory = getDataDirectory();
        if (!directory.exists() && !directory.isDirectory()) {
            FileUtils.forceMkdir(directory);
        }
    }

    private static List<String> parseForImportedFileLinks() throws IOException {
        File directory = getDataDirectory();
        File importedFile = new File(directory.getAbsolutePath() + "/" + IMPORTED_CSV_FILENAME);
        if (!importedFile.exists() || !importedFile.isFile()) {
            importedFile.createNewFile();
        }
        List<String[]> importedRows = parseCSV(importedFile, false);
        return importedRows.stream()
                           .map(importedRow -> importedRow[0])
                           .collect(toList());
    }

    private static List<String> parseForFileLinks() throws IOException {
        Document doc = Jsoup.connect(GAIA_SOURCE_CSV_URL).get();
        Elements links = doc.getElementsByTag("a");
        return links.stream()
                    .map(Element::text)
                    .filter(text -> StringUtils.startsWith(text, FILE_PREFIX) && StringUtils.endsWith(text, FILE_ENDING))
                    .sorted()
                    .collect(toList());
    }

    private static File download(String fileName) throws IOException {
        URL url = new URL(GAIA_SOURCE_CSV_URL + "/" + fileName);
        File file = new File(DATA_DIRECTORY + "/" + fileName);
        FileUtils.copyURLToFile(url, file);
        return file;
    }

    private static File unzip(File fileGZ) throws IOException {
        String fileNameCSV = StringUtils.split(fileGZ.getName(), ".")[0] + ".csv";
        File outputFileCSV = new File(getDataDirectory().getAbsolutePath() + "/" + fileNameCSV);

        byte[] buffer = new byte[1024];
        GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(fileGZ));
        FileOutputStream out = new FileOutputStream(outputFileCSV);

        int len;
        while ((len = gzis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }

        gzis.close();
        out.close();
        return outputFileCSV;
    }

    private static File getDataDirectory() throws IOException {
        File root = new File(".");
        return new File(root.getCanonicalPath() + "/" + DATA_DIRECTORY);
    }

    private static List<String[]> parseCSV(File fileCSV, boolean extractHeader) {
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setLineSeparatorDetectionEnabled(true);
        RowListProcessor rowProcessor = new RowListProcessor();
        parserSettings.setProcessor(rowProcessor);
        parserSettings.setHeaderExtractionEnabled(extractHeader);
        CsvParser parser = new CsvParser(parserSettings);
        parser.parse(fileCSV);
        return rowProcessor.getRows();
    }

    private static void rowsCSVToCassandra(Session session, String keyspace, String table, List<String[]> rowsCSV) {
        Field<?>[] fieldArray = Field.values();
        String[] columnNames = Field.columnNames();
        String columnNamesString = String.join(", ", columnNames);
        String[] namedSetters = Arrays.stream(columnNames)
                                      .map(name -> ":" + name)
                                      .toArray(String[]::new);
        String namedSettersString = String.join(", ", namedSetters);

        PreparedStatement prepared = session.prepare("INSERT INTO " + keyspace + "." + table + " (" + columnNamesString + ")"
                                                     + " VALUES (" + namedSettersString + ")");

        for (int rowCount = 0; rowCount < rowsCSV.size(); rowCount++) {
            String[] rowCSV = rowsCSV.get(rowCount);
            BoundStatement bound = prepared.bind();
            for (int fieldCount = 0; fieldCount < fieldArray.length; fieldCount++) {
                Field<?> field = fieldArray[fieldCount];
                String rawValue = rowCSV[fieldCount];
                bound = setBoundValue(bound, field, rawValue);
            }
            session.executeAsync(bound);
        }
    }

    private static BoundStatement setBoundValue(BoundStatement bound, Field<?> field, String rawValue) {
        if (rawValue == null) {
            return bound;
        }

        Class<?> dataType = field.getDataType();
        if (dataType.equals(Integer.class)) {
            return bound.setInt(field.getColumnName(), Integer.parseInt(rawValue));
        }
        else if (dataType.equals(Long.class)) {
            return bound.setLong(field.getColumnName(), Long.parseLong(rawValue));
        }
        else if (dataType.equals(Double.class)) {
            return bound.setDouble(field.getColumnName(), Double.parseDouble(rawValue));
        }
        else if (dataType.equals(Float.class)) {
            return bound.setFloat(field.getColumnName(), Float.parseFloat(rawValue));
        }
        else if (dataType.equals(Boolean.class)) {
            return bound.setBool(field.getColumnName(), Boolean.parseBoolean(rawValue));
        }
        else if (dataType.equals(String.class)) {
            return bound.setString(field.getColumnName(), rawValue);
        }

        throw new RuntimeException("Unknown Cassandra datatype: " + dataType);
    }

    private static void persistImportedFileLinks(String importedFileLink) throws IOException {
        List<String> importedFileLinks = parseForImportedFileLinks();
        importedFileLinks.add(importedFileLink);
        List<Object[]> rows = importedFileLinks.stream()
                                               .map(link -> new String[]{link})
                                               .collect(toList());
        File directory = getDataDirectory();
        File importedFile = new File(directory.getAbsolutePath() + "/" + IMPORTED_CSV_FILENAME);
        if (!importedFile.exists() || !importedFile.isFile()) {
            importedFile.createNewFile();
        }
        FileOutputStream csvResult = new FileOutputStream(importedFile);
        Writer outputWriter = new OutputStreamWriter(csvResult);
        CsvWriter writer = new CsvWriter(outputWriter, new CsvWriterSettings());
        writer.writeRowsAndClose(rows);
    }

}
