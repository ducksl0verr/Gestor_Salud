package com.GrupoProga3.Gestor_Salud.entity.MetodosDePagos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "Metodos_De_Pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodosDePagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_de_pago")
    private Long idMetodoDePago;

    @NotBlank(message = "El nombre del método de pago no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;
}
