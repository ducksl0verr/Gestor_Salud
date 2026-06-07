package com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio;

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
@Table(name="medicamentos")
public class EntidadMedicamento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String principioActivo;

    private String laboratorio;

    private String descripcion;

    private Integer stock;

    private Double precio;

    private LocalDate fechaVencimiento;


    public void descontarStock(Integer cantidad){
        if (stock<cantidad){
            throw new IllegalArgumentException("El stock es insuficiente");
        }
        stock-=cantidad;
    }
}
