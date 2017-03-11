package gaiasource.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Indicating if variability was identified in the photometric {@code G}-band.
 */
public enum PhotometricVariability implements Serializable {

    /**
     * Source not processed and/or exported to catalogue.
     */
    NOT_AVAILABLE("NOT_AVAILABLE"),

    /**
     * Source not identified as variable.
     */
    CONSTANT("CONSTANT"),

    /**
     * Source identified and processed as variable, see tables:
     * {@code phot_variable_summary},
     * {@code phot_variable_time_series_gfov},
     * {@code phot_variable_time_series_gfov_statistical_parameters},
     * and {@code cepheid} or {@code rrlyrae} for more details.
     */
    VARIABLE("VARIABLE");

    private String name;

    /**
     * Indicating if variability was identified in the photometric {@code G}-band.
     */
    PhotometricVariability(String name) {
        this.name = name;
    }

    /**
     * @return the name of this photometric variability
     */
    public String getName() {
        return name;
    }

    /**
     * Photometric variability by given name.
     *
     * @param name the name to get photometric variability for (ignores case)
     * @return Photometric variability by given name or {@code null} if name doesn't match any photometric variability
     */
    public static PhotometricVariability getByName(String name) {
        return Arrays.stream(PhotometricVariability.values())
                     .filter(priorType -> priorType.getName().equalsIgnoreCase(name))
                     .findFirst()
                     .orElse(null);
    }


}
