package gaiasource.dao;

import gaiasource.model.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * The field mapping.
 */
public abstract class Field<T> extends FieldContract<T> implements Serializable {

    private static final long serialVersionUID = 5413419332815837198L;

    /**
     * @return an array of all defined columns
     */
    public static Field<?>[] values() {
        return FIELDS;
    }

    /**
     * @return an array of all column names
     */
    public static String[] columnNames() {
        return columnNames(values());
    }

    /**
     * Gets the column names of given fields.
     *
     * @param fields the fields to get column names for
     * @return an array of column names of given fields
     */
    public static String[] columnNames(Field<?>... fields) {
        return columnNames(Stream.of(fields));
    }

    /**
     * Gets the column names of given fields.
     *
     * @param fields the fields to get column names for
     * @return an array of column names of given fields
     */
    public static String[] columnNames(Collection<Field<?>> fields) {
        return columnNames(fields.stream());
    }

    private static String[] columnNames(Stream<Field<?>> fields) {
        return fields.map(Field::getColumnName)
                     .toArray(String[]::new);
    }

    /**
     * {@link Solution#solutionId}
     */
    public static final Field<Long> SOLUTION_ID = new Field<Long>() {
        @Override
        public String getColumnName() {
            return "solution_id";
        }

        @Override
        public Class<Long> getDataType() {
            return Long.class;
        }

        @Override
        public void set(Solution solution, Long value) {
            solution.setSolutionId(value);
        }

        @Override
        public Long get(Solution solution) {
            return solution.getSolutionId();
        }
    };

    /**
     * {@link Solution#sourceId}
     */
    public static final Field<Long> SOURCE_ID = new Field<Long>() {
        @Override
        public String getColumnName() {
            return "source_id";
        }

        @Override
        public Class<Long> getDataType() {
            return Long.class;
        }

        @Override
        public void set(Solution solution, Long value) {
            solution.setSourceId(value);
        }

        @Override
        public Long get(Solution solution) {
            return solution.getSourceId();
        }
    };

    /**
     * {@link Solution#randomIndex}
     */
    public static final Field<Long> RANDOM_INDEX = new Field<Long>() {
        @Override
        public String getColumnName() {
            return "random_index";
        }

        @Override
        public Class<Long> getDataType() {
            return Long.class;
        }

        @Override
        public void set(Solution solution, Long value) {
            solution.setRandomIndex(value);
        }

        @Override
        public Long get(Solution solution) {
            return solution.getRandomIndex();
        }
    };

