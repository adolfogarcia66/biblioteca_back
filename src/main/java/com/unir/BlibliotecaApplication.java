package com.unir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class BlibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlibliotecaApplication.class, args);
	}

}
