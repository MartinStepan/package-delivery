package bsc.packagedelivery.input.parser;

import bsc.packagedelivery.exceptions.IncorrectInputException;

import java.io.IOException;

public interface Parser {
    /**
     * Parse input string - it can be filename (parse input file with delivery packages or fee file)
     * or user input/line from file with delivery package/fee file
     *
     * @param input
     * @throws IncorrectInputException
     */
    void parseInput(String input) throws IncorrectInputException, IOException;
}
