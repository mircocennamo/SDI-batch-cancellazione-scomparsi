package it.interno.scomparsibatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScomparsiBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScomparsiBatchApplication.class, args);
	}
}
