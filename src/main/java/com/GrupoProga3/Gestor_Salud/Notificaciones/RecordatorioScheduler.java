package com.GrupoProga3.Gestor_Salud.Notificaciones;

import com.GrupoProga3.Gestor_Salud.Turno.ServicioTurno;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordatorioScheduler {

    private final ServicioTurno servicioTurno;

    @Scheduled(cron = "0 0 9 * * *")
    public void ejecutarRecordatorio(){
        servicioTurno.enviarRecordatorio();
    }

}
