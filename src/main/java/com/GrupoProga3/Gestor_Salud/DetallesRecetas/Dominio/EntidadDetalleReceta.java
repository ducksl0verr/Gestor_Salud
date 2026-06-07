package com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio;

import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.EntidadMedicamento;
import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.EntidadReceta;
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
@Table(name="detallesRecetas")
public class EntidadDetalleReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dosis;

    private String frecuencia;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="id_receta")
    private EntidadReceta receta;

    @ManyToOne
    @JoinColumn(name="id_medicamento")
    private EntidadMedicamento medicamento;
}
