package com.GrupoProga3.Gestor_Salud.Domicilio.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Domicilios")
public class EntidadDomicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=50, nullable=false)
    private String calle;
    @Column(length=50, nullable=false)
    private String numero;
    @Column(length=50, nullable=false)
    private String piso;
    @Column(length=50, nullable=false)
    private String depto;
    @Column(length=50, nullable=false)
    private String localidad;
    @Column(length=50, nullable=false)
    private String provincia;
    @Column(length=50, nullable=false)
    private String codigo_postal;
    @Column(length=50, nullable=false)
    private String pais;

}
