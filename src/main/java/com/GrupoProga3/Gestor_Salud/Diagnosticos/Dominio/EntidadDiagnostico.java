package com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio;

import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.EntidadHistoriaClinica;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="diagnosticos")
public class EntidadDiagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;

    private LocalDate fechaDiagnostico;

    @ManyToOne
    @JoinColumn(name="id_historiaClinica")
    private EntidadHistoriaClinica historiaClinica;

    @ManyToOne
    @JoinColumn(name="id_profesional")
    private EntidadUsuarios profesional;
}
