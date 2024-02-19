package com.escorre.escorrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.escorre.escorrega.repositories")
public class EscorregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscorregaApplication.class, args);
	}

}
