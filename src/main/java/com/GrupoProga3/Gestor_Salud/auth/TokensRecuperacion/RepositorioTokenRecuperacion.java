package com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion;

import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RepositorioTokenRecuperacion extends JpaRepository<EntidadTokenRecuperacion, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from EntidadTokenRecuperacion t where t.tokenHash = :tokenHash")
    Optional<EntidadTokenRecuperacion> findByTokenHashForUpdate(@Param("tokenHash") String tokenHash);

    long countByUsuarioAndCreadoEnAfter(EntidadUsuarios usuario, LocalDateTime desde);

    @Modifying
    @Query("update EntidadTokenRecuperacion t set t.usado = true " +
            "where t.usuario = :usuario and t.usado = false")
    void invalidarPendientes(@Param("usuario") EntidadUsuarios usuario);

    @Modifying
    @Query("delete from EntidadTokenRecuperacion t where t.vencimiento < :limite")
    void borrarVencidosAntesDe(@Param("limite") LocalDateTime limite);

    @Modifying
    @Query("delete from EntidadTokenRecuperacion t where t.usuario = :usuario")
    void borrarPorUsuario(@Param("usuario") EntidadUsuarios usuario);
}
