# Sistema de Reportes de Mantenimiento

Proyecto desarrollado para el Colegio Cristiano Mixto Bilingüe Mi Dulce Paraíso.

## Descripción

Este proyecto consiste en un Sistema de Reportes de Mantenimiento que permitirá organizar y gestionar los reportes relacionados con las necesidades de mantenimiento de la institución.

Actualmente el proyecto cuenta con una estructura organizada en capas: modelo, servicio y controlador.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.1.1
- Maven
- HTML
- JavaScript
- Git
- GitHub

## Cómo ejecutar el proyecto

1. Abrir el proyecto en Visual Studio Code.
2. Abrir una terminal dentro de la carpeta del proyecto.
3. Ejecutar el siguiente comando:

`.\mvnw.cmd spring-boot:run`

4. Esperar hasta que aparezca el mensaje `Started SistemaReportesMantenimientoApplication`.
5. Abrir en el navegador `http://localhost:8080`.

## Endpoints

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/saludo` | Muestra un saludo general de bienvenida |
| GET | `/api/bienvenida?nombre=Mia` | Muestra un saludo personalizado utilizando el nombre recibido |

## Estructura del proyecto

El proyecto utiliza una estructura organizada por capas:

- `controller`: recibe las solicitudes del usuario.
- `service`: contiene la lógica de negocio.
- `model`: contiene los datos utilizados por la aplicación.
- `static`: contiene el formulario web.

## Institución

Colegio Cristiano Mixto Bilingüe Mi Dulce Paraíso

## Autores

- Mia Muñoz
- Sergio Orellana