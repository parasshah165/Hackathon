package com.ge.data.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ge.data.simulator.*"})
public class GarbageVanDataSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarbageVanDataSimulatorApplication.class, args);
	}
}
