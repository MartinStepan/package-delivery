package bsc.packagedelivery;

import bsc.packagedelivery.exceptions.IncorrectInputException;
import bsc.packagedelivery.input.parser.PackageUserInputParser;
import bsc.packagedelivery.input.parser.PackageLineParser;
import bsc.packagedelivery.model.PackageDeliveryStorage;
import bsc.packagedelivery.model.PackageDeliveryStorageImpl;
import bsc.packagedelivery.utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PackageUserInputParser.class, PackageDeliveryStorageImpl.class, Utils.class, PackageDeliveryStorageImpl.class })
@TestPropertySource(properties = {
        "package.weight.decimal.places=3",
        "package.post.code.digits=5"
})
public class UserInputParserTest {

    @Autowired
    PackageLineParser parser;

    @Autowired
    PackageDeliveryStorage storage;

    int storageSize = 0;

    @Test
    public void parseIntputTest1() {
        storageSize = storage.getPackagesByPostCode().size();
        Assertions.assertDoesNotThrow(() -> parser.parseInput("1.1 99999"));
        Assertions.assertEquals(storage.getPackagesByPostCode().size(), storageSize + 1);
    }

    @Test
    public void parseIntputTest2() {
        storageSize = storage.getPackagesByPostCode().size();
        Assertions.assertDoesNotThrow(() -> parser.parseInput("  1.1 88888   "));
        Assertions.assertEquals(storage.getPackagesByPostCode().size(), storageSize + 1);
    }

    @Test
    public void parseIntputTest3() {
        storageSize = storage.getPackagesByPostCode().size();
        Assertions.assertThrows(IncorrectInputException.class, () -> parser.parseInput("1.112345"));
        Assertions.assertEquals(storage.getPackagesByPostCode().size(), storageSize);
    }

    @Test
    public void parseIntputTest4() {
        storageSize = storage.getPackagesByPostCode().size();
        Assertions.assertThrows(IncorrectInputException.class, () -> parser.parseInput("1.1  12345"));
        Assertions.assertEquals(storage.getPackagesByPostCode().size(), storageSize);
    }
}
