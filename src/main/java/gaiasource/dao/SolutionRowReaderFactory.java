package gaiasource.dao;

import com.datastax.spark.connector.ColumnRef;
import com.datastax.spark.connector.cql.TableDef;
import com.datastax.spark.connector.rdd.reader.RowReader;
import com.datastax.spark.connector.rdd.reader.RowReaderFactory;
import gaiasource.model.Solution;
import scala.collection.IndexedSeq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Factory for the {@link SolutionRowReader}, used to extracted values from raw row data and map them on a
 * {@link Solution}.
 */
public class SolutionRowReaderFactory implements RowReaderFactory<Solution>, Serializable {

    private static final long serialVersionUID = -3008458923567348769L;

    private final Collection<Field<?>> fields = new ArrayList<>();

    /**
     * Initializes a new solution reader factory. Only the values from the given fields are read from the raw row data
     * and then set on a new {@link Solution}.
     *
     * @param fields the fields that are extracted from raw data
     */
    public SolutionRowReaderFactory(Field<?>... fields) {
        this.fields.addAll(Arrays.asList(fields));
    }

    /**
     * Initializes a new solution reader factory. Only the values from the given fields are read from the raw row data
     * and then set on a new {@link Solution}.
     *
     * @param fields the fields that are extracted from raw data
     */
    public SolutionRowReaderFactory(Collection<Field<?>> fields) {
        this.fields.addAll(fields);
    }

    @Override
    public RowReader<Solution> rowReader(TableDef tableDef, IndexedSeq<ColumnRef> columnRef) {
        Field[] fieldArray = new Field[fields.size()];
        return new SolutionRowReader(fields.toArray(fieldArray));
    }

    @Override
    public Class<Solution> targetClass() {
        return Solution.class;
    }

}
