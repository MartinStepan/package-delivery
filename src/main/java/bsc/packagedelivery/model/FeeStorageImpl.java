package bsc.packagedelivery.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class FeeStorageImpl {

    static final Logger log = LoggerFactory.getLogger(FeeStorageImpl.class);

    private SortedMap<Double, Double> fees = new TreeMap<Double, Double>();

    public void insertNewFee(double weight, double fee) {
        log.debug("Insert new fee, weight: {}, fee: {}", weight, fee);

        fees.put(weight, fee);
    }

    public SortedMap<Double, Double> getFees() {
        return fees;
    }
}
