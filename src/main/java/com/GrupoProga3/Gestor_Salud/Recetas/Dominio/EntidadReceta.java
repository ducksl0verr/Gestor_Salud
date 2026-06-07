package com.GrupoProga3.Gestor_Salud.Recetas.Dominio;

import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.EntidadDetalleReceta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
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

    @OneToMany(mappedBy = "receta")
    private List<EntidadDetalleReceta> detalles;
}
