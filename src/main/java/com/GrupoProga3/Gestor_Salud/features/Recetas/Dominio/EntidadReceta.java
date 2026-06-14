package com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio;

import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.EntidadDetalleReceta;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="recetas")
public class EntidadReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name="id_paciente")
    private EntidadPaciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_profesional")
    private EntidadUsuarios profesional;

    @OneToMany(mappedBy = "receta",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<EntidadDetalleReceta> detalles;
}
