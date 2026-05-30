package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio;

import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

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
    private Long id_historiaClinica;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(length = 100, nullable = false)
    private String observaciones;
    @Column(length = 100, nullable = false)
    private String evolucion;
    /// Lo mismo, una vez que tengamos las clases se instancian acá, La relación es que recibe muchos
    @OneToOne
    @JoinColumn(name = "id_paciente")
    private EntidadPaciente id_paciente;

    @ManyToOne
    @JoinColumn(name = "id_profesional")
    private EntidadUsuarios id_profesional;
}
