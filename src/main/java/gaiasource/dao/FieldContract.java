package gaiasource.dao;

import gaiasource.model.Solution;

import javax.validation.constraints.NotNull;

/**
 * The Field contract.
 */
public abstract class FieldContract<T> {

    /**
     * @return the DB column name of the field
     */
    public abstract String getColumnName();

    /**
     * @return the data type to map values to
     */
    public abstract Class<T> getDataType();

    /**
     * Sets a value of this column on a given {@link Solution}.
     *
     * @param solution the {@link Solution}
     * @param value    the value to set
     */
    public abstract void set(@NotNull Solution solution, T value);

    /**
     * Gets a value of this column from a given {@link Solution}.
     *
     * @param solution the {@link Solution}
     * @return the value
     */
    public abstract T get(@NotNull Solution solution);

    /**
     * See {@link FieldContract#getColumnName}
     *
     * @return a String representation of {@link FieldContract}
     */
    @Override
    public String toString() {
        return getColumnName();
    }

}
