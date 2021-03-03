package bsc.packagedelivery.facade;

public interface PackageDeliveryFacade {
    /**
     * Run file parser defined in program argument
     * args[0] = first argument is always file with packages delivery
     * args[1] = second argument is always file with fees per weight
     *
     * @param args
     */
    void runFileParser(String... args);

    /**
     * Run user input parser
     */
    void runUserInputParser();

    /**
     * Periodically print information about delivery packages - see @{@link bsc.packagedelivery.summary.printer.SummaryPrinter} for more info
     */
    void runPeriodicallyPrint();
}
