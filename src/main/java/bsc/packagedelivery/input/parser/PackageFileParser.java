package bsc.packagedelivery.input.parser;

import bsc.packagedelivery.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Parser input file defined as first program argument
 * filename of file containing lines in same format as user can enter in command line
 * and then call parent method for parse line
 */
@Component
public class PackageFileParser extends PackageLineParser {

    @Autowired
    FileUtils fileUtils;

    @Override
    public void parseInput(String input) throws NumberFormatException, IOException {
        log.debug("Parse package delivery file {}", input);
        fileUtils.parseFile(input).forEach(line -> {
            try {
                super.parseInput(line);
            } catch (Exception e) {
                log.warn(e.toString());
            }
        });
    }
}
