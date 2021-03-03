package bsc.packagedelivery.facade;

public interface PackageDeliveryFacade {
    /**
     * Run file parser defined in program argument
     * @param args
     */
    void runFileParser(String... args);

    /**
     * Run user input parser
     */
    void runCommandLineParser();

    /**
     * Periodically print information about delivery packages - see @{@link bsc.packagedelivery.summary.printer.SummaryPrinter} for more info
     */
    void runPeriodicallyPrint();
}
