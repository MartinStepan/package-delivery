package bsc.packagedelivery;

import bsc.packagedelivery.input.parser.PackageFileParser;
import bsc.packagedelivery.input.parser.PackageLineParser;
import bsc.packagedelivery.model.PackageDeliveryStorage;
import bsc.packagedelivery.model.PackageDeliveryStorageImpl;
import bsc.packagedelivery.utils.FileUtils;
import bsc.packagedelivery.utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PackageFileParser.class, PackageDeliveryStorageImpl.class, Utils.class, FileUtils.class, PackageDeliveryStorageImpl.class})
@TestPropertySource(properties = {
        "package.weight.decimal.places=3",
        "package.post.code.digits=5",
        "file.extension.support=txt"
})
public class FileParserTest {

    @Autowired
    PackageLineParser parser;

    @Autowired
    PackageDeliveryStorage storage;

    int storageSize = 0;

    @Test
    public void parseTest1() {
        storageSize = storage.getPackagesByPostCode().size();
        Assertions.assertDoesNotThrow(() -> parser.parseInput("packages.txt"));
        Assertions.assertEquals(storage.getPackagesByPostCode().size(), storageSize +3);
    }

    @Test
    public void parseTest2() {
        storageSize = storage.getPackagesByPostCode().size();
        Assertions.assertDoesNotThrow(() -> parser.parseInput("file"));
        Assertions.assertEquals(storage.getPackagesByPostCode().size(), storageSize);
    }
}
