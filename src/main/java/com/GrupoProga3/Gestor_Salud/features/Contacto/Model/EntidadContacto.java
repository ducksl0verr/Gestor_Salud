package com.GrupoProga3.Gestor_Salud.features.Contacto.Model;

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

        @Column(nullable = false, length = 100)
        private String email;

        @Column(name = "telefono", nullable = false, length = 20)
        private String telefono;

    }



