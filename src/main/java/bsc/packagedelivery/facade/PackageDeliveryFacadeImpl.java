package bsc.packagedelivery.facade;

import bsc.packagedelivery.input.parser.FeeParser;
import bsc.packagedelivery.input.parser.PackageLineParser;
import bsc.packagedelivery.summary.printer.SummaryPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PackageDeliveryFacadeImpl implements PackageDeliveryFacade {

    static final Logger log = LoggerFactory.getLogger(PackageDeliveryFacadeImpl.class);

    @Qualifier("packageUserInputParser")
    @Autowired
    private PackageLineParser userInputParser;

    @Qualifier("packageFileParser")
    @Autowired
    private PackageLineParser fileParser;

    @Qualifier("feeParser")
    @Autowired
    private FeeParser feeParser;

    @Autowired
    private SummaryPrinter packageByWeightSummaryPrinter;

    final Scanner scanner = new Scanner(System.in);

    public void runFileParser(String... args) {
        if(args.length > 0) {
            if(args.length > 1) {
                try {
                    log.debug("args[1] argument: {}", args[1]);
                    feeParser.parseInput(args[1]);
                }
                catch (Exception e) {
                    log.warn(e.toString());
                }
            }

            try {
                log.debug("args[0] argument: {}", args[0]);
                fileParser.parseInput(args[0]);
            }
            catch (Exception e) {
                log.warn(e.toString());
            }
        }
    }

    public void runUserInputParser() {

        while(true) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                log.debug("User input: {}", input);
                if(input.equalsIgnoreCase("quit")) {
                    packageByWeightSummaryPrinter.shutdownScheduledExecutorService();
                    return;
                }
                try {
                    userInputParser.parseInput(input);
                }
                catch (Exception e) {
                    log.warn(e.toString());
                }
            }
        }
    }

    public void runPeriodicallyPrint() {
        packageByWeightSummaryPrinter.periodicallyPrintTotalPackagesWeightByPostCode();
    }
}
