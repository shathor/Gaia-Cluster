package gaiasource.dao;

import com.datastax.spark.connector.ColumnRef;
import com.datastax.spark.connector.cql.TableDef;
import com.datastax.spark.connector.writer.RowWriter;
import com.datastax.spark.connector.writer.RowWriterFactory;
import gaiasource.model.Solution;
import scala.collection.IndexedSeq;

import java.io.Serializable;

/**
 * Factory for the {@link SolutionRowWriter}, used to insert values taken from {@link Solution}.
 */
public class SolutionRowWriterFactory implements RowWriterFactory<Solution>, Serializable {

    private static final long serialVersionUID = 3647052599872717310L;

    private static final SolutionRowWriter solutionRowWriter = new SolutionRowWriter();

    @Override
    public RowWriter<Solution> rowWriter(TableDef tableDef, IndexedSeq<ColumnRef> indexedSeq) {
        return solutionRowWriter;
    }

}
