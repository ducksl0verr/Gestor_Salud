package com.GrupoProga3.Gestor_Salud.common.validacion;

import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Repositorio.RepositorioContacto;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.RepositorioProveedor;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Repositorio.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

/**
 * Validaciones de unicidad compartidas por todos los alta/edición que manejen
 * email, teléfono o DNI. Los contactos viven en una única tabla, así que
 * validar contra RepositorioContacto cubre usuarios, pacientes, proveedores y
 * contactos creados por separado.
 */
@Service
@RequiredArgsConstructor
public class ServicioValidacionUnicidad {

    private final RepositorioContacto repositorioContacto;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioProveedor repositorioProveedor;

    /**
     * Normaliza el contacto (en el mismo objeto) y verifica que no exista otro
     * con el mismo email o teléfono. idContactoExcluido se usa al editar.
     */
    public void normalizarYValidarContacto(EntidadContacto contacto, Long idContactoExcluido) {
        if (contacto == null) {
            return;
        }

        String email = normalizarEmail(contacto.getEmail());
        String telefono = normalizarTelefono(contacto.getTelefono());
        contacto.setEmail(email);
        contacto.setTelefono(telefono);

        if (email != null && !email.isBlank()) {
            boolean repetido = repositorioContacto.findAll().stream()
                    .anyMatch(otro -> !Objects.equals(idContactoExcluido, otro.getId())
                            && email.equals(normalizarEmail(otro.getEmail())));

            if (repetido) {
                throw new RecursoOcupadoException(
                        "Ya existe un contacto registrado con el email " + email + ".");
            }
        }

        if (telefono != null && !telefono.isBlank()) {
            boolean repetido = repositorioContacto.findAll().stream()
                    .anyMatch(otro -> !Objects.equals(idContactoExcluido, otro.getId())
                            && telefono.equals(normalizarTelefono(otro.getTelefono())));

            if (repetido) {
                throw new RecursoOcupadoException(
                        "Ya existe un contacto registrado con el teléfono " + telefono + ".");
            }
        }
    }

    /**
     * Normaliza y valida el DNI en usuarios y pacientes. Pasa el id del registro
     * que se está editando para no contarlo como duplicado.
     *
     * @return el DNI normalizado, listo para guardar.
     */
    public String normalizarYValidarDni(String dni, Long idUsuarioExcluido, Long idPacienteExcluido) {
        if (dni == null || dni.isBlank()) {
            return dni;
        }

        String normalizado = dni.trim();

        boolean enUsuarios = idUsuarioExcluido == null
                ? repositorioUsuario.existsByDni(normalizado)
                : repositorioUsuario.existsByDniAndIdNot(normalizado, idUsuarioExcluido);

        boolean enPacientes = idPacienteExcluido == null
                ? repositorioPaciente.existsByDni(normalizado)
                : repositorioPaciente.existsByDniAndIdNot(normalizado, idPacienteExcluido);

        if (enUsuarios || enPacientes) {
            throw new RecursoOcupadoException(
                    "Ya existe una persona registrada con el DNI " + normalizado + ".");
        }

        return normalizado;
    }

    /**
     * Los proveedores no tienen DNI, pero sí CUIL (también único en el sistema).
     */
    public String normalizarYValidarCuil(String cuil, Integer idProveedorExcluido) {
        if (cuil == null || cuil.isBlank()) {
            return cuil;
        }

        String normalizado = cuil.trim();

        boolean repetido = idProveedorExcluido == null
                ? repositorioProveedor.existsByCuil(normalizado)
                : repositorioProveedor.existsByCuilAndIdNot(normalizado, idProveedorExcluido);

        if (repetido) {
            throw new RecursoOcupadoException(
                    "Ya existe un proveedor registrado con el CUIL " + normalizado + ".");
        }

        return normalizado;
    }

    private String normalizarEmail(String email) {
        return email == null ? null : email.trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Para teléfonos se ignoran espacios, guiones, paréntesis y el signo +:
     * "(011) 1111-1111" y "+54 9 11 1111 1111" se comparan por sus dígitos.
     */
    private String normalizarTelefono(String telefono) {
        return telefono == null ? null : telefono.replaceAll("\\D", "");
    }
}
