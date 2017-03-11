import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Configuration loader.
 */
public class Config {

    private Config() {
        // only static access
    }

    private static final String CONFIG_FILEPATH = "config/application.yaml";

    private static final SparkConfig SPARK_CONFIG;
    private static final CassandraConfig CASSANDRA_CONFIG;

    static {

        ClassLoader loader = Config.class.getClassLoader();
        URL url = loader.getResource(CONFIG_FILEPATH);

        try {
            URI uri = url.toURI();
            ConfigFilesProvider configFilesProvider = () -> Arrays.asList(Paths.get(uri));
            ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);

            ConfigurationProvider configProvider = new ConfigurationProviderBuilder()
                    .withConfigurationSource(source)
                    .build();

            SPARK_CONFIG = configProvider.bind(SparkConfig.SPARK_CONFIG, SparkConfig.class);
            CASSANDRA_CONFIG = configProvider.bind(CassandraConfig.CASSANDRA_CONFIG, CassandraConfig.class);
        }
        catch (URISyntaxException e) {
            throw new RuntimeException("Couldn't find configuration file: " + CONFIG_FILEPATH);
        }

    }

    /**
     * @return spark configurations
     */
    public static SparkConfig spark() {
        return SPARK_CONFIG;
    }

    /**
     * @return cassandra configurations
     */
    public static CassandraConfig cassandra() {
        return CASSANDRA_CONFIG;
    }

    public interface SparkConfig {
        String SPARK_CONFIG = "spark";

        String appname();

        String master();

        String connectionHost();
    }

    public interface CassandraConfig {
        String CASSANDRA_CONFIG = "cassandra";

        String address();

        String keyspace();

        String gaiasource();
    }

}
