package com.dquinones;

import com.dquinones.repository.IAuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class DiscographyTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscographyTaskApplication.class, args);
	}

	@Autowired
	private IAuthorRepository authorRepository;

	@Bean
	CommandLineRunner init() {
		return args -> {
			authorRepository.findAll().forEach(auth -> {

			});
		};
	}

}