    /**
     * {@link Solution#refEpoch}
     */
    public static final Field<Double> REF_EPOCH = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "ref_epoch";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setRefEpoch(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getRefEpoch();
        }
    };

    /**
     * {@link Solution#ra}
     */
    public static final Field<Double> RA = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "ra";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setRa(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getRa();
        }
    };

    /**
     * {@link Solution#raError}
     */
    public static final Field<Double> RA_ERROR = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "ra_error";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setRaError(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getRaError();
        }
    };

    /**
     * {@link Solution#dec}
     */
    public static final Field<Double> DEC = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "dec";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setDec(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getDec();
        }
    };

    /**
     * {@link Solution#decError}
     */
    public static final Field<Double> DEC_ERROR = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "dec_error";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setDecError(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getDecError();
        }
    };

    /**
     * {@link Solution#parallax}
     */
    public static final Field<Double> PARALLAX = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "parallax";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setParallax(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getParallax();
        }
    };

    /**
     * {@link Solution#parallaxError}
     */
    public static final Field<Double> PARALLAX_ERROR = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "parallax_error";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setParallaxError(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getParallaxError();
        }
    };

    /**
     * {@link Solution#pmRa}
     */
    public static final Field<Double> PMRA = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "pmra";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setPmRa(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getPmRa();
        }
    };

    /**
     * {@link Solution#pmRaError}
     */
    public static final Field<Double> PMRA_ERROR = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "pmra_error";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setPmRaError(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getPmRaError();
        }
    };

    /**
     * {@link Solution#pmDec}
     */
    public static final Field<Double> PMDEC = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "pmdec";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setPmDec(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getPmDec();
        }
    };

    /**
     * {@link Solution#pmDecError}
     */
    public static final Field<Double> PMDEC_ERROR = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "pmdec_error";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setPmDecError(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getPmDecError();
        }
    };

    /**
     * {@link Correlation#raDec}
     */
    public static final Field<Float> RA_DEC_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "ra_dec_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setRaDec(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getRaDec();
        }
    };

    /**
     * {@link Correlation#raParallax}
     */
    public static final Field<Float> RA_PARALLAX_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "ra_parallax_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setRaParallax(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getRaParallax();
        }
    };

    /**
     * {@link Correlation#raPmRa}
     */
    public static final Field<Float> RA_PMRA_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "ra_pmra_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setRaPmRa(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getRaPmRa();
        }
    };

    /**
     * {@link Correlation#raPmDec}
     */
    public static final Field<Float> RA_PMDEC_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "ra_pmdec_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setRaPmDec(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getRaPmDec();
        }
    };

    /**
     * {@link Correlation#raPmDec}
     */
    public static final Field<Float> DEC_PARALLAX_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "dec_parallax_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setDecParallax(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getDecParallax();
        }
    };

    /**
     * {@link Correlation#decPmRa}
     */
    public static final Field<Float> DEC_PMRA_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "dec_pmra_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setDecPmRa(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getDecPmRa();
        }
    };

    /**
     * {@link Correlation#decPmDec}
     */
    public static final Field<Float> DEC_PMDEC_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "dec_pmdec_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setDecPmDec(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getDecPmDec();
        }
    };

    /**
     * {@link Correlation#parallaxPmRa}
     */
    public static final Field<Float> PARALLAX_PMRA_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "parallax_pmra_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setParallaxPmRa(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getParallaxPmRa();
        }
    };

    /**
     * {@link Correlation#parallaxPmDec}
     */
    public static final Field<Float> PARALLAX_PMDEC_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "parallax_pmdec_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setParallaxPmRa(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getParallaxPmRa();
        }
    };

    /**
     * {@link Correlation#pmRaPmDec}
     */
    public static final Field<Float> PMRA_PMDEC_CORR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "pmra_pmdec_corr";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getCorrelation().setPmRaPmDec(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getCorrelation().getPmRaPmDec();
        }
    };

    /**
     * {@link Astrometric#numberObsAL}
     */
    public static final Field<Integer> ASTROMETRIC_N_OBS_AL = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "astrometric_n_obs_al";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            solution.getAstrometric().setNumberObsAL(value);
        }

        @Override
        public Integer get(Solution solution) {
            return solution.getAstrometric().getNumberObsAL();
        }
    };

    /**
     * {@link Astrometric#numberObsAC}
     */
    public static final Field<Integer> ASTROMETRIC_N_OBS_AC = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "astrometric_n_obs_ac";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            solution.getAstrometric().setNumberObsAC(value);
        }

        @Override
        public Integer get(Solution solution) {
            return solution.getAstrometric().getNumberObsAC();
        }
    };

    /**
     * {@link Astrometric#numberGoodObsAL}
     */
    public static final Field<Integer> ASTROMETRIC_N_GOOD_OBS_AL = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "astrometric_n_good_obs_al";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            solution.getAstrometric().setNumberGoodObsAL(value);
        }

        @Override
        public Integer get(Solution solution) {
            return solution.getAstrometric().getNumberGoodObsAL();
        }
    };

    /**
     * {@link Astrometric#numberGoodObsAC}
     */
    public static final Field<Integer> ASTROMETRIC_N_GOOD_OBS_AC = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "astrometric_n_good_obs_ac";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            solution.getAstrometric().setNumberGoodObsAC(value);
        }

        @Override
        public Integer get(Solution solution) {
            return solution.getAstrometric().getNumberGoodObsAC();
        }
    };

    /**
     * {@link Astrometric#numberBadObsAL}
     */
    public static final Field<Integer> ASTROMETRIC_N_BAD_OBS_AL = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "astrometric_n_bad_obs_al";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            solution.getAstrometric().setNumberBadObsAL(value);
        }

        @Override
        public Integer get(Solution solution) {
            return solution.getAstrometric().getNumberBadObsAL();
        }
    };

    /**
     * {@link Astrometric#numberBadObsAC}
     */
    public static final Field<Integer> ASTROMETRIC_N_BAD_OBS_AC = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "astrometric_n_bad_obs_ac";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            solution.getAstrometric().setNumberBadObsAC(value);
        }

        @Override
        public Integer get(Solution solution) {
            return solution.getAstrometric().getNumberBadObsAC();
        }
    };

    /**
     * {@link Astrometric#deltaQ}
     */
    public static final Field<Float> ASTROMETRIC_DELTA_Q = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "astrometric_delta_q";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getAstrometric().setDeltaQ(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getAstrometric().getDeltaQ();
        }
    };

    /**
     * {@link Astrometric#excessNoise}
     */
    public static final Field<Double> ASTROMETRIC_EXCESS_NOISE = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "astrometric_excess_noise";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.getAstrometric().setExcessNoise(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getAstrometric().getExcessNoise();
        }
    };

    /**
     * {@link Astrometric#excessNoiseSignificance}
     */
    public static final Field<Double> ASTROMETRIC_EXCESS_NOISE_SIG = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "astrometric_excess_noise_sig";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.getAstrometric().setExcessNoiseSignificance(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getAstrometric().getExcessNoiseSignificance();
        }
    };

    /**
     * {@link Astrometric#primaryFlag}
     */
    public static final Field<Boolean> ASTROMETRIC_PRIMARY_FLAG = new Field<Boolean>() {
        @Override
        public String getColumnName() {
            return "astrometric_primary_flag";
        }

        @Override
        public Class<Boolean> getDataType() {
            return Boolean.class;
        }

        @Override
        public void set(Solution solution, Boolean value) {
            solution.getAstrometric().setPrimaryFlag(value);
        }

        @Override
        public Boolean get(Solution solution) {
            return solution.getAstrometric().getPrimaryFlag();
        }
    };

    /**
     * {@link Astrometric#relegationFactor}
     */
    public static final Field<Float> ASTROMETRIC_RELEGATION_FACTOR = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "astrometric_relegation_factor";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getAstrometric().setRelegationFactor(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getAstrometric().getRelegationFactor();
        }
    };

    /**
     * {@link Astrometric#weightAL}
     */
    public static final Field<Float> ASTROMETRIC_WEIGHT_AL = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "astrometric_weight_al";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getAstrometric().setWeightAL(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getAstrometric().getWeightAL();
        }
    };

    /**
     * {@link Astrometric#weightAC}
     */
    public static final Field<Float> ASTROMETRIC_WEIGHT_AC = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "astrometric_weight_ac";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getAstrometric().setWeightAC(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getAstrometric().getWeightAC();
        }
    };

    /**
     * {@link Astrometric#priorsUsed}
     */
    public static final Field<Integer> ASTROMETRIC_PRIORS_USED = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "astrometric_priors_used";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            PriorType priorsUsed = PriorType.getByCode(value);
            solution.getAstrometric().setPriorsUsed(priorsUsed);
        }

        @Override
        public Integer get(Solution solution) {
            PriorType priorsUsed = solution.getAstrometric().getPriorsUsed();
            return priorsUsed != null ? priorsUsed.getCode() : null;
        }
    };

    /**
     * {@link Solution#matchedObservations}
     */
    public static final Field<Integer> MATCHED_OBSERVATIONS = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "matched_observations";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            solution.setMatchedObservations(value);
        }

        @Override
        public Integer get(Solution solution) {
            return solution.getMatchedObservations();
        }
    };

    /**
     * {@link Solution#duplicatedSource}
     */
    public static final Field<Boolean> DUPLICATED_SOURCE = new Field<Boolean>() {
        @Override
        public String getColumnName() {
            return "duplicated_source";
        }

        @Override
        public Class<Boolean> getDataType() {
            return Boolean.class;
        }

        @Override
        public void set(Solution solution, Boolean value) {
            solution.setDuplicatedSource(value);
        }

        @Override
        public Boolean get(Solution solution) {
            return solution.getDuplicatedSource();
        }
    };

    /**
     * {@link ScanDirection#strengthK1}
     */
    public static final Field<Float> SCAN_DIRECTION_STRENGTH_K1 = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "scan_direction_strength_k1";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getScanDirection().setStrengthK1(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getScanDirection().getStrengthK1();
        }
    };

    /**
     * {@link ScanDirection#strengthK2}
     */
    public static final Field<Float> SCAN_DIRECTION_STRENGTH_K2 = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "scan_direction_strength_k2";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getScanDirection().setStrengthK2(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getScanDirection().getStrengthK2();
        }
    };

    /**
     * {@link ScanDirection#strengthK3}
     */
    public static final Field<Float> SCAN_DIRECTION_STRENGTH_K3 = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "scan_direction_strength_k3";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getScanDirection().setStrengthK3(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getScanDirection().getStrengthK3();
        }
    };

    /**
     * {@link ScanDirection#strengthK1}
     */
    public static final Field<Float> SCAN_DIRECTION_STRENGTH_K4 = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "scan_direction_strength_k4";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getScanDirection().setStrengthK4(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getScanDirection().getStrengthK4();
        }
    };

    /**
     * {@link ScanDirection#meanK1}
     */
    public static final Field<Float> SCAN_DIRECTION_MEAN_K1 = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "scan_direction_mean_k1";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getScanDirection().setMeanK1(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getScanDirection().getMeanK1();
        }
    };

    /**
     * {@link ScanDirection#meanK2}
     */
    public static final Field<Float> SCAN_DIRECTION_MEAN_K2 = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "scan_direction_mean_k2";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getScanDirection().setMeanK2(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getScanDirection().getMeanK2();
        }
    };

    /**
     * {@link ScanDirection#meanK3}
     */
    public static final Field<Float> SCAN_DIRECTION_MEAN_K3 = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "scan_direction_mean_k3";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getScanDirection().setMeanK3(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getScanDirection().getMeanK3();
        }
    };

    /**
     * {@link ScanDirection#meanK4}
     */
    public static final Field<Float> SCAN_DIRECTION_MEAN_K4 = new Field<Float>() {
        @Override
        public String getColumnName() {
            return "scan_direction_mean_k4";
        }

        @Override
        public Class<Float> getDataType() {
            return Float.class;
        }

        @Override
        public void set(Solution solution, Float value) {
            solution.getScanDirection().setMeanK4(value);
        }

        @Override
        public Float get(Solution solution) {
            return solution.getScanDirection().getMeanK4();
        }
    };

    /**
     * {@link Photometry#getGNumberObs()}
     */
    public static final Field<Integer> PHOT_G_N_OBS = new Field<Integer>() {
        @Override
        public String getColumnName() {
            return "phot_g_n_obs";
        }

        @Override
        public Class<Integer> getDataType() {
            return Integer.class;
        }

        @Override
        public void set(Solution solution, Integer value) {
            solution.getPhotometry().setGNumberObs(value);
        }

        @Override
        public Integer get(Solution solution) {
            return solution.getPhotometry().getGNumberObs();
        }
    };

    /**
     * {@link Photometry#gMeanFlux}
     */
    public static final Field<Double> PHOT_G_MEAN_FLUX = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "phot_g_mean_flux";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.getPhotometry().setGMeanFlux(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getPhotometry().getGMeanFlux();
        }
    };

    /**
     * {@link Photometry#gMeanFluxError}
     */
    public static final Field<Double> PHOT_G_MEAN_FLUX_ERROR = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "phot_g_mean_flux_error";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.getPhotometry().setGMeanFluxError(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getPhotometry().getGMeanFluxError();
        }
    };

    /**
     * {@link Photometry#gMeanMag}
     */
    public static final Field<Double> PHOT_G_MEAN_MAG = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "phot_g_mean_mag";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.getPhotometry().setGMeanMag(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getPhotometry().getGMeanMag();
        }
    };

    /**
     * {@link Photometry#photometricVariability}
     */
    public static final Field<String> PHOT_VARIABLE_FLAG = new Field<String>() {
        @Override
        public String getColumnName() {
            return "phot_variable_flag";
        }

        @Override
        public Class<String> getDataType() {
            return String.class;
        }

        @Override
        public void set(Solution solution, String value) {
            PhotometricVariability photometricVariability = PhotometricVariability.getByName(value);
            solution.getPhotometry().setPhotometricVariability(photometricVariability);
        }

        @Override
        public String get(Solution solution) {
            PhotometricVariability photometricVariability = solution.getPhotometry().getPhotometricVariability();
            return photometricVariability != null ? photometricVariability.getName() : null;
        }
    };

    /**
     * {@link Solution#galacticLongitude}
     */
    public static final Field<Double> L = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "l";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setGalacticLongitude(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getGalacticLongitude();
        }
    };

    /**
     * {@link Solution#galacticLatitude}
     */
    public static final Field<Double> B = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "b";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setGalacticLatitude(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getGalacticLatitude();
        }
    };

    /**
     * {@link Solution#eclipticLongitude}
     */
    public static final Field<Double> ECL_LON = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "ecl_lon";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setEclipticLongitude(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getEclipticLongitude();
        }
    };

    /**
     * {@link Solution#eclipticLatitude}
     */
    public static final Field<Double> ECL_LAT = new Field<Double>() {
        @Override
        public String getColumnName() {
            return "ecl_lat";
        }

        @Override
        public Class<Double> getDataType() {
            return Double.class;
        }

        @Override
        public void set(Solution solution, Double value) {
            solution.setEclipticLatitude(value);
        }

        @Override
        public Double get(Solution solution) {
            return solution.getEclipticLatitude();
        }
    };

    private static final Field<?>[] FIELDS = new Field<?>[]{
            SOLUTION_ID,
            SOURCE_ID,
            RANDOM_INDEX,
            REF_EPOCH,
            RA,
            RA_ERROR,
            DEC,
            DEC_ERROR,
            PARALLAX,
            PARALLAX_ERROR,
            PMRA,
            PMRA_ERROR,
            PMDEC,
            PMDEC_ERROR,
            RA_DEC_CORR,
            RA_PARALLAX_CORR,
            RA_PMRA_CORR,
            RA_PMDEC_CORR,
            DEC_PARALLAX_CORR,
            DEC_PMRA_CORR,
            DEC_PMDEC_CORR,
            PARALLAX_PMRA_CORR,
            PARALLAX_PMDEC_CORR,
            PMRA_PMDEC_CORR,
            ASTROMETRIC_N_OBS_AL,
            ASTROMETRIC_N_OBS_AC,
            ASTROMETRIC_N_GOOD_OBS_AL,
            ASTROMETRIC_N_GOOD_OBS_AC,
            ASTROMETRIC_N_BAD_OBS_AL,
            ASTROMETRIC_N_BAD_OBS_AC,
            ASTROMETRIC_DELTA_Q,
            ASTROMETRIC_EXCESS_NOISE,
            ASTROMETRIC_EXCESS_NOISE_SIG,
            ASTROMETRIC_PRIMARY_FLAG,
            ASTROMETRIC_RELEGATION_FACTOR,
            ASTROMETRIC_WEIGHT_AL,
            ASTROMETRIC_WEIGHT_AC,
            ASTROMETRIC_PRIORS_USED,
            MATCHED_OBSERVATIONS,
            DUPLICATED_SOURCE,
            SCAN_DIRECTION_STRENGTH_K1,
            SCAN_DIRECTION_STRENGTH_K2,
            SCAN_DIRECTION_STRENGTH_K3,
            SCAN_DIRECTION_STRENGTH_K4,
            SCAN_DIRECTION_MEAN_K1,
            SCAN_DIRECTION_MEAN_K2,
            SCAN_DIRECTION_MEAN_K3,
            SCAN_DIRECTION_MEAN_K4,
            PHOT_G_N_OBS,
            PHOT_G_MEAN_FLUX,
            PHOT_G_MEAN_FLUX_ERROR,
            PHOT_G_MEAN_MAG,
            PHOT_VARIABLE_FLAG,
            L,
            B,
            ECL_LON,
            ECL_LAT
    };

}
