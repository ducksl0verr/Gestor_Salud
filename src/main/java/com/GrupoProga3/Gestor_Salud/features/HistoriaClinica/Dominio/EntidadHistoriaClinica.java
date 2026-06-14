package com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio;

import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.EntidadDiagnostico;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Historias_Clinicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntidadHistoriaClinica {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_historiaClinica")
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    @Column(length = 100, nullable = false)
    private String observaciones;
    @Column(length = 100, nullable = false)
    private String evolucion;
    /// Lo mismo, una vez que tengamos las clases se instancian acá, La relación es que recibe muchos
    @OneToOne
    @JoinColumn(name = "id_paciente")
    private EntidadPaciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_profesional")
    private EntidadUsuarios profesional;

    @OneToMany(mappedBy = "historiaClinica")
    private List<EntidadDiagnostico> diagnosticos;
}
