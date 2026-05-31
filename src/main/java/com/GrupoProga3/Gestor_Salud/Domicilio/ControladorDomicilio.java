package com.GrupoProga3.Gestor_Salud.Domicilio;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.Mappers.DTO.DomicilioDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/domicilios")
public class ControladorDomicilio {
    private final IServicioDomicilio servicioDomicilio;
    @GetMapping
    public ResponseEntity<List<DomicilioDTO>> buscarTodos (){
        return ResponseEntity.ok(servicioDomicilio.buscarTodos());
    }
    @GetMapping("/{idDomicilio}")
    public ResponseEntity<DomicilioDTO> buscarPorId (@PathVariable Long idDomicilio) {
        return  ResponseEntity.ok(servicioDomicilio.buscarPorId(idDomicilio));
    }
    /*@PostMapping          Pensadolo bien, realmente no es tan necesario un método http para CREAR un domicilio,
                            ni tampoco uno para borrar (por eso no lo agregue). Aunque simpre se puede chequear
    public ResponseEntity<DomicilioDTO> crearDomicilio (@RequestBody @Valid DomicilioDTO domicilioDTO) {
        return new ResponseEntity<>(servicioDomicilio.guardar(domicilioDTO), HttpStatus.CREATED);
    }
     */
    @PutMapping("/{id}")
    public ResponseEntity<DomicilioDTO> actualizarDomicilio (@PathVariable Long id, @RequestBody @Valid DomicilioDTO domicilioDTO) {
        return ResponseEntity.ok(servicioDomicilio.actualizar(id,domicilioDTO));
    }
}
