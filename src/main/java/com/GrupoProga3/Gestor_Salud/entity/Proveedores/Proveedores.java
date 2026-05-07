package com.GrupoProga3.Gestor_Salud.entity.Proveedores;

import com.GrupoProga3.Gestor_Salud.entity.TipoProvedor.TipoProvedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "Proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provedor")
    private Long idProvedor;

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 150, message = "El email no puede superar los 150 caracteres")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "El CUIL no puede estar vacío")
    @Pattern(regexp = "\\d{11}", message = "El CUIL debe contener exactamente 11 dígitos numéricos")
    @Column(name = "cuil", nullable = false, unique = true, length = 11)
    private String cuil;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(min = 5, max = 255, message = "La dirección debe tener entre 5 y 255 caracteres")
    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @NotNull(message = "El tipo de proveedor no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_provedor", nullable = false)
    private TipoProvedor tipoProvedor;
}
