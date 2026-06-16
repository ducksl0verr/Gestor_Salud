🏥 Gestor de Salud

Proyecto académico desarrollado para la materia Programación III utilizando Spring Boot, JPA, MySQL y tecnologías modernas para el desarrollo de APIs REST.

Sistema de gestión médica desarrollado en Java utilizando Spring Boot. La aplicación permite administrar pacientes, turnos, historias clínicas, tratamientos, recetas, medicamentos, cirugías, internaciones, obras sociales, facturación y usuarios, centralizando la información clínica y administrativa de una institución médica.

📌 Tabla de Contenidos
Descripción
Características
Tecnologías Utilizadas
Arquitectura
Modelo del Sistema
Módulos Principales
Instalación
Documentación API
Seguridad
Futuras Mejoras
Objetivos Académicos
Autores
Estado del Proyecto


📖 Descripción
Gestor de Salud es una API REST diseñada para facilitar la administración integral de centros médicos y consultorios.

El sistema permite:

Registrar pacientes.
Gestionar obras sociales.
Programar y administrar turnos.
Mantener historias clínicas.
Registrar diagnósticos.
Gestionar tratamientos médicos.
Emitir recetas.
Administrar medicamentos.
Programar cirugías.
Gestionar quirófanos y salas de internación.
Generar facturas y registrar pagos.
Enviar notificaciones.
Administrar usuarios y roles del sistema.


✨ Características

✔ Arquitectura multicapa.

✔ API REST siguiendo buenas prácticas de desarrollo.

✔ Persistencia de datos mediante JPA/Hibernate.

✔ DTOs para transferencia segura de información.

✔ Mapeo automático mediante MapStruct.

✔ Validaciones utilizando Bean Validation.

✔ Documentación automática mediante Swagger/OpenAPI.

✔ Integración con correo electrónico.

✔ Gestión de relaciones complejas entre entidades.

✔ Seguridad mediante Spring Security y JWT.

✔ Control de acceso basado en roles.

✔ Separación clara entre lógica de negocio y acceso a datos.



🚀 Tecnologías Utilizadas
Java 21
Spring Boot
Spring Data JPA
Hibernate
MySQL
Maven
Lombok
MapStruct
Spring Validation
Spring Mail
Swagger / OpenAPI
Spring Security
JWT (JSON Web Tokens)


🏗️ Arquitectura

El proyecto sigue una arquitectura en capas para garantizar escalabilidad, mantenibilidad y separación de responsabilidades.

┌──────────────────┐
│   Controllers    │
└────────┬─────────┘
         │
┌────────▼─────────┐
│     Services     │
└────────┬─────────┘
         │
┌────────▼─────────┐
│   Repositories   │
└────────┬─────────┘
         │
┌────────▼─────────┐
│      MySQL       │
└──────────────────┘

Además, se emplea una capa de DTOs y mappers para desacoplar las entidades del modelo de persistencia de las respuestas expuestas por la API.

DTO ↔ MapStruct ↔ Entidad
🗄️ Modelo del Sistema
Gestión Médica
Paciente
Historia Clínica
Diagnóstico
Tratamiento
Receta
Medicamento
Gestión Administrativa
Obra Social
Factura
Pago
Método de Pago
Gestión Operativa
Turno
Cirugía
Quirófano
Sala de Internación
Notificación
Seguridad y Usuarios
Usuario
Rol
Autenticación JWT


📂 Módulos Principales

👤 Pacientes
Registro de pacientes.
Actualización de información personal.
Asociación con obras sociales.
Consulta de historial médico.

🏥 Obras Sociales
Gestión de obras sociales.
Administración de coberturas.
Asociación con pacientes.

📅 Turnos
Solicitud y programación de turnos.
Modificación y cancelación.
Seguimiento del estado de atención.

📋 Historia Clínica
Registro de antecedentes médicos.
Consulta de evolución clínica.
Asociación con diagnósticos y tratamientos.

🩺 Diagnósticos
Registro y seguimiento de diagnósticos médicos.

💊 Medicamentos y Recetas
Gestión de medicamentos.
Emisión de recetas médicas.
Asociación entre recetas y medicamentos.

