package bsc.packagedelivery.summary.printer;

import bsc.packagedelivery.model.PackageDeliveryStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.Map.Entry.comparingByValue;

@Component
public class SummaryPrinterImpl implements SummaryPrinter {

    static final Logger log = LoggerFactory.getLogger(SummaryPrinterImpl.class);

    @Value("${summary.printer.init.delay}")
    private int initDelay;

    @Value("${summary.printer.period}")
    private long period;

    @Value("${summary.printer.thread.pool.size}")
    private int threadPoolSize;

    @Autowired
    private PackageDeliveryStorage packageStorage;

    private ScheduledExecutorService executorService;
    private Runnable scheduledTask;

    public SummaryPrinterImpl() {
        this.scheduledTask = () ->
            packageStorage.getPackagesByPostCode().entrySet().stream().sorted(Collections.reverseOrder(comparingByValue())).forEach(entry ->
                log.info("{} {}", entry.getKey(), entry.getValue())
            );
        log.debug("Scheduled task created");
    }

    @Override
    public void periodicallyPrintTotalPackagesWeightByPostCode() {
        executorService = Executors.newScheduledThreadPool(threadPoolSize);
        executorService.scheduleAtFixedRate(scheduledTask, initDelay, period, TimeUnit.SECONDS);
        log.debug("Scheduled task scheduled, init delay: {} seconds, period: {} seconds", initDelay, period);
    }

    public void shutdownScheduledExecutorService() {
        executorService.shutdown();
        log.debug("Scheduled task shutted down");
    }
}
