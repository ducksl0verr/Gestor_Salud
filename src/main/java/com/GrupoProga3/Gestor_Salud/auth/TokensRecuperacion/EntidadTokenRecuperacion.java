package com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion;

import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "tokens_recuperacion",
        indexes = @Index(name = "idx_tokens_recuperacion_usuario_creado",
                columnList = "id_usuario, creado_en"))
@Getter @Setter
public class EntidadTokenRecuperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="token_hash", nullable = false, unique = true)
    private String tokenHash;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private EntidadUsuarios usuario;

    @Column(nullable = false)
    private LocalDateTime creadoEn;

    @Column(nullable = false)
    private LocalDateTime vencimiento;

    @Column(nullable = false)
    private boolean usado = false;


}
