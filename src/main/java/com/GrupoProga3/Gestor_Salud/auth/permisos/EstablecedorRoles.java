package com.GrupoProga3.Gestor_Salud.auth.permisos;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class EstablecedorRoles implements CommandLineRunner {

    private final RepositorioRole repositorioRole;
    private final RepositorioPermiso repositorioPermiso;

    @Override
    public void run(String... args) throws Exception {
        if (repositorioRole.count()>0){
            return;
        }
        crearPermisos();

        repositorioRole.save(crearAdminsitrador());
        repositorioRole.save(crearAdministrativo());
        repositorioRole.save(crearProfesional());
    }

    public void crearPermisos(){
        for (PERMISOS permiso : PERMISOS.values()) {
            if (repositorioPermiso
                    .findByPermiso(permiso).isEmpty()){
                repositorioPermiso.save(
                        EntidadPermiso.builder()
                                .permiso(permiso)
                                .build()
                );
            }
        }
    }

    public EntidadRole crearAdminsitrador(){

        /// SE LE ASIGNA AL ADMINISTRADOR TO' LOS PERMISOS
        EntidadRole admin=
                new EntidadRole(ROLES.ROLE_ADMINISTRADOR);
        admin.getPermisos().addAll(repositorioPermiso.findAll());
        return admin;
    }

    public EntidadRole crearAdministrativo(){
        EntidadRole administrativo =
                new EntidadRole(ROLES.ROLE_ADMINISTRATIVO);

        administrativo.getPermisos().addAll(
                obtenerPermisos(
                        PERMISOS.CREAR_PACIENTE,
                        PERMISOS.VER_PACIENTE,
                        PERMISOS.ELIMINAR_PACIENTE,

                        PERMISOS.VER_HISTORIA_CLINICA,

                        PERMISOS.VER_TURNO,
                        PERMISOS.CREAR_TURNO,
                        PERMISOS.EDITAR_TURNO,
                        PERMISOS.CANCELAR_TURNO,

                        PERMISOS.VER_RECETA,

                        PERMISOS.VER_MEDICAMENTO,
                        PERMISOS.EDITAR_MEDICAMENTO,
                        PERMISOS.ELIMINAR_MEDICAMENTO,

                        PERMISOS.CREAR_FACTURA,
                        PERMISOS.VER_FACTURA,

                        PERMISOS.CREAR_PAGO,
                        PERMISOS.VER_PAGO,

                        PERMISOS.CREAR_PROVEEDOR,
                        PERMISOS.VER_PROVEEDOR,
                        PERMISOS.COMUNICAR_PROVEEDOR
                )
        );
        return administrativo;
    }

    public EntidadRole crearProfesional(){
        EntidadRole profesional=
                new EntidadRole(ROLES.ROLE_PROFESIONAL);
        profesional.getPermisos().addAll(
                obtenerPermisos(
                        PERMISOS.VER_PACIENTE,
                        PERMISOS.EDITAR_PACIENTE,

                        PERMISOS.VER_HISTORIA_CLINICA,
                        PERMISOS.CREAR_HISTORIA_CLINICA,
                        PERMISOS.EDITAR_HISTORIA_CLINICA,

                        PERMISOS.CREAR_DIAGNOSTICO,
                        PERMISOS.EDITAR_DIAGNOSTICO,

                        PERMISOS.CREAR_TRATAMIENTO,
                        PERMISOS.EDITAR_TRATAMIENTO,

                        PERMISOS.CREAR_RECETA,
                        PERMISOS.VER_RECETA,

                        PERMISOS.VER_MEDICAMENTO,

                        PERMISOS.VER_TURNO,
                        PERMISOS.EDITAR_TURNO,
                        PERMISOS.CANCELAR_TURNO
                )
        );
        return profesional;
    }

    private Set<EntidadPermiso> obtenerPermisos(PERMISOS... permiso){
        return Arrays.stream(permiso)
                .map(p->repositorioPermiso.findByPermiso(p)
                        .orElseThrow(()-> new RuntimeException(
                                "No se ha encontrado el permiso: "+ p)
    ))
        .collect(Collectors.toSet());
    }
}
