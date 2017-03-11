package gaiasource.dao;

import com.datastax.spark.connector.ColumnRef;
import com.datastax.spark.connector.rdd.reader.RowReader;
import scala.Option;
import scala.collection.Seq;

import java.io.Serializable;

public abstract class GenericRowReader<T> implements RowReader<T>, Serializable {

    @Override
    public Option<Seq<ColumnRef>> neededColumns() {
        return Option.empty();
    }

}
