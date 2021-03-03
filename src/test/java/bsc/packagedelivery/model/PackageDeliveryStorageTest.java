package bsc.packagedelivery.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PackageDeliveryStorageImpl.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PackageDeliveryStorageTest {

    @Autowired
    PackageDeliveryStorage storage;

    @Test
    public void storeNewPackageTest() {
        String postCode1 = "12345";
        String postCode2 = "23456";
        double weight1 = 1.0;
        double weight2 = 2.0;
        double weight3 = 3.0;

        Assertions.assertEquals(storage.getPackagesByPostCodeWithFees().size(), 0);

        storage.storeNewPackage(postCode1, weight1, 0.0);
        storage.storeNewPackage(postCode1, weight2, 0.0);
        storage.storeNewPackage(postCode2, weight3, 0.0);

        Assertions.assertEquals(storage.getPackagesByPostCodeWithFees().size(), 2);
        Assertions.assertEquals(storage.getPackagesByPostCodeWithFees().get(postCode1).getWeight(), weight1 + weight2);
        Assertions.assertEquals(storage.getPackagesByPostCodeWithFees().get(postCode2).getWeight(), weight3);
    }
}
