package bsc.packagedelivery.input.parser;

import bsc.packagedelivery.exceptions.IncorrectInputException;
import bsc.packagedelivery.model.PackageDeliveryStorage;
import bsc.packagedelivery.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class PackageLineParser implements Parser {

    static final Logger log = LoggerFactory.getLogger(PackageLineParser.class);

    @Autowired
    protected PackageDeliveryStorage packageStorage;

    @Autowired
    protected Utils utils;

    @Override
    public void parseInput(String input) throws IncorrectInputException, NumberFormatException, IOException {
        input = input.trim();
        log.debug("Line readed after trim: {}", input);

        if(!input.contains(" ")) {
            throw new IncorrectInputException("Missing gap in input arguments");
        }

        if(input.indexOf(" ") != input.lastIndexOf(" ")) {
            throw new IncorrectInputException("Find more than one gap in input arguments");
        }

        double weight = utils.findAndReturnWeight(input);
        String postCode = utils.findAndReturnPostCode(input);

        packageStorage.storeNewPackage(postCode, weight);
    }
}
