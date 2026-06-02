package com.GrupoProga3.Gestor_Salud.DetalleFacturas.Model;
import com.GrupoProga3.Gestor_Salud.Facturas.Model.EntidadFacturas;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalles_facturas")
public class EntidadDetalleFacturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_factura")
    private EntidadFacturas factura;

    @ManyToOne
    @JoinColumn(name = "id_turno")
    private EntidadTurno turno;
    //

    @Column(name = "concepto",nullable = false)
    private String concepto;

    @Column(name = "importe", nullable = false, precision = 10, scale = 2)
    private BigDecimal importe;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

// ANOTACION: ES POSIBLE QUE DEBAMOS MEJORAR ESTE ATRIBUTO SUBTOTAL PARA QUE SE CALCULE AUTOMATICAMENTE EN LA ENTIDAD, VER...

    @Column(name = "subtotal",nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;
}
