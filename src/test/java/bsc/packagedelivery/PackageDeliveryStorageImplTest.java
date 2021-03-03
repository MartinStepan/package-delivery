package bsc.packagedelivery;

import bsc.packagedelivery.model.PackageDeliveryStorageImpl;
import bsc.packagedelivery.model.PackageDeliveryStorage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PackageDeliveryStorageImpl.class)
public class PackageDeliveryStorageImplTest {

    @Autowired
    PackageDeliveryStorage storage;

    @Test
    public void storeNewPackageTest() {
        String postCode1 = "12345";
        String postCode2 = "23456";
        double weight1 = 1.0;
        double weight2 = 2.0;
        double weight3 = 3.0;

        Assertions.assertEquals(storage.getPackagesByPostCode().size(), 0);

        storage.storeNewPackage(postCode1, weight1);
        storage.storeNewPackage(postCode1, weight2);
        storage.storeNewPackage(postCode2, weight3);

        Assertions.assertEquals(storage.getPackagesByPostCode().size(), 2);
        Assertions.assertEquals(storage.getPackagesByPostCode().get(postCode1), weight1 + weight2);
        Assertions.assertEquals(storage.getPackagesByPostCode().get(postCode2), weight3);
    }
}
