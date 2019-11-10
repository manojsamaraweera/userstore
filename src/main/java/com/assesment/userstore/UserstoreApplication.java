package com.assesment.userstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UserstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserstoreApplication.class, args);
	}

}
