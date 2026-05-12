package com.GrupoProga3.Gestor_Salud.HistoriaClinica;

import com.GrupoProga3.Gestor_Salud.Pacientes.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Usuarios.EntidadUsuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Historias_Clinicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadHistoriaClinica {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_historiaClinica;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @NotBlank
    @Column(length = 100, nullable = false)
    private String observaciones;
    @NotBlank
    @Column(length = 100, nullable = false)
    private String evolucion;
    /// Lo mismo, una vez que tengamos las clases se instancian acá, La relación es que recibe muchos
    @ManyToOne
    @JoinColumn(name = "id_paciente_id")
    private EntidadPaciente id_paciente;
    @ManyToOne
    @JoinColumn(name = "id_profesional_id")
    private EntidadUsuarios id_profesional;
}
