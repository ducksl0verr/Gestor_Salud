package com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion;

public record RecuperacionSolicitadaEvent(String destinatario,
                                          String enlace,
                                          long minutosValidez) {
}
