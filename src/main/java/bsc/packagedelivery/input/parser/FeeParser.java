package bsc.packagedelivery.input.parser;

import bsc.packagedelivery.exceptions.IncorrectInputException;
import bsc.packagedelivery.model.FeeStorage;
import bsc.packagedelivery.utils.FileUtils;
import bsc.packagedelivery.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Parser input file defined as second program argument
 * filename of file containing information about fees related to package weight
 */
@Component
public class FeeParser implements Parser {

    static final Logger log = LoggerFactory.getLogger(FeeParser.class);

    @Autowired
    FileUtils fileUtils;

    @Autowired
    Utils utils;

    @Autowired
    FeeStorage feeStorage;

    @Override
    public void parseInput(String input) throws IOException {
        log.debug("Parse fee file {}", input);
        fileUtils.parseFile(input).forEach(line -> {
            try {
                feeStorage.insertNewFee(utils.findAndReturnWeight(line), utils.findAndReturnFee(line));
            } catch (IncorrectInputException e) {
                log.warn(e.toString());
            }
        });
    }
}
