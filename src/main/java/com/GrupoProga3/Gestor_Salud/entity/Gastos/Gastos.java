package com.GrupoProga3.Gestor_Salud.entity.Gastos;

import com.GrupoProga3.Gestor_Salud.entity.MetodosDePagos.MetodosDePagos;
import com.GrupoProga3.Gestor_Salud.entity.Proveedores.Proveedores;
import com.GrupoProga3.Gestor_Salud.entity.TipoGasto.TipoGasto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Gastos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gastos")
    private Long idGastos;

    @NotNull(message = "La fecha no puede ser nula")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    @Digits(integer = 12, fraction = 2, message = "El monto debe tener máximo 12 enteros y 2 decimales")
    @Column(name = "monto", nullable = false, precision = 14, scale = 2)
    private BigDecimal monto;

    @NotNull(message = "El tipo de gasto no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_gasto", nullable = false)
    private TipoGasto tipoGasto;

    @NotNull(message = "El método de pago no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodosDePagos metodoPago;

    @NotNull(message = "El proveedor no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provedor", nullable = false)
    private Proveedores provedor;

    @Size(max = 100, message = "El comprobante no puede superar los 100 caracteres")
    @Column(name = "comprobante", length = 100)
    private String comprobante;

    @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
    @Column(name = "observaciones", length = 500)
    private String observaciones;
}
