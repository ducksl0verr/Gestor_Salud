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
