package com.aluracursos.ForoHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.aluracursos.ForoHub.modelo")
@EnableJpaRepositories("com.aluracursos.ForoHub.repositorio")
public class ForoHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoHubApplication.class, args);
	}

}
