package com.GrupoProga3.Gestor_Salud.Facturas.Model;
import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Model.EntidadDetalleFacturas;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.ENUMS.EstadoFactura;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @Column(name = "numero_factura",nullable = false,length = 50)
    private String numero_factura;
    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;
    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;
    @Column(name = "total",nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoFactura estadoFactura;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private EntidadPaciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_obra_social")
    private EntidadObraSocial obraSocial;

    //RELACION PARA QUE DESDE FACTURA SE PUEDA ACCEDER A LOS DETALLES DE LA FACTURA
    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL) // VER que hace a profundidad, basicamente guarda la factura y los detalles de la misma automaticamente
    private List<EntidadDetalleFacturas> detalles = new ArrayList<>();



















}
