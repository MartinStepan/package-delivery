package bsc.packagedelivery.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class FeeStorageImpl implements FeeStorage {

    static final Logger log = LoggerFactory.getLogger(FeeStorageImpl.class);

    private SortedMap<Double, Double> fees = new TreeMap<>();

    @Override
    public void insertNewFee(double weight, double fee) {
        log.debug("Insert new fee, weight: {}, fee: {}", weight, fee);

        fees.put(weight, fee);
    }

    @Override
    public double getFeeForWeight(double weight) {
        double fee = 0.0;
        try {
            //special case - ascending order
            if(weight < fees.entrySet().stream().findFirst().get().getKey()) {
                fee = fees.entrySet().stream().findFirst().get().getValue();
            } else {
                fee = fees.entrySet().stream().filter(entry -> weight >= entry.getKey()).max(Comparator.comparing(Map.Entry::getKey)).get().getValue();
            }
        } catch (Exception e) {
            log.warn("No fee found: {}, returning value 0.0", e.toString());
        }

        return fee;
    }

    @Override
    public SortedMap<Double, Double> getFees() {
        return fees;
    }
}
