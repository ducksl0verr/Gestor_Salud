package com.GrupoProga3.Gestor_Salud.Pacientes.Model;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.EntidadPrescripcionTratamiento;
import com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.EntidadSalaInternacion;
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
@Table(name = "pacientes")
public class EntidadPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_paciente", nullable = false, length = 50)
    private String nombre;
    @Column(name = "apellido_paciente", nullable = false, length = 50)
    private String apellido;
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fecha_nacimiento;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_domicilio")
    private EntidadDomicilio domicilio;

    @ManyToOne
    @JoinColumn(name = "id_obra_social")
    private EntidadObraSocial obraSocial;

    /// Esto lo agregó Dante, si algo no anda ya saben de quién es la culpa
    @OneToMany(mappedBy = "pacientes")
    private List<EntidadPrescripcionTratamiento> tratamientos;

    @ManyToOne
    @JoinColumn(name="id_salaInternacion")
    private EntidadSalaInternacion salaInternacion;
}
