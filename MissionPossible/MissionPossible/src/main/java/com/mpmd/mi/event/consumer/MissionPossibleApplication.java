package com.mpmd.mi.event.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MissionPossibleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MissionPossibleApplication.class, args);
	}

}
