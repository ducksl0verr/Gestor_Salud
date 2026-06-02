package com.GrupoProga3.Gestor_Salud.Facturas;

import com.GrupoProga3.Gestor_Salud.DetalleFacturas.EntidadDetalleFacturas;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import com.GrupoProga3.Gestor_Salud.Pacientes.EntidadPaciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facturas")
public class EntidadFacturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "El numero de factura no puede estar vacio")
    @Column(name = "numero_factura",nullable = false,length = 50)
    private String numero_factura;


    @NotNull(message = "La fecha de emisión no puede ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision",nullable = false)
    private Date fecha_emision;



    @NotNull(message = "La fecha de vencimiento no puede ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_vencimiento",nullable = false)
    private Date fecha_vencimiento;


    @NotNull(message = "El total no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "total",nullable = false, precision = 10, scale = 2)
    private BigDecimal total;


    @NotBlank(message = "El estado no puede estar vacio")
    @Column(name = "estado",nullable = false,length = 50)
    private String estado;


    //RELACIONES

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private EntidadPaciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_obra_social")
    private EntidadObraSocial obraSocial;



    //RELACION PARA QUE DESDE FACTURA SE PUEDA ACCEDER A LOS DETALLES DE LA FACTURA

    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL) // VER que hace a profundidad, basicamente guarda la factura y los detalles de la misma automaticamente
    private List<EntidadDetalleFacturas> detalles = new ArrayList<>();;



















}
