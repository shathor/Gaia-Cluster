package gaiasource.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The Galaxy Bayesian Prior, where it is denoted {@code σϖ,F90} (for the parallax) and {@code σμ,F90=ℛσϖ,F90, with ℛ=10
 * yr-1} (for proper motion). The Galaxy Bayesian Prior relaxed by a factor 10 is {@code 10σϖ,F90} and {@code 10σμ,F90],
 * respectively.
 * <p>
 * For Gaia DR1 the only types of priors used are 2 (for the secondary data set), 3 (for the Hipparcos subset of the
 * primary data set), or 5 (for the non-Hipparcos subset of the primary data set). Type 6 was used for internal
 * calibration purposes and alignment of the reference frame, but the corresponding astrometric results are in general
 * not published.
 */
public enum PriorType implements Serializable {

    /**
     * No prior used.
     */
    NO_PRIOR(0),

    /**
     * Galaxy Bayesian Prior for parallax and proper motion.
     */
    GALAXY_BAYESIAN_PA_PM(1),

    /*
     * Galaxy Bayesian Prior for parallax and proper motion relaxed by factor 10.
     */
    GALAXY_BAYESIAN_PA_PM_X_10(2),

    /*
     * Hipparcos prior for position.
     */
    HIPPARCOS_POS(3),

    /*
     * Hipparcos prior for position and proper motion.
     */
    HIPPARCOS_POS_PM(4),

    /*
     * Tycho2 prior for position.
     */
    TYCHO2_POS(5),

    /*
     * Quasar prior for proper motion.
     */
    QUASAR_PM(6);

    private final int code;

    /**
     * The prior type.
     *
     * @param code the code of the prior type
     */
    PriorType(int code) {
        this.code = code;
    }

    /**
     * @return the code of this prior type
     */
    public int getCode() {
        return code;
    }

    /**
     * Prior type by given code.
     *
     * @param code the code to get prior type for
     * @return prior type by given code or {@code null} if code doesn't match any prior type
     */
    public static PriorType getByCode(int code) {
        return Arrays.stream(PriorType.values())
                     .filter(priorType -> priorType.getCode() == code)
                     .findFirst()
                     .orElse(null);
    }

}
