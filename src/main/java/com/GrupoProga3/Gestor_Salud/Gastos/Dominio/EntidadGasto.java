package com.GrupoProga3.Gestor_Salud.Gastos.Dominio;

import com.GrupoProga3.Gestor_Salud.Gastos.Enums.TIPO_GASTO;
import com.GrupoProga3.Gestor_Salud.Metodos_de_Pago.METODOS_PAGO;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.EntidadProveedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="gastos")
public class EntidadGasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String descripcion;

    private Double monto;

    @Enumerated(EnumType.STRING)
    private TIPO_GASTO tipoGasto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private EntidadProveedor proveedor;

    @Enumerated(EnumType.STRING)
    private METODOS_PAGO metodoPago;

    private String observaciones;
}
