package com.GrupoProga3.Gestor_Salud.Pago;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "Pagos")
public class EntidadPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "El monto debe ser mayor a 0")
    @Column(name = "monto", nullable = false)
    private BigDecimal monto;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;


    @ManyToOne
    @JoinTable(name = "id_paciente",
    joinColumns =  @JoinColumn(name = "id_pago"),
    inverseJoinColumns = @JoinColumn(name = "id_paciente"))
    private List<EntidadPaciente> pacientes;




}
