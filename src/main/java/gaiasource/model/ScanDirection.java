package gaiasource.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Scan direction data.
 * <p>
 * The scanDirectionStrength and scanDirectionMean quantify the distribution of AL scan directions across the source.
 * scanDirectionMean[k-1] (k=1,2,3,4) is 1/k times the argument of the trigonometric moments {@code mk=<exp(ikθ)>},
 * where {@code θ} is the position angle of the scan and the mean value is taken over the nObs[0] AL observations
 * contributing to the astrometric parameters of the source. {@code θ} is defined in the usual astronomical sense:
 * {@code θ}=0 when the FoV is moving towards local North, and {@code θ}=90∘ towards local East.
 * <p>
 * scanDirectionMean[k-1] is an angle between -180∘/k and +180∘/k, giving the mean position angle of the scans at order
 * k.
 * <p>
 * The different orders k are statistics of the scan directions modulo 360∘/k. For example, at first order (k=1), {@code
 * θ}=10∘ and {@code θ}=190∘ count as different directions, but at second order (k=2) they are the same. Thus,
 * scanDirectionMean[0] is the mean direction when the sense of direction is taken into account, while
 * scanDirectionMean[1] is the mean direction without regard to the sense of the direction. For example,
 * scanDirectionMean[0] = 0 means that the scans preferentially go towards North, while scanDirectionMean[1] = 0 means
 * that they preferentially go in the North-South direction, and scanDirectionMean[4] = 0 that they preferentially go
 * either in the North-South or in the East-West direction.
 */
@Data
public class ScanDirection implements Serializable {

    /**
     * Degree of concentration of scan directions across the source (float).
     */
    private Float strengthK1;

    /**
     * Degree of concentration of scan directions across the source (float).
     */
    private Float strengthK2;

    /**
     * Degree of concentration of scan directions across the source (float).
     */
    private Float strengthK3;

    /**
     * Degree of concentration of scan directions across the source (float).
     */
    private Float strengthK4;

    /**
     * Mean position angle of scan directions across the source (float, Angle[deg]).
     */
    private Float meanK1;

    /**
     * Mean position angle of scan directions across the source (float, Angle[deg]).
     */
    private Float meanK2;

    /**
     * Mean position angle of scan directions across the source (float, Angle[deg]).
     */
    private Float meanK3;

    /**
     * Mean position angle of scan directions across the source (float, Angle[deg]).
     */
    private Float meanK4;

}
