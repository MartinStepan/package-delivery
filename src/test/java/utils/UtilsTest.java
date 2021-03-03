package utils;

import bsc.packagedelivery.exceptions.IncorrectInputException;
import bsc.packagedelivery.utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Utils.class)
@TestPropertySource(properties = {
        "package.weight.decimal.places=3",
        "package.post.code.digits=5"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UtilsTest {

    @Autowired
    Utils utils;

    @Test
    public void findAndReturnWeightTest1() {
        Assertions.assertThrows(IncorrectInputException.class, () -> utils.findAndReturnWeight("1.1111 12345"));
    }

    @Test
    public void findAndReturnWeightTest2() {
        Assertions.assertThrows(NumberFormatException.class, () -> utils.findAndReturnWeight("1..1 12345"));
    }

    @Test
    public void findAndReturnWeightTest3() {
        Assertions.assertThrows(NumberFormatException.class, () -> utils.findAndReturnWeight("..1 12345"));
    }

    @Test
    public void findAndReturnWeightTest4() {
        Assertions.assertThrows(NumberFormatException.class, () -> utils.findAndReturnWeight("1.. 12345"));
    }

    @Test
    public void findAndReturnWeightTest5() {
        Assertions.assertDoesNotThrow(() -> utils.findAndReturnWeight("1.111 12345"));
    }

    @Test
    public void findAndReturnPostCodeTest1() {
        Assertions.assertThrows(IncorrectInputException.class, () -> utils.findAndReturnPostCode("1.1 1234"));
    }

    @Test
    public void findAndReturnPostCodeTest2() {
        Assertions.assertDoesNotThrow(() -> utils.findAndReturnPostCode("1.1 12345"));
    }
}
