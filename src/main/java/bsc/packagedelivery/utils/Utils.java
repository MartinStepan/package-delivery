package bsc.packagedelivery.utils;

import bsc.packagedelivery.exceptions.IncorrectInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Some helpful utils for handling input string
 */
@Component
public class Utils {

    @Value("${package.weight.decimal.places}")
    protected int weightDecimalPlaces;

    @Value("${package.post.code.digits}")
    protected int postCodeDigits;

    public double findAndReturnWeight(String inputLine) throws NumberFormatException, IncorrectInputException {
        String sWeight = inputLine.substring(0, inputLine.indexOf(" ")).trim();
        if(sWeight.contains(".") && (sWeight.substring(sWeight.lastIndexOf(".")+1).length() > weightDecimalPlaces)) {
            throw new IncorrectInputException("Bad weight argument specified - contains more then "+ weightDecimalPlaces +" decimal places");
        }

        return Double.parseDouble(sWeight);
    }

    public String findAndReturnPostCode(String inputLine) throws IncorrectInputException {
        String postCode = inputLine.substring(inputLine.indexOf(" ")).trim();
        if(postCode.length() != postCodeDigits) {
            throw new IncorrectInputException("Post code must contain "+ postCodeDigits +" chars");
        }

        return postCode;
    }
}
