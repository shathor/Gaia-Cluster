package gaiasource.dao;

import com.datastax.driver.core.Row;
import com.datastax.spark.connector.CassandraRowMetadata;
import gaiasource.model.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * The solution reader to extracted values from raw row data and map them on a {@link Solution}.
 */
public class SolutionRowReader extends GenericRowReader<Solution> {

    private static final long serialVersionUID = -6716863198707941120L;

    private final Collection<Field<?>> fields = new ArrayList<>();

    /**
     * Initializes a new solution reader. Only the values from the given fields are read from the raw row data and then
     * set on a new {@link Solution}.
     *
     * @param fields the fields that are extracted from raw data
     */
    public SolutionRowReader(Field<?>... fields) {
        this.fields.addAll(Arrays.asList(fields));
    }

    /**
     * Initializes a new solution reader. Only the values from the given fields are read from the raw row data and then
     * set on a new solution.
     *
     * @param fields the fields that are extracted from raw data
     */
    public SolutionRowReader(Collection<Field<?>> fields) {
        this.fields.addAll(fields);
    }

    @Override
    public Solution read(Row row, CassandraRowMetadata cassandraRowMetadata) {
        Solution solution = new Solution();
        fields.forEach(field -> read(row, solution, field));
        return solution;
    }

    /**
     * Extracts a value of a single field from the raw data row and sets it on the solution.
     *
     * @param <T>      data type of the field
     * @param row      the raw data {@link Row}
     * @param solution the {@link Solution} to set the extracted value on
     * @param field    the {@link Field} to extract
     */
    public static <T> void read(Row row, Solution solution, Field<T> field) {
        field.set(solution, row.get(field.getColumnName(), field.getDataType()));
    }

}
