package bsc.packagedelivery.input.parser;

import bsc.packagedelivery.exceptions.ExtensionException;
import bsc.packagedelivery.exceptions.IncorrectInputException;
import bsc.packagedelivery.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser input file defined as program argument
 * and then call parent method for parse line
 */
@Component
public class PackageFileParser extends PackageLineParser {

    @Autowired
    FileUtils fileUtils;

    @Override
    public void parseInput(String input) throws IncorrectInputException, NumberFormatException, IOException {
        parseFile(input).forEach(line -> {
            try {
                super.parseInput(line);
            } catch (Exception e) {
                log.warn(e.toString());
            }
        });
    }

    private List<String> parseFile(String filename) throws IOException {
        log.info("Parsing file {}", filename);
        List<String> lines = new ArrayList<>();

        try {
            fileUtils.checkFileExtension(filename);
        } catch (ExtensionException e) {
            log.warn(e.toString());
            return lines;
        }

        if(!fileUtils.fileExist(filename)) {
            log.warn("File {} not found", filename);
            return lines;
        }

        Files.lines(Paths.get(filename)).forEach(line -> lines.add(line));

        return lines;
    }
}
