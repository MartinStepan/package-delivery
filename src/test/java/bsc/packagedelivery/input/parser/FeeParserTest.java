package bsc.packagedelivery.input.parser;

import bsc.packagedelivery.model.FeeStorage;
import bsc.packagedelivery.model.FeeStorageImpl;
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
@ContextConfiguration(classes = { FeeParser.class, FeeStorageImpl.class, Utils.class, FileUtils.class })
@TestPropertySource(properties = {
        "package.weight.decimal.places=3",
        "package.post.code.digits=5",
        "file.extension.support=txt"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FeeParserTest {

    @Autowired
    FeeParser parser;

    @Autowired
    FeeStorage storage;

    int storageSize = 0;

    @Test
    public void parseExistFileTest() {
        storageSize = storage.getFees().size();
        Assertions.assertDoesNotThrow(() -> parser.parseInput("fees.txt"));
        Assertions.assertEquals(storage.getFees().size(), storageSize +7);
    }

    @Test
    public void parseNonExistFileTest() {
        storageSize = storage.getFees().size();
        Assertions.assertDoesNotThrow(() -> parser.parseInput("file"));
        Assertions.assertEquals(storage.getFees().size(), storageSize);
    }
}
