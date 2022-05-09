package com.jbklenterpirse.petsGoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class PetsGoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsGoAppApplication.class, args);
	}


}