📝 Tratamientos
Registro de tratamientos.
Seguimiento de evolución.
Asociación con diagnósticos.

🔪 Cirugías
Programación de cirugías.
Gestión de intervenciones.
Administración de quirófanos.

🛏️ Internación
Administración de salas.
Gestión de pacientes internados.

💰 Facturación
Generación de facturas.
Registro de pagos.
Seguimiento de movimientos económicos.

📢 Notificaciones
Envío de mensajes y avisos.
Notificaciones automáticas de eventos relevantes.

👥 Usuarios y Roles
Administración de usuarios.
Asignación de roles.
Gestión de permisos.
Control de acceso a funcionalidades.

# ⚙️ Instalación

## 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
```

## 2. Ingresar al proyecto

```bash
cd Gestor_Salud
```

## 3. Configurar la base de datos

Editar el archivo:

```text
src/main/resources/application.properties
```

Configurando los parámetros correspondientes:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<NOMBRE_BD>
spring.datasource.username=<USUARIO>
spring.datasource.password=<CONTRASEÑA>
```

## 4. Compilar el proyecto

```bash
mvn clean install
```

## 5. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

📚 Documentación de la API

La API cuenta con documentación interactiva generada automáticamente mediante Swagger/OpenAPI.

Una vez iniciada la aplicación, la documentación estará disponible en:

http://localhost:8080/swagger-ui/index.html

Desde Swagger UI es posible:

Consultar todos los endpoints disponibles.
Visualizar los modelos de datos (DTOs).
Probar solicitudes directamente desde el navegador.
Ver los códigos de respuesta y posibles errores.
Validar el funcionamiento de los endpoints protegidos mediante JWT.

🔗 Endpoints Principales
🔐 Autenticación
POST /api/auth/iniciar → Autenticar usuario y generar tokens JWT.
POST /api/auth/refresh → Renovar el token de acceso utilizando un refresh token válido.
POST /api/auth/registrar → Registrar un nuevo usuario en el sistema.
👤 Pacientes
GET /api/pacientes → Obtener el listado de pacientes registrados.
GET /api/pacientes/{id} → Obtener los datos de un paciente específico.
POST /api/pacientes → Registrar un nuevo paciente.
PUT /api/pacientes/{id} → Actualizar la información de un paciente.
DELETE /api/pacientes/{id} → Eliminar un paciente del sistema.
🏥 Obras Sociales
GET /api/obras-sociales → Obtener todas las obras sociales registradas.
GET /api/obras-sociales/{id} → Obtener una obra social por ID.
POST /api/obras-sociales → Registrar una nueva obra social.
PUT /api/obras-sociales/{id} → Actualizar información de una obra social.
DELETE /api/obras-sociales/{id} → Eliminar una obra social.
📅 Turnos
GET /api/turnos → Obtener todos los turnos registrados.
GET /api/turnos/{id} → Obtener un turno específico.
POST /api/turnos → Programar un nuevo turno médico.
PUT /api/turnos/{id} → Modificar fecha o estado de un turno.
DELETE /api/turnos/{id} → Cancelar un turno.
GET /api/turnos/pacientes/{id}/turnos-facturables → Obtener los turnos pendientes de facturación de un paciente.
📋 Historias Clínicas
GET /api/historiasClinicas → Obtener todas las historias clínicas.
GET /api/historiasClinicas/{id} → Consultar una historia clínica específica.
POST /api/historiasClinicas → Crear una nueva historia clínica.
PUT /api/historiasClinicas/{id} → Actualizar observaciones o evolución clínica.
🩺 Diagnósticos
GET /api/diagnosticos → Obtener todos los diagnósticos registrados.
GET /api/diagnosticos/{id} → Consultar un diagnóstico específico.
POST /api/diagnosticos → Registrar un nuevo diagnóstico.
💊 Medicamentos
GET /api/medicamentos → Obtener el catálogo de medicamentos.
GET /api/medicamentos/{id} → Consultar un medicamento específico.
POST /api/medicamentos → Registrar un nuevo medicamento.
PUT /api/medicamentos/{id} → Actualizar información o stock de un medicamento.
GET /api/medicamentos/principioActivo/{principio} → Buscar medicamentos por principio activo.
📝 Tratamientos y Prescripciones
GET /api/tratamientos → Obtener todos los tratamientos disponibles.
POST /api/tratamientos → Registrar un nuevo tratamiento.
GET /api/prescripciones → Consultar las prescripciones registradas.
POST /api/prescripciones → Asignar un tratamiento a un paciente.
PUT /api/prescripciones/{id}/baja → Finalizar o desactivar una prescripción.
💉 Recetas
GET /api/recetas → Obtener todas las recetas emitidas.
GET /api/recetas/{id} → Consultar una receta específica.
GET /api/recetas/pacientes/{id} → Obtener las recetas asociadas a un paciente.
POST /api/recetas → Emitir una nueva receta médica.
🔪 Cirugías y Quirófanos
GET /api/cirugias → Obtener todas las cirugías programadas.
POST /api/cirugias → Programar una nueva cirugía.
PUT /api/cirugias/{id} → Actualizar datos o estado de una cirugía.
DELETE /api/cirugias/{id} → Cancelar una cirugía programada.
GET /api/quirofanos → Obtener los quirófanos disponibles.
POST /api/quirofanos → Registrar un nuevo quirófano.
🛏️ Internaciones
GET /api/salasInternaciones → Obtener las salas de internación disponibles.
POST /api/salasInternaciones → Registrar una nueva sala de internación.
PUT /api/salasInternaciones/{idSala}/internar/{idPaciente} → Asignar un paciente a una sala de internación.
💰 Facturación y Pagos
GET /api/facturas → Obtener todas las facturas emitidas.
GET /api/facturas/{id} → Consultar una factura específica.
POST /api/facturas → Generar una nueva factura.
GET /api/pagos → Obtener todos los pagos registrados.
POST /api/pagos → Registrar un nuevo pago.
👥 Usuarios y Roles
GET /api/usuarios → Obtener todos los usuarios del sistema.
GET /api/usuarios/{id} → Consultar un usuario específico.
POST /api/usuarios → Registrar un nuevo usuario.
PUT /api/usuarios/{id} → Actualizar información de un usuario.
DELETE /api/usuarios/{id} → Eliminar un usuario.
GET /api/usuarios/profesionales → Obtener el listado de profesionales médicos.
POST /api/usuarios/profesionales → Registrar un nuevo profesional médico.
📢 Notificaciones
POST /api/usuarios/{id}/mensaje → Enviar una notificación o mensaje a un usuario.

