package bsc.packagedelivery.model;

import java.util.SortedMap;

public interface FeeStorage {

    /**
     * Insert new fee for defined weight
     *
     * @param weight
     * @param fee
     */
    void insertNewFee(double weight, double fee);

    /**
     * Return fee for concrete weight
     *
     * @param weight
     * @return
     */
    double getFeeForWeight(double weight);

    /**
     * Return fees as SortedMap
     *
     * @return
     */
    SortedMap<Double, Double> getFees();
}
