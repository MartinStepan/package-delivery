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

    private Map<String, Double> packagesByPostCode;

    public PackageDeliveryStorageImpl() {
        this.packagesByPostCode = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public Map<String, Double> getPackagesByPostCode() {
        return packagesByPostCode;
    }

    @Override
    public void storeNewPackage(String postCode, double weight) {
        log.debug("Store new package, post code: {}, weight: {}", postCode, weight);
        if(packagesByPostCode.computeIfPresent(postCode, (key, val) -> val + weight) == null) {
            packagesByPostCode.put(postCode, weight);
        }
    }
}
