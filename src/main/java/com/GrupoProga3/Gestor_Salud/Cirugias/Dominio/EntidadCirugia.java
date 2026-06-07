package com.GrupoProga3.Gestor_Salud.Cirugias.Dominio;

import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.Enums.EstadoCirugia;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.EntidadQuirofano;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cirugias")
public class EntidadCirugia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoCirugia estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_paciente")
    private EntidadPaciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_quirofano")
    private EntidadQuirofano quirofano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_profesional")
    private EntidadUsuarios cirujano;
}
