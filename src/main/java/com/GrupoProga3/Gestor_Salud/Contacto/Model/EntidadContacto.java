package com.GrupoProga3.Gestor_Salud.Contacto.Model;


import com.GrupoProga3.Gestor_Salud.Pacientes.EntidadPaciente;
import jakarta.persistence.*;
import lombok.*;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    @Entity
    @Table(name = "contactos")
    public class EntidadContacto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nombre", nullable = false, length = 100)
        private String nombre;


        @Column(name = "apellido", nullable = false, length = 100)
        private String apellido;


        @Column(name = "telefono", nullable = false, length = 20)
        private String telefono;

        @OneToOne
        @JoinColumn (name = "id_paciente")
        private EntidadPaciente idpaciente;


    }



