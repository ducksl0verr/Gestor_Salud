package com.GrupoProga3.Gestor_Salud;

import com.GrupoProga3.Gestor_Salud.Notificaciones.ServicioEmail;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestorSaludApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestorSaludApplication.class, args);

       /* ConfigurableApplicationContext context = SpringApplication.run(GestorSaludApplication.class, args);

        ServicioEmail email = context.getBean(ServicioEmail.class);
        email.enviarMail("paciente@gmail.com",
                "TURNO",
                "mamahuevo");


        */
    }

}
