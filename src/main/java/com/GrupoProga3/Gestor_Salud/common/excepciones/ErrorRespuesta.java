package com.GrupoProga3.Gestor_Salud.common.excepciones;

import java.time.LocalDateTime;

public record ErrorRespuesta(LocalDateTime momento,
                             Integer estatus,
                             String error,
                             String mensaje,
                             String path) {
}
