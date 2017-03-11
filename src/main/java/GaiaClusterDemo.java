import com.datastax.spark.connector.japi.CassandraJavaUtil;
import gaiasource.dao.Field;
import gaiasource.dao.SolutionRowReaderFactory;
import gaiasource.dao.SolutionRowWriterFactory;
import gaiasource.model.Solution;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;

/**
 * GaiaClusterDemo: Examples of how to start Apache Spark and using it to query Cassandra.
 */
public class GaiaClusterDemo implements Serializable {

    private final static Logger LOG = Logger.getLogger(GaiaClusterDemo.class);

    private transient SparkConf conf;

    private GaiaClusterDemo(SparkConf conf) {
        this.conf = conf;
    }

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName(Config.spark().appname());

        sparkConf.setMaster(Config.spark().master());
        sparkConf.set(Config.spark().connectionHost(), Config.cassandra().address());

        GaiaClusterDemo gaiaCluster = new GaiaClusterDemo(sparkConf);
        gaiaCluster.run();
    }

    private void run() {
        try(JavaSparkContext sc = new JavaSparkContext(conf)) {
            compute(sc);
        }
    }

    private void compute(JavaSparkContext sc) {

        // only Fields that are specified are extracted, get all Fields with Field.values()
        List<Field<?>> fields = Arrays.asList(Field.REF_EPOCH, Field.MATCHED_OBSERVATIONS, Field.L, Field.B);

        Function<Solution, Boolean> filterFunction = (Solution solution) -> Objects.equals(solution.getMatchedObservations(), 42);
        List<Solution> solutions = select(sc, fields, filterFunction);

        printResult(solutions, fields);

        LOG.info("Count found: " + solutions.size());

        // showing both ways how to access information of a solution
        if (!solutions.isEmpty()) {
            LOG.info("#1: B: " + solutions.get(0).getGalacticLatitude());
            LOG.info("#2: B: " + Field.B.get(solutions.get(1)));
        }

    }

    // example select of fields with a filter function
    private List<Solution> select(JavaSparkContext sc, List<Field<?>> fields, Function<Solution, Boolean> filterFunction) {
        String keyspace = Config.cassandra().keyspace();
        String table = Config.cassandra().gaiasource();
        JavaRDD<Solution> cassandraRdd = javaFunctions(sc)
                .cassandraTable(keyspace, table, new SolutionRowReaderFactory(fields))
                .select(Field.columnNames(fields))
                .filter(filterFunction);
        return cassandraRdd.collect();
    }

    private long selectCount(JavaSparkContext sc) {
        String keyspace = Config.cassandra().keyspace();
        String table = Config.cassandra().gaiasource();
        return javaFunctions(sc)
                .cassandraTable(keyspace, table, new SolutionRowReaderFactory(Field.SOLUTION_ID))
                .count();
    }

    private List<Solution> selectAll(JavaSparkContext sc) {
        return select(sc, Arrays.asList(Field.values()), (Solution solution) -> true);
    }

    private void printResult(List<Solution> solutions, Field<?>... fields) {
        printResult(solutions, Arrays.asList(fields));
    }

    private void printResult(List<Solution> solutions, List<Field<?>> fields) {
        for (Solution solution : solutions) {
            List<String> row = new ArrayList<>();
            for (Field<?> field : fields) {
                row.add(String.format("%1s: %2s", field.getColumnName(), field.get(solution)));
            }
            LOG.info(String.join(", ", row));
        }
    }

    private static void insertSolution(JavaSparkContext sc, List<Solution> solutions) {
        JavaRDD<Solution> rdd = sc.parallelize(solutions);

        SolutionRowWriterFactory factory = new SolutionRowWriterFactory();

        String keyspace = Config.cassandra().keyspace();
        String table = Config.cassandra().gaiasource();

        CassandraJavaUtil.javaFunctions(rdd).writerBuilder(keyspace, table, factory).saveToCassandra();
    }

}
