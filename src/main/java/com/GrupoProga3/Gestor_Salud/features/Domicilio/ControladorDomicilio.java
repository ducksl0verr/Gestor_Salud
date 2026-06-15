package com.GrupoProga3.Gestor_Salud.features.Domicilio;

import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/domicilios")
public class ControladorDomicilio {
    private final IServicioDomicilio servicioDomicilio;
    @GetMapping
    @PreAuthorize("hasAuthority('VER_DOMICILIO')")
    public ResponseEntity<List<DomicilioRespuesta>> buscarTodos (){
        return ResponseEntity.ok(servicioDomicilio.buscarTodos());
    }
    @GetMapping("/{idDomicilio}")
    @PreAuthorize("hasAuthority('VER_DOMICILIO')")
    public ResponseEntity<DomicilioRespuesta> buscarPorId (@PathVariable Long idDomicilio) {
        return  ResponseEntity.ok(servicioDomicilio.buscarPorId(idDomicilio));
    }
    /*@PostMapping          Pensadolo bien, realmente no es tan necesario un método http para CREAR un domicilio,
                            ni tampoco uno para borrar (por eso no lo agregue). Aunque simpre se puede chequear
    public ResponseEntity<DomicilioDTO> crearDomicilio (@RequestBody @Valid DomicilioDTO domicilioDTO) {
        return new ResponseEntity<>(servicioDomicilio.guardar(domicilioDTO), HttpStatus.CREATED);
    }
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_DOMICILIO')")
    public ResponseEntity<DomicilioRespuesta> actualizarDomicilio (@PathVariable Long id, @RequestBody @Valid DomicilioNuevo domicilioNuevo) {
        return ResponseEntity.ok(servicioDomicilio.actualizar(id, domicilioNuevo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ELIMINAR_DOMICILIO')")
    ResponseEntity<Void> eliminarDomicilio (@PathVariable Long idDomicilio) {
        servicioDomicilio.borrar(idDomicilio);
        return ResponseEntity.ok().build();
    }
}
