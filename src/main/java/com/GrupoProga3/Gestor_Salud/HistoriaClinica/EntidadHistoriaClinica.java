package com.GrupoProga3.Gestor_Salud.HistoriaClinica;

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
    private int id_historiaClinica;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @NotBlank
    private String observaciones;
    @NotBlank
    private String evolucion;
    /// Lo mismo, una vez que tengamos las clases se instancian acá, La relación es que recibe muchos
    private int id_paciente;
    private int id_profesional;
}
