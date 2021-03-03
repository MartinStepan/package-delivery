package bsc.packagedelivery.summary.printer;

public interface SummaryPrinter {
    /**
     * Periodically print information about delivered packages - total weight by post code, sorted by total weight
     */
    void periodicallyPrintTotalPackagesWeightByPostCode();

    /**
     * Shutdown executor service
     */
    void shutdownScheduledExecutorService();
}