La documentación completa e interactiva de la API se encuentra disponible en Swagger UI, donde pueden consultarse todos los endpoints, parámetros, respuestas, ejemplos y modelos de datos.

🔐 Seguridad

El sistema incorpora mecanismos de autenticación y autorización utilizando Spring Security y JWT (JSON Web Tokens).

Características principales:

Autenticación mediante tokens JWT.
Control de acceso basado en roles.
Protección de endpoints sensibles.
Gestión segura de credenciales.
Restricción de operaciones según permisos asignados.

📈 Futuras Mejoras
Dashboard administrativo con métricas del sistema.
Generación y exportación de reportes PDF.
Estadísticas médicas y financieras.
Auditoría de acciones realizadas por usuarios.
Integración con aplicaciones móviles.
Optimización de rendimiento y consultas complejas.
Sistema avanzado de recordatorios y notificaciones.


🎓 Objetivos Académicos

Este proyecto fue desarrollado con el objetivo de aplicar conocimientos relacionados con:

Programación Orientada a Objetos.
Desarrollo Backend.
Arquitectura en Capas.
APIs REST.
Spring Boot.
Persistencia con JPA/Hibernate.
Bases de Datos Relacionales.
DTOs y Mappers.
Validaciones.
Seguridad de aplicaciones.
Documentación de APIs.


👨‍💻 Autores
Grupo Proga III
-Sanchez Madrid, Dante Valentino
-Leccese, Miguel Angel
-Ferrarello, Maximiliano

Proyecto académico desarrollado para la materia Programación III.

📄 Licencia

Proyecto desarrollado con fines educativos y académicos.

🚧 Estado del Proyecto

En desarrollo activo.

Las funcionalidades principales se encuentran implementadas y el sistema continúa evolucionando mediante la incorporación de nuevas características y mejoras arquitectónicas.
