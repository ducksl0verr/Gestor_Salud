package com.GrupoProga3.Gestor_Salud.features.Notificaciones;

import com.GrupoProga3.Gestor_Salud.features.Medicamentos.ServicioMedicamento;
import com.GrupoProga3.Gestor_Salud.features.Turno.ServicioTurno;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordatorioScheduler {

    private final ServicioTurno servicioTurno;
    private final ServicioMedicamento servicioMedicamento;

    @Scheduled(cron = "0 0 9 * * *")
    public void ejecutarRecordatorio(){
        servicioTurno.enviarRecordatorio();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void limpiarTurnosVencidos(){
        servicioTurno.finalizarTurnosViejos();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void limpiarMedicamentos(){
        servicioMedicamento.eliminarVencidos();
    }
}
