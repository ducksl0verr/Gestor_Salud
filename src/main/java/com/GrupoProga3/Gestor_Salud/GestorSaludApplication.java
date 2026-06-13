package com.GrupoProga3.Gestor_Salud;

import com.GrupoProga3.Gestor_Salud.Notificaciones.ServicioEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestorSaludApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorSaludApplication.class, args);
	}

}
