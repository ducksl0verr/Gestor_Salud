package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO;

public record UsuarioDTO( String nombre,
         String apellido,
         String dni,
         String telefono,
         String matricula, // posible cambio debido a que puede ser null si no es profesional. VER
         String email){ // elimine el atributo de id_domicilioo FK, ver en usuarioMapperImpl.java si hay que cambiar, se encuentra en la carpeta target.
}




///El record te genera automáticamente casi todo lo que normalmente escribirías a mano en una clase DTO.
///
/// La diferencia importante es:
///
/// genera getters
/// genera toString()
/// genera equals() y hashCode()
/// genera constructor
/// NO genera setters
///
/// Porque los records son inmutables.
///
/// Entonces:
///
/// public record DomicilioDTO(String calle, String numero) {
/// }
///
/// Java automáticamente crea algo parecido a:
///
/// public final class DomicilioDTO {
///
///     private final String calle;
///     private final String numero;
///
///     public DomicilioDTO(String calle, String numero) {
///         this.calle = calle;
///         this.numero = numero;
///     }
///
///     public String calle() {
///         return calle;
///     }
///
///     public String numero() {
///         return numero;
///     }
///
///     @Override
///     public String toString() { ... }
///
///     @Override
///     public boolean equals(Object o) { ... }
///
///     @Override
///     public int hashCode() { ... }
/// }
///
/// Fijate que:
///
/// no hay setCalle()
/// no hay getCalle()
/// el getter es:
/// dto.calle()