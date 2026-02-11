package com.aluracursos.ForoHub;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EntityScan("com.aluracursos.ForoHub.modelo")
@EnableJpaRepositories("com.aluracursos.ForoHub.repositorio")
public class ForoHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoHubApplication.class, args);
	}

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void generarHash() {
        System.out.println(passwordEncoder.encode("123456"));
    }
}
