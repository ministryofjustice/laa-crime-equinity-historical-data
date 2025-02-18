package uk.gov.justice.laa.crime.equinity.historicaldata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EquinityHistoricalDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquinityHistoricalDataApplication.class, args);
	}

}
