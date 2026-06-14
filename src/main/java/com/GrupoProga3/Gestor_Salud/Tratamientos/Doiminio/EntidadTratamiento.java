package com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio;

import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.Dominio.EntidadPrescripcionTratamiento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tratamientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntidadTratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @OneToMany(mappedBy = "tratamientos")
    private List<EntidadPrescripcionTratamiento> pacientes;
}
