package gaiasource.dao;

import com.datastax.spark.connector.writer.RowWriter;
import gaiasource.model.Solution;
import scala.collection.Seq;

import java.util.Arrays;
import java.util.List;

/**
 * The solution reader to insert values taken from {@link Solution}.
 */
public class SolutionRowWriter implements RowWriter<Solution> {

    @Override
    public Seq<String> columnNames() {
        List<String> columnNames = Arrays.asList(Field.columnNames());
        return scala.collection.JavaConversions.asScalaBuffer(columnNames).toList();
    }

    @Override
    public void readColumnValues(Solution solution, Object[] buffer) {
        Field<?>[] fieldArray = Field.values();
        for(int i = 0; i < fieldArray.length; i++) {
            Field<?> field = fieldArray[i];
            buffer[i] = field.get(solution);
        }

    }
}
