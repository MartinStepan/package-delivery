package bsc.packagedelivery.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class PackageDeliveryStorageImpl implements PackageDeliveryStorage {

    static final Logger log = LoggerFactory.getLogger(PackageDeliveryStorageImpl.class);

    private Map<String, Package> packagesByPostCodeWithFees;

    public PackageDeliveryStorageImpl() {
        this.packagesByPostCodeWithFees = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public Map<String, Package> getPackagesByPostCodeWithFees() {
        return packagesByPostCodeWithFees;
    }

    @Override
    public void storeNewPackage(String postCode, double weight, double fee) {
        log.debug("Store new package, post code: {}, weight: {}, fee: {}", postCode, weight, fee);

        if(packagesByPostCodeWithFees.computeIfPresent(postCode, (key, val) -> val = val.updateWeightAndFee(weight, fee)) == null) {
            packagesByPostCodeWithFees.put(postCode, new Package(postCode, weight, fee));
        }
    }
}
