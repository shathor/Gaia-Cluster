package gaiasource.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Astrometric data
 */
@Data
public class Astrometric implements Serializable {

    /**
     * Total number of observations AL (int)
     * <p>
     * Total number of AL observations (= CCD transits) used in the astrometric solution of the source, independent of
     * their weight. Note that some observations may be strongly downweighted. See {@link Astrometric#numberBadObsAL}.
     */
    private Integer numberObsAL;

    /**
     * Total number of observations AC (int)
     * <p>
     * Total number of AC observations (= CCD transits) used in the astrometric solution of the source, independent of
     * their weight. Note that some observations may be strongly downweighted. See {@link Astrometric#numberBadObsAC}.
     * Nearly all sources having {@code G} < 13 will have AC observations from 2d windows, while fainter than that limit
     * only ∼1% of stars (the so–called "calibration faint stars") are assigned 2d windows resulting in AC
     * observations.
     */
    private Integer numberObsAC;

    /**
     * Number of good observations AL (int)
     * <p>
     * Number of AL observations (= CCD transits) that were not strongly downweighted in the astrometric solution of the
     * source. Strongly downweighted observations (with downweighting factor {@code w} < 0.2) are instead counted in
     * {@link Astrometric#numberBadObsAL}. The sum of {@link Astrometric#numberGoodObsAL} and {@link
     * Astrometric#numberBadObsAL} equals {@link Astrometric#numberObsAL}, the total number of AL observations used in
     * the astrometric solution of the source.
     */
    private Integer numberGoodObsAL;

    /**
     * Number of good observations AC (int)
     * <p>
     * Number of AC observations (= CCD transits) that were not strongly downweighted in the astrometric solution of the
     * source. Strongly downweighted observations (with downweighting factor {{@code w} < 0.2) are instead counted in
     * {@link Astrometric#numberBadObsAC}. The sum of {@link Astrometric#numberGoodObsAC} and {@link
     * Astrometric#numberBadObsAC} equals {@link Astrometric#numberObsAC}, the total number of AC observations used in
     * the astrometric solution of the source.
     */
    private Integer numberGoodObsAC;

    /**
     * Number of bad observations AL (int)
     * <p>
     * Number of AL observations (= CCD transits) that were strongly downweighted in the astrometric solution of the
     * source, and therefore contributed little to the determination of the astrometric parameters. An observation is
     * considered to be strongly downweighted if its downweighting factor {@code w} < 0.2, which means that the absolute
     * value of the astrometric residual exceeds 4.83 times the total uncertainty of the observation, calculated as the
     * quadratic sum of the centroiding uncertainty, excess source noise, and excess attitude noise.
     */
    private Integer numberBadObsAL;

    /**
     * Number of bad observations AC (int)
     * <p>
     * Number of AC observations (= CCD transits) that were strongly downweighted in the astrometric solution of the
     * source, and therefore contributed little to the determination of the astrometric parameters. An observation is
     * considered to be strongly downweighted if its downweighting factor {@code w} < 0.2, which means that the absolute
     * value of the astrometric residual exceeds 4.83 times the total uncertainty of the observation, calculated as the
     * quadratic sum of the centroiding uncertainty, excess source noise, and excess attitude noise.
     */
    private Integer numberBadObsAC;

    /**
     * Hipparcos/Gaia data discrepancy (Hipparcos subset of TGAS only) (float)
     * <p>
     * In the TGAS solution astrometric_delta_q ({@code ΔQ}) indicates the discrepancy between the Hipparcos proper
     * motion and the TGAS proper motion. A large value of {@code ΔQ} could indicate non-linear motion (e.g. in a
     * binary).
     */
    private Float deltaQ;

    /**
     * Excess noise of the source (double, Angle[mas])
     * <p>
     * This is the excess noise {@code ϵi} of the source. It measures the disagreement, expressed as an angle, between
     * the observations of a source and the best-fitting standard astrometric model (using five astrometric parameters).
     * The assumed observational noise in each observation is quadratically increased by ϵi in order to statistically
     * match the residuals in the astrometric solution. A value of 0 signifies that the source is astrometrically
     * well-behaved, i.e. that the residuals of the fit statistically agree with the assumed observational noise. A
     * positive value signifies that the residuals are statistically larger than expected.
     * <p>
     * The significance of {@code ϵi} is given by {@link Astrometric#excessNoiseSignificance} ({@code D}). If {@code D}
     * ≤ 2 then {@code ϵi} is probably not significant, and the source may be astrometrically well-behaved even if
     * {@code ϵi} is large.
     */
    private Double excessNoise;

    /**
     * Significance of excess noise (double)
     * <p>
     * A dimensionless measure ({@code D}) of the significance of the calculated {@link Astrometric#excessNoise} ({@code
     * ϵi}). A value {@code D} ≤ 2 indicates that the given {@code ϵi} is probably significant.
     * <p>
     * For good fits in the limit of a large number of observations, {@code D} should be zero in half of the cases and
     * approximately follow the positive half of a normal distribution with zero mean and unit standard deviation for
     * the other half. Consequently, {@code D} is expected to be greater than 2 for only a few percent of the sources
     * with well-behaved astrometric solutions.
     * <p>
     * In the early data releases ϵi will however include instrument and attitude modelling errors that are
     * statistically significant and could result in large values of {@code ϵi} and {@code D}. The user must study the
     * empirical distributions of these statistics and make sensible cutoffs before filtering out sources for their
     * particular application.
     */
    private Double excessNoiseSignificance;

    /**
     * Primary or seconday (boolean)
     * <p>
     * Flag indicating if this source was used as a primary source ({@code true}) or secondary source ({@code false}).
     * Only primary sources contribute to the estimation of attitude, calibration, and global parameters. The estimation
     * of source parameters is otherwise done in exactly the same way for primary and secondary sources.
     */
    private Boolean primaryFlag;

    /**
     * Relegation factor (float)
     * <p>
     * Relegation factor of the source used for the primary selection process.
     */
    private Float relegationFactor;

    /**
     * Mean astrometric weight of the source in the AL direction (float, Angle[mas-2])
     */
    private Float weightAL;

    /**
     * Mean astrometric weight of the source in the AC direction (float, Angle[mas-2])
     */
    private Float weightAC;

    /*
     * Type of prior used in the astrometric solution (PriorType).
     */
    private PriorType priorsUsed;

}
