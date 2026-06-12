package com.GrupoProga3.Gestor_Salud.Pago;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface RepositorioPago extends JpaRepository<EntidadPago, Long> {

    List<EntidadPago> findByMonto(BigDecimal monto);

    List<EntidadPago> findByFecha(LocalDateTime fecha);

    List<EntidadPago> findByMontoAndFecha(
            BigDecimal monto,
            LocalDateTime fecha);

    boolean existsByMontoAndFecha(BigDecimal monto,  LocalDateTime fecha);
}
