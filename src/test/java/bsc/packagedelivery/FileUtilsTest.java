package bsc.packagedelivery;

import bsc.packagedelivery.exceptions.ExtensionException;
import bsc.packagedelivery.utils.FileUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FileUtils.class)
@TestPropertySource(properties = {
        "file.extension.support=txt"
})
public class FileUtilsTest {

    @Autowired
    FileUtils fileUtils;

    @Test
    public void checkFileWithNoExtension1() {
        Assertions.assertThrows(ExtensionException.class, () -> fileUtils.checkFileExtension("file"));
    }

    @Test
    public void checkFileWithNoExtension2() {
        Assertions.assertThrows(ExtensionException.class, () -> fileUtils.checkFileExtension("file."));
    }

    @Test
    public void checkFileWithNoExtension3() {
        Assertions.assertThrows(ExtensionException.class, () -> fileUtils.checkFileExtension("../file"));
    }

    @Test
    public void checkFileWithNoExtension4() {
        Assertions.assertThrows(ExtensionException.class, () -> fileUtils.checkFileExtension("..\\file"));
    }

    @Test
    public void checkFileWithNoExtension5() {
        Assertions.assertThrows(ExtensionException.class, () -> fileUtils.checkFileExtension("../file."));
    }

    @Test
    public void checkFileWithNoExtension6() {
        Assertions.assertThrows(ExtensionException.class, () -> fileUtils.checkFileExtension("..\\file."));
    }

    @Test
    public void checkFileWithUnsupportedExtension() {
        Assertions.assertThrows(ExtensionException.class, () -> fileUtils.checkFileExtension("file.x"));
    }

    @Test
    public void checkFileWithSupportedExtension() {
        Assertions.assertDoesNotThrow(() -> fileUtils.checkFileExtension("filea.txt"));
    }
}
