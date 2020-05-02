package com.homebookkeeper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@SpringBootApplication
public class HomebookkeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebookkeeperApplication.class, args);
	}

}
