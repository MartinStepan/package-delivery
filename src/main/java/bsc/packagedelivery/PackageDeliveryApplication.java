package bsc.packagedelivery;

import bsc.packagedelivery.facade.PackageDeliveryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PackageDeliveryApplication implements CommandLineRunner {

	@Autowired
	PackageDeliveryFacade packageDeliveryFacade;

	public static void main(String[] args) {
		SpringApplication.run(PackageDeliveryApplication.class, args);
	}

	@Override
	public void run(String... args) {
		packageDeliveryFacade.runPeriodicallyPrint();
		packageDeliveryFacade.runFileParser(args);
		packageDeliveryFacade.runCommandLineParser();
	}
}
