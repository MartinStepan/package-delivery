package bsc.packagedelivery.input.parser;

import bsc.packagedelivery.model.FeeStorageImpl;
import bsc.packagedelivery.model.PackageDeliveryStorage;
import bsc.packagedelivery.model.PackageDeliveryStorageImpl;
import bsc.packagedelivery.utils.FileUtils;
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
@ContextConfiguration(classes = { PackageFileParser.class, PackageDeliveryStorageImpl.class, Utils.class, FileUtils.class, FeeStorageImpl.class })
@TestPropertySource(properties = {
        "package.weight.decimal.places=3",
        "package.post.code.digits=5",
        "file.extension.support=txt"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PackageFileParserTest {

    @Autowired
    PackageFileParser parser;

    @Autowired
    PackageDeliveryStorage storage;

    int storageSize = 0;

    @Test
    public void parseExistFileTest() {
        storageSize = storage.getPackagesByPostCodeWithFees().size();
        Assertions.assertDoesNotThrow(() -> parser.parseInput("packages.txt"));
        Assertions.assertEquals(storage.getPackagesByPostCodeWithFees().size(), storageSize +3);
    }

    @Test
    public void parseNonExistFileTest() {
        storageSize = storage.getPackagesByPostCodeWithFees().size();
        Assertions.assertDoesNotThrow(() -> parser.parseInput("file"));
        Assertions.assertEquals(storage.getPackagesByPostCodeWithFees().size(), storageSize);
    }
}
