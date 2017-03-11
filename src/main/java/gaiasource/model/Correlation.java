package gaiasource.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Correlations between right ascension (ra), declination (dec), parralax or proper motion (pm).
 */
@Data
public class Correlation implements Serializable {

    /**
     * Correlation between right ascension and declination (float, Dimensionless Unit [-1:+1]).
     */
    private Float raDec;

    /**
     * Correlation between right ascension and parallax (float, Dimensionless Unit [-1:+1]).
     */
    private Float raParallax;

    /**
     * Correlation between right ascension and proper motion in right ascension (float, Dimensionless Unit [-1:+1]).
     */
    private Float raPmRa;

    /**
     * Correlation between right ascension and proper motion in declination (float, Dimensionless Unit [-1:+1]).
     */
    private Float raPmDec;

    /**
     * Correlation between declination and parallax (float, Dimensionless Unit [-1:+1]).
     */
    private Float decParallax;

    /**
     * Correlation between declination and proper motion in right ascension (float, Dimensionless Unit [-1:+1]).
     */
    private Float decPmRa;

    /**
     * Correlation between declination and proper motion in declination (float, Dimensionless Unit [-1:+1]).
     */
    private Float decPmDec;

    /**
     * Correlation between parallax and proper motion in right ascension (float, Dimensionless Unit [-1:+1]).
     */
    private Float parallaxPmRa;

    /**
     * Correlation between parallax and proper motion in declination (float, Dimensionless Unit [-1:+1]).
     */
    private Float parallaxPmDec;

    /**
     * Correlation between proper motion in right ascension and proper motion in declination (float, Dimensionless Unit [-1:+1]).
     */
    private Float pmRaPmDec;

}
