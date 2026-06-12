package com.GrupoProga3.Gestor_Salud.Contacto.Controlador;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.Contacto.Servicio.IServicioContacto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactos")
@RequiredArgsConstructor
public class ContactoControlador {

    private final IServicioContacto servicioContacto;

    @PostMapping
    public ResponseEntity<ContactoRespuesta> guardar(
            @Valid @RequestBody ContactoNuevo contactoNuevo) {

        ContactoRespuesta respuesta =
                servicioContacto.guardar(contactoNuevo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoRespuesta> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ContactoNuevo contactoNuevo) {

        ContactoRespuesta respuesta =
                servicioContacto.actualizar(id, contactoNuevo);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {

        servicioContacto.borrar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoRespuesta> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                servicioContacto.buscarPorId(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<ContactoRespuesta>> buscarTodos() {

        return ResponseEntity.ok(
                servicioContacto.buscarTodos()
        );
    }

   /* @GetMapping("/buscar")
    public ResponseEntity<List<ContactoRespuesta>> buscarContacto(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido) {

        return ResponseEntity.ok(
                servicioContacto.buscarContacto(nombre, apellido)
        );
    }

    */
}