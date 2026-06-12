package com.GrupoProga3.Gestor_Salud.Proveedores.Dominio;

import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.Proveedores.Enums.TIPO_PROVEEDOR;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="proveedores")
public class EntidadProveedor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    private EntidadContacto contacto;

    @Column(unique = true, length = 11)
    private String cuil;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_proveedor")
    private List<EntidadDomicilio> direccion;

    @Enumerated(EnumType.STRING)
    private TIPO_PROVEEDOR tipo;
}
