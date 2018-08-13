package br.com.algartelecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class SpringAPIApplication {

    private static Logger LOGGER = Logger.getLogger(SpringAPIApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(SpringAPIApplication.class, args);
	}
}
