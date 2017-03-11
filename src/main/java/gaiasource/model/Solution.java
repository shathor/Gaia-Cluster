package gaiasource.model;

import lombok.Data;

import java.io.Serializable;

/**
 * GaiaSource Entry
 * <p>
 * Representation of an entry for every Gaia observed source as listed in the Main Database accumulating
 * catalogue version from which the catalogue release has been generated. It contains the basic source parameters, that
 * is only data (no epoch data) and no spectra (neither nor epoch).
 */
@Data
public class Solution implements Serializable {

    /**
     * Solution Identifier (long)
     * <p>
     * The data in the MDB will be described by means of a "Solution identifier" parameter. This will be a
     * numeric field attached to each table row that can be used to unequivocally identify the version of all the
     * subsystems that where used in the generation of the data as well as the input data used. Each DPC generating the
     * data will have the freedom to choose the Solution identifier number, but they must ensure that given
     * the Solution identifier they can provide detailed information about the "conditions" used to generate
     * the data: versions of the software, version of the data used.
     */
    private Long solutionId;

    /**
     * Unique source identifier (long)
     * <p>
     * For the contents of Gaia DR1, which does not include Solar System objects, the
     * source ID consists consists of a 64-bit integer, least significant bit = 1 and most significant bit = 64.
     */
    private Long sourceId;

    /**
     * Random index used to select subsets (long)
     * <p>
     * Random index which can be used to select smaller subsets of the data that are still representative. The column
     * contains a random permutation of the numbers from 0 to N-1, where N is the number of rows.
     * <p>
     * The random index can be useful for validation (testing on 10 different random subsets), visualization (displaying
     * 1% of the data), and statistical exploration of the data, without the need to download all the data.
     */
    private Long randomIndex;

    /**
     * Reference epoch (double, Time[Julian Years])
     * <p>
     * Reference epoch to which the astrometric source parameters are referred, expressed as a Julian Year in TCB.
     */
    private Double refEpoch;

    /**
     * Right ascension (double, Angle[deg])
     * <p>
     * Barycentric right ascension {@code α} of the source in ICRS at the reference epoch {@link Solution#refEpoch}.
     */
    private Double ra;

    /**
     * Standard error of right ascension (double, Angle[mas])
     * <p>
     * Standard error {@code σα*≡σαcosδ} of the right ascension of the source in ICRS at the reference epoch {@link
     * Solution#refEpoch}.
     */
    private Double raError;

    /**
     * Declination (double, Angle[deg])
     * <p>
     * Barycentric declination {@code δ} of the source in ICRS at the reference epoch {@link Solution#refEpoch}.
     */
    private Double dec;

    /**
     * Standard error of declination (double, Angle[mas])
     * <p>
     * Standard error σδ of the declination of the source in ICRS at the reference epoch {@link Solution#refEpoch}.
     */
    private Double decError;

    /**
     * Parallax (double, Angle[mas] )
     * <p>
     * Absolute barycentric stellar parallax {@code ϖ} of the source at the reference epoch {@link Solution#refEpoch}.
     */
    private Double parallax;

    /**
     * Standard error of parallax (double, Angle[mas])
     * <p>
     * Standard error {@code σϖ} of the stellar parallax at the reference epoch {@link Solution#refEpoch}.
     */
    private Double parallaxError;

    /**
     * Proper motion in right ascension direction (double, Angular Velocity[mas/year])
     * <p>
     * Proper motion in right ascension {@code μα*} of the source in ICRS at the reference epoch {@link
     * Solution#refEpoch}. This is the projection of the proper motion vector in the direction of increasing right
     * ascension.
     */
    private Double pmRa;

    /**
     * Standard error of proper motion in right ascension direction (double, Angular Velocity[mas/year])
     * <p>
     * Standard error {@code σμα*} of the proper motion vector in right ascension at the reference epoch {@link
     * Solution#refEpoch}.
     */
    private Double pmRaError;

    /**
     * Proper motion in declination direction (double, Angular Velocity[mas/year])
     * <p>
     * Proper motion in declination {@code μδ} of the source at the reference epoch {@link Solution#refEpoch}. This is the
     * projection of the proper motion vector in the direction of increasing declination.
     */
    private Double pmDec;

    /**
     * Standard error of proper motion in declination direction (double, Angular Velocity[mas/year])
     * <p>
     * Standard error {@code σμδ} of the proper motion in declination at the reference epoch {@link Solution#refEpoch}.
     */
    private Double pmDecError;

    /**
     * {@link Correlation}.
     */
    private final Correlation correlation = new Correlation();

    /**
     * {@link Astrometric}.
     */
    private final Astrometric astrometric = new Astrometric();

    /**
     * Amount of observations matched to this source (short)
     * <p>
     * This field indicates the number of observations (detection transits) that have been matched to a given source
     * during the last internal cross-match revision.
     */
    private Integer matchedObservations;

    /**
     * Source with duplicate sources (boolean)
     * <p>
     * During data processing, this source happened to been duplicated and one source only has been kept. This may
     * indicate observational, cross-matching or processing problems, or stellar multiplicity, and probable astrometric
     * or photometric problems in all cases.
     */
    private Boolean duplicatedSource;

    /**
     * {@link ScanDirection}.
     */
    private final ScanDirection scanDirection = new ScanDirection();

    /**
     * {@link Photometry}.
     */
    private final Photometry photometry = new Photometry();

    /**
     * Galactic longitude (double, Angle[deg])
     * <p>
     * Galactic Longitude of the object at reference epoch {@link Solution#refEpoch}.
     */
    private Double galacticLongitude;

    /**
     * Galactic latitude (double, Angle[deg])
     * <p>
     * Galactic Latitude of the object at reference epoch {@link Solution#refEpoch}.
     */
    private Double galacticLatitude;

    /**
     * Ecliptic longitude (double, Angle[deg])
     * <p>
     * Ecliptic Longitude of the object at reference epoch {@link Solution#refEpoch}.
     */
    private Double eclipticLongitude;

    /**
     * Ecliptic latitude (double, Angle[deg])
     * <p>
     * Ecliptic Latitude of the object at reference epoch {@link Solution#refEpoch}.
     */
    private Double eclipticLatitude;

}
