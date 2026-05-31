package com.GrupoProga3.Gestor_Salud.Pacientes;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.ObraSocial.EntidadObraSocial;
import com.GrupoProga3.Gestor_Salud.Pago.EntidadPago;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    @NotBlank
    @Column(name = "nombre_paciente", nullable = false, length = 50)
    private String nombre;


    @NotBlank
    @Column(name = "apellido_paciente", nullable = false, length = 50)
    private String apellido;


    @NotBlank
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;


    //RELACIONES

    @ManyToOne
    @JoinColumn(name = "id_domicilio")
    private EntidadDomicilio domicilio;

    @OneToMany
    @JoinColumn(name="id_pago")
    private List<EntidadPago> pagos;


    @ManyToOne
    @JoinColumn(name = "id_obra_social")
    private EntidadObraSocial obraSocial;





















}
