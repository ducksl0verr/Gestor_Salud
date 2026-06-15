package com.GrupoProga3.Gestor_Salud.auth.permisos;

import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
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
        crearPermisos();
        if(repositorioRole.count()==0){
        repositorioRole.save(crearAdminsitrador());
        repositorioRole.save(crearAdministrativo());
        repositorioRole.save(crearProfesional());}
        else{
            actualizarRolesConPermisosNuevos();
        }
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
                        PERMISOS.VER_PROFESIONAL,
                        PERMISOS.CREAR_PACIENTE,
                        PERMISOS.VER_PACIENTE,
                        PERMISOS.ELIMINAR_PACIENTE,

                        PERMISOS.VER_OBRA_SOCIAL,
                        PERMISOS.EDITAR_OBRA_SOCIAL,
                        PERMISOS.CREAR_OBRA_SOCIAL,

                        PERMISOS.VER_HISTORIA_CLINICA,

                        PERMISOS.VER_TURNO_FACTURABLE,
                        PERMISOS.VER_TURNO,
                        PERMISOS.CREAR_TURNO,
                        PERMISOS.EDITAR_TURNO,
                        PERMISOS.CANCELAR_TURNO,

                        PERMISOS.VER_RECETA,

                        PERMISOS.VER_MEDICAMENTO,
                        PERMISOS.EDITAR_MEDICAMENTO,
                        PERMISOS.ELIMINAR_MEDICAMENTO,

                        PERMISOS.VER_DIAGNOSTICO,

                        PERMISOS.CREAR_FACTURA,
                        PERMISOS.VER_FACTURA,

                        PERMISOS.CREAR_PAGO,
                        PERMISOS.VER_PAGO,

                        PERMISOS.VER_GASTO,
                        PERMISOS.CREAR_GASTO,
                        PERMISOS.EDITAR_GASTO,

                        PERMISOS.CREAR_PROVEEDOR,
                        PERMISOS.VER_PROVEEDOR,
                        PERMISOS.COMUNICAR_PROVEEDOR,

                        PERMISOS.VER_SALA,
                        PERMISOS.EDITAR_SALA,

                        PERMISOS.VER_DOMICILIO,
                        PERMISOS.EDITAR_DOMICILIO,
                        PERMISOS.ELIMINAR_DOMICILIO,

                        PERMISOS.VER_CONTACTO,
                        PERMISOS.CREAR_CONTACTO,
                        PERMISOS.EDITAR_CONTACTO,
                        PERMISOS.ELIMINAR_CONTACTO,

                        PERMISOS.VER_CIRUGIA
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
                        PERMISOS.INTERNAR_PACIENTE,

                        PERMISOS.VER_HISTORIA_CLINICA,
                        PERMISOS.CREAR_HISTORIA_CLINICA,
                        PERMISOS.EDITAR_HISTORIA_CLINICA,

                        PERMISOS.CREAR_DIAGNOSTICO,
                        PERMISOS.EDITAR_DIAGNOSTICO,
                        PERMISOS.VER_DIAGNOSTICO,

                        PERMISOS.CREAR_TRATAMIENTO,
                        PERMISOS.EDITAR_TRATAMIENTO,
                        PERMISOS.VER_TRATAMIENTO,
                        PERMISOS.ASIGNAR_TRATAMIENTO,

                        PERMISOS.CREAR_RECETA,
                        PERMISOS.VER_RECETA,

                        PERMISOS.VER_MEDICAMENTO,

                        PERMISOS.VER_TURNO,
                        PERMISOS.EDITAR_TURNO,
                        PERMISOS.CANCELAR_TURNO,

                        PERMISOS.VER_SALA,

                        PERMISOS.CREAR_CIRUGIA,
                        PERMISOS.EDITAR_CIRUGIA,
                        PERMISOS.VER_CIRUGIA
                )
        );
        return profesional;
    }

    private Set<EntidadPermiso> obtenerPermisos(PERMISOS... permiso){
        return Arrays.stream(permiso)
                .map(p->repositorioPermiso.findByPermiso(p)
                        .orElseThrow(()-> new EntidadNoEncontradaException(
                                "Permiso",
                                p.name(),
                                null,
                                "No se ha encontrado el permiso"
                        )
    ))
        .collect(Collectors.toSet());
    }

    @Transactional
    public void actualizarRolesConPermisosNuevos() {
        EntidadRole admin = repositorioRole.findByRole(ROLES.ROLE_ADMINISTRADOR)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Rol",
                        "Administrador no encontrado",
                        null,
                        "No se ha encontrado el rol"));
        admin.getPermisos().addAll(repositorioPermiso.findAll());
        repositorioRole.save(admin);


        EntidadRole administrativo = repositorioRole.findByRole(ROLES.ROLE_ADMINISTRATIVO)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Rol",
                        "Administrativo no encontrado",
                        null,
                        "No se ha encontrado el rol"));
        administrativo.getPermisos().addAll(
                obtenerPermisos(
                        PERMISOS.VER_PROFESIONAL,
                        PERMISOS.CREAR_PACIENTE,
                        PERMISOS.VER_PACIENTE,
                        PERMISOS.ELIMINAR_PACIENTE,

                        PERMISOS.VER_OBRA_SOCIAL,
                        PERMISOS.EDITAR_OBRA_SOCIAL,
                        PERMISOS.CREAR_OBRA_SOCIAL,

                        PERMISOS.VER_HISTORIA_CLINICA,

                        PERMISOS.VER_TURNO_FACTURABLE,
                        PERMISOS.VER_TURNO,
                        PERMISOS.CREAR_TURNO,
                        PERMISOS.EDITAR_TURNO,
                        PERMISOS.CANCELAR_TURNO,

                        PERMISOS.VER_RECETA,

                        PERMISOS.VER_MEDICAMENTO,
                        PERMISOS.EDITAR_MEDICAMENTO,
                        PERMISOS.ELIMINAR_MEDICAMENTO,

                        PERMISOS.VER_DIAGNOSTICO,

                        PERMISOS.CREAR_FACTURA,
                        PERMISOS.VER_FACTURA,

                        PERMISOS.CREAR_PAGO,
                        PERMISOS.VER_PAGO,

                        PERMISOS.VER_GASTO,
                        PERMISOS.CREAR_GASTO,
                        PERMISOS.EDITAR_GASTO,

                        PERMISOS.CREAR_PROVEEDOR,
                        PERMISOS.VER_PROVEEDOR,
                        PERMISOS.COMUNICAR_PROVEEDOR,

                        PERMISOS.VER_SALA,
                        PERMISOS.EDITAR_SALA,

                        PERMISOS.VER_DOMICILIO,
                        PERMISOS.EDITAR_DOMICILIO,
                        PERMISOS.ELIMINAR_DOMICILIO,

                        PERMISOS.VER_CONTACTO,
                        PERMISOS.CREAR_CONTACTO,
                        PERMISOS.EDITAR_CONTACTO,
                        PERMISOS.ELIMINAR_CONTACTO,

                        PERMISOS.VER_CIRUGIA
                )
        );
        repositorioRole.save(administrativo);


        EntidadRole profesional = repositorioRole.findByRole(ROLES.ROLE_PROFESIONAL)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Rol",
                        "Profesional no encontrado",
                        null,
                        "No se ha encontrado el rol"));
        profesional.getPermisos().addAll(
                obtenerPermisos(
                        PERMISOS.VER_PACIENTE,
                        PERMISOS.EDITAR_PACIENTE,
                        PERMISOS.INTERNAR_PACIENTE,

                        PERMISOS.VER_HISTORIA_CLINICA,
                        PERMISOS.CREAR_HISTORIA_CLINICA,
                        PERMISOS.EDITAR_HISTORIA_CLINICA,

                        PERMISOS.CREAR_DIAGNOSTICO,
                        PERMISOS.EDITAR_DIAGNOSTICO,
                        PERMISOS.VER_DIAGNOSTICO,

                        PERMISOS.CREAR_TRATAMIENTO,
                        PERMISOS.EDITAR_TRATAMIENTO,
                        PERMISOS.VER_TRATAMIENTO,
                        PERMISOS.ASIGNAR_TRATAMIENTO,

                        PERMISOS.CREAR_RECETA,
                        PERMISOS.VER_RECETA,

                        PERMISOS.VER_MEDICAMENTO,

                        PERMISOS.VER_TURNO,
                        PERMISOS.EDITAR_TURNO,
                        PERMISOS.CANCELAR_TURNO,

                        PERMISOS.VER_SALA,

                        PERMISOS.CREAR_CIRUGIA,
                        PERMISOS.EDITAR_CIRUGIA,
                        PERMISOS.VER_CIRUGIA
                )
        );
        repositorioRole.save(profesional);
    }


}
