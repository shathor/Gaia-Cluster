package gaiasource.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Photometric data.
 */
@Data
public class Photometry implements Serializable {

    /**
     * Number of observations contributing to {@code G} photometry (int)
     * <p>
     * Number of observations (CCD transits) that contributed to the {@code G} mean flux and mean flux error.
     */
    private Integer gNumberObs;

    /**
     * {@code G}-band mean flux (double, Flux[e-/s])
     * <p>
     * Mean flux in the {@code G}-band.
     */
    private Double gMeanFlux;

    /**
     * Error on {@code G}-band mean flux (double, Flux[e-/s])
     * <p>
     * Error on the mean flux in the {@code G}-band.
     */
    private Double gMeanFluxError;

    /**
     * G-band mean magnitude (double, Magnitude[mag])
     * <p>
     * Mean magnitude in the {@code G} band. This is computed from the {@code G}-band mean flux applying the magnitude
     * zero-point in the Vega scale.
     */
    private Double gMeanMag;

    /**
     * Photometric variability flag (string, Dimensionless[PhotometricVariability])
     * <p>
     * Indication if variability was identified in the photometric {@code G}-band.
     * <p>
     * Note that for this data release only a small subset of (variable) sources was processed and/or exported, so for
     * many (known) variable sources this flag is set to {@link PhotometricVariability#NOT_AVAILABLE}. No {@link
     * PhotometricVariability#CONSTANT} sources were exported either.
     */
    private PhotometricVariability photometricVariability;

}
