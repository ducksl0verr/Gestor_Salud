package com.GrupoProga3.Gestor_Salud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestorSaludApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestorSaludApplication.class, args);
        System.out.println("Hola =)");
    }

}
