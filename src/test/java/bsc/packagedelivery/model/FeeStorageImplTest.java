package bsc.packagedelivery.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FeeStorageImpl.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FeeStorageImplTest {

    @Autowired
    FeeStorage feeStorage;

    int feeStorageSize = 0;

    @Test
    public void insertNewFeeTest() {
        feeStorageSize = feeStorage.getFees().size();

        feeStorage.insertNewFee(10, 5.0);
        feeStorage.insertNewFee(5, 2.5);
        feeStorage.insertNewFee(3, 2.0);
        feeStorage.insertNewFee(2, 1.5);
        feeStorage.insertNewFee(1, 1.0);
        feeStorage.insertNewFee(0.5, 0.7);
        feeStorage.insertNewFee(0.2, 0.5);

        Assertions.assertEquals(feeStorage.getFees().size(), feeStorageSize + 7);
    }

    @Test
    public void getFeeForWeightTest1() {
        feeStorage.insertNewFee(10, 5.0);
        feeStorage.insertNewFee(5, 2.5);
        feeStorage.insertNewFee(3, 2.0);

        Assertions.assertEquals(feeStorage.getFeeForWeight(10), 5.0);
        Assertions.assertEquals(feeStorage.getFeeForWeight(6), 2.5);
        Assertions.assertEquals(feeStorage.getFeeForWeight(0.1), 2.0);
    }

    @Test
    public void getFeeForWeightTest2() {
        Assertions.assertEquals(feeStorage.getFeeForWeight(5), 0);
    }
}
