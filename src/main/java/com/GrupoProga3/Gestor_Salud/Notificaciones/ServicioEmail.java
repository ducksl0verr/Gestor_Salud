package com.GrupoProga3.Gestor_Salud.Notificaciones;

import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicioEmail {

    private final JavaMailSender mailSender;

    public void enviarRecordatorioTurno (EntidadPaciente paciente, EntidadTurno turno) {
        String asunto ="RECORDATORIO DE TURNO";
        String mensaje = paciente.getNombre().toUpperCase() + " "+
                paciente.getApellido().toUpperCase() + ", se le recuerda que mañana tiene un turno a las: "+turno.getFechaHora();

        enviarMail(paciente.getContacto().getEmail(), asunto, mensaje);
    }

    public void enviarMail (String destinatario, String asunto, String mensaje) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("gestor@salud.com");
        mail.setTo(destinatario);
        mail.setSubject(asunto);
        mail.setText(mensaje);

        mailSender.send(mail);
    }

}
