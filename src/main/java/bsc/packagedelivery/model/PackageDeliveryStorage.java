package bsc.packagedelivery.model;

import java.util.Map;

public interface PackageDeliveryStorage {

    /**
     * Return delivery packages as Map structure, key is post code
     *
     * @return Map
     */
    Map<String, Package> getPackagesByPostCodeWithFees();

    /**
     * Store new package (defined with post code and weight) to Map
     *
     * @param postCode
     * @param weight
     */
    void storeNewPackage(String postCode, double weight, double fee);
}
