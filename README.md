# Gaia-Cluster

## Description

Provides a scaffold to build and develop a cluster to query the data from ESA's Gaia satellite. The cluster uses open-source technology: Apache Spark as the data process engine
and Apache Cassandra as database.

## Gaia

Gaia is an ambitious mission to chart a three-dimensional map of our Galaxy, the Milky Way, in the process revealing the composition, formation and evolution of the Galaxy.
Gaia will provide unprecedented positional and radial velocity measurements with the accuracies needed to produce a stereoscopic and kinematic census of about one billion stars
in our Galaxy and throughout the Local Group. This amounts to about 1 per cent of the Galactic stellar population.
[[Source]](http://sci.esa.int/gaia/)

### Setup

This setup focuses on a single node test cluster for your local machine. Using:

* [Maven](https://maven.apache.org/) - Project and dependency management, build tool
* [Apache Cassandra](http://cassandra.apache.org/) - A disributed, scalable NoSQL database
..* For Windows user I recommend (Datastax)[https://www.datastax.com/2012/01/getting-started-with-apache-cassandra-on-windows-the-easy-way]
* [Apache Spark](http://spark.apache.org/) - A fast and general engine for large-scale data processing. Used as dependency, no need for installation.

### Data Import
ESA released the first catalogue (Gaia DR 1)[https://www.cosmos.esa.int/web/gaia/release] the 14 September 2016.
The data is available at their (archive page)[https://gea.esac.esa.int/archive/] in form of (CSV)[http://cdn.gea.esac.esa.int/Gaia/gaia_source/csv/] amongst other.

The amount of data is huge. Around  1'142'727'643 rows, 57 columns each.

To faciliate the data import into Cassandra, there is helper class: [DataImport](../blob/master/src/main/java/DataImport.java). Following steps are executed for all 5231 CSV files until finished:

1. Download the next compressed CSV source file from Gaia archive
2. Decompress the the CSV source file
3. Delete compressed CSV source file (no longer needed)
4. Parse all rows of the CSV source file into memory
5. Delete decompressed CSV source file (no longer needed)
6. Insert the rows into the Cassandra database

The whole data import will take a day, but can be stopped and restarted.

**Important**: The data importer sets up the Cassandra keyspace with SimpleStrategy replication and replacation factor 1. Primary keys are 'solution_id' and 'source_id'.

Given the program argument "-restart true", any previously imported data will be dropped and the data import is started anew. In case the restart argument is set to false or no argument is given,
the data import is checking the "imported.csv" file for already imported data source files and excludes those. This mode allows the data import to be  interrupted, stopped and started again and
provides some fault tolerance.
 
Note that no checks are made for completeness or data integrity.

For testing you can also setup the Cassandra database with some test data by using the Cassandra CQL Shell. An example script can be found in the [data folder](../blob/master/src/main/resources/data/cassandra_init.txt).

*** Configuration

The cluster connections are configured in the [application.yaml](../blob/master/src/main/resources/config/application.yaml). It is both relevant for the cluster and the data importer:

``` yaml
spark:
  appname: GaiaCluster
  master: local[4]
  connectionHost: spark.cassandra.connection.host

cassandra:
  address: 127.0.0.1
  keyspace: gaia_cluster
  gaiasource: gaia_source
 ```

*** Executing queries
The [GaiaClusterDemo](../blob/master/src/main/java/GaiaClusterDemo.java) shows how to start a Apache Spark context and some examples of querying the data.

* Starting the Apache Spark context:

``` java
SparkConf sparkConf = new SparkConf();
sparkConf.setAppName(Config.spark().appname());

sparkConf.setMaster(Config.spark().master());
sparkConf.set(Config.spark().connectionHost(), Config.cassandra().address());
try(JavaSparkContext sc = new JavaSparkContext(conf)) {
    // write queries here using "sc"
}
// after try-with-resources: Apache Spark is stopped
		
```

* Count all data sets:
``` java
private long selectCount(JavaSparkContext sc) {
	String keyspace = Config.cassandra().keyspace();
	String table = Config.cassandra().gaiasource();
	return javaFunctions(sc)
			.cassandraTable(keyspace, table, new SolutionRowReaderFactory(Field.SOLUTION_ID)) // using one of the primary key's.
			.count();
}
```

* Specify the fields you want to get data from and/or are involved in the query. Restricting fields to a subset will speedup queries:
``` java
List<Field<?>> fields = Arrays.asList(Field.REF_EPOCH, Field.MATCHED_OBSERVATIONS, Field.L, Field.B);
```

* In case you need all fields of a solution (one complete row):
``` java
List<Field<?>> fields = Fields.values();
```


* Example generic select of fields with a filter function
``` java
private List<Solution> select(JavaSparkContext sc, List<Field<?>> fields, Function<Solution, Boolean> filterFunction) {
	String keyspace = Config.cassandra().keyspace();
	String table = Config.cassandra().gaiasource();
	JavaRDD<Solution> cassandraRdd = javaFunctions(sc)
			.cassandraTable(keyspace, table, new SolutionRowReaderFactory(fields))
			.select(Field.columnNames(fields))
			.filter(filterFunction);
	return cassandraRdd.collect();
}
```

* Example of calling the above `select` method to select all solutions (discouraged on the complete dataset: Would be slow or lead to out-of-memory exception):
``` java
private List<Solution> selectAll(JavaSparkContext sc) {
	return select(sc, Arrays.asList(Field.values()), (Solution solution) -> true);
}
```

* Example of calling the above `select` method to select solutions that were observed 42 times (never tested on full dataset, might also lead to out-of-memory exception):
``` java
private List<Solution> selectMatchedObservations42(JavaSparkContext sc, List<Field<?>> fields) {
	Function<Solution, Boolean> filterFunction = (Solution solution) -> Objects.equals(solution.getMatchedObservations(), 42);
	return select(sc, fields, filterFunction);
}

Note that the JavaRDD class by Apache Spark allows all kinds of map-reduce functionality beyond select or filter.

* Getting the desired values from returned solutions, there are two ways (Logging may only work on local machine):
``` java
//  calling getters on solution
for(Solution solution : solutions) {
    LOG.info("Galactic Latitude: " + solution.getGalacticLatitude());
}

// calling get on the desired Field and passing the solution

for(Solution solution : solutions) {
    LOG.info("Galactic Latitude: " + Field.B.get(solution)); // Field.B is the galactic latitude field
}
```

Note that the getters will return null, if they were not selected explicitely in the query (for performance reasons).

* Inserting solutions into Cassandra (for intermediate results and to persist results in a distributed cluster context):
``` java
private static void insertSolution(JavaSparkContext sc, List<Solution> solutions) {
	JavaRDD<Solution> rdd = sc.parallelize(solutions);

	SolutionRowWriterFactory factory = new SolutionRowWriterFactory();

	String keyspace = Config.cassandra().keyspace(); // maybe use a different keyspace
	String table = Config.cassandra().gaiasource(); // use a different table !!

	CassandraJavaUtil.javaFunctions(rdd).writerBuilder(keyspace, table, factory).saveToCassandra();
}
```













