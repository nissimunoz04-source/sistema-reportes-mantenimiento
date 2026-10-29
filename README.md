Sistema de Reportes de Mantenimiento
Sistema web desarrollado para el Colegio Cristiano Mixto Bilingüe Mi Dulce Paraíso, ubicado en Puerto Barrios, Izabal, Guatemala.
1. Descripción del proyecto
El Sistema de Reportes de Mantenimiento es una aplicación web diseñada para registrar, consultar y administrar las necesidades de mantenimiento de las instalaciones y los equipos del establecimiento educativo.

Su propósito es facilitar la organización de los reportes, mejorar el seguimiento de los problemas identificados y mantener un registro digital de las solicitudes de mantenimiento.

El proyecto utiliza una arquitectura organizada en capas y una base de datos para almacenar la información.
2. Funcionalidades principales
- Inicio de sesión con autenticación.
- Control de acceso mediante roles.
- Registro de nuevos reportes de mantenimiento.
- Consulta del listado de reportes.
- Edición de reportes existentes.
- Eliminación de reportes según los permisos del usuario.
- Validación de campos obligatorios.
- Mensajes de validación en español.
- Búsqueda y filtrado de reportes.
- Consulta del estado y prioridad de los reportes.
- Seguimiento e historial de mantenimiento.
- Impresión de reportes.
- Exportación de información a Excel.
3. Tecnologías utilizadas
Tecnología	Función
Java 21	Lenguaje de programación
Spring Boot 4.1.1	Framework principal
Spring MVC	Organización de controladores y vistas
Spring Data JPA	Acceso y persistencia de datos
Spring Security	Autenticación y autorización
Maven	Gestión y construcción del proyecto
Thymeleaf	Plantillas HTML dinámicas
HTML y CSS	Estructura y diseño de la interfaz
JavaScript	Interactividad
H2	Base de datos de desarrollo
PostgreSQL (Neon)	Base de datos de producción
Git y GitHub	Control de versiones
Render	Publicación de la aplicación4. Modelo de datos
La entidad principal del sistema es ReporteMantenimiento.

Contiene los siguientes campos:
Campo	Tipo de dato	Descripción
id	Long	Identificador único del reporte
titulo	String	Nombre del problema
descripcion	String	Descripción del problema
ubicacion	String	Lugar donde se presenta
estado	String	Estado actual del reporte
fechaRegistro	LocalDateTime	Fecha y hora del registro
fechaResolucion	LocalDateTime	Fecha y hora de resolución
reportadoPor	String	Persona que realiza el reporte
prioridad	String	Prioridad del mantenimiento
tipoMantenimiento	String	Mantenimiento preventivo o correctivo
observaciones	String	Información adicionalLos campos obligatorios cuentan con validaciones para evitar registros incompletos.
5. Estados y clasificaciones
Estados del reporte:
- Pendiente.
- En revisión.
- Resuelto.
Prioridades:
- Baja.
- Media.
- Alta.
Tipos de mantenimiento:
- Preventivo.
- Correctivo.
6. Seguridad y roles
El sistema utiliza Spring Security para controlar el acceso a las funcionalidades.
ADMIN
El administrador puede consultar, registrar, editar y eliminar reportes de mantenimiento.
PERSONAL
El personal tiene acceso limitado a las funcionalidades de consulta autorizadas.

Las operaciones administrativas están protegidas mediante restricciones de acceso.

Además, la aplicación incorpora:
- Autenticación de usuarios.
- Contraseñas almacenadas de forma cifrada.
- Protección CSRF.
- Control de acceso por roles.
- Validación de datos.
- Comunicación HTTPS en producción.
- Configuración sensible mediante variables de entorno.
Las contraseñas y credenciales reales no se incluyen en este repositorio.
7. Bases de datos
Desarrollo
Durante el desarrollo se utiliza H2 para almacenar los registros de mantenimiento.
Producción
La aplicación publicada utiliza PostgreSQL mediante Neon.

Esto permite conservar los reportes cuando el servicio de Render se reinicia o vuelve a desplegarse.

Las credenciales de conexión se administran mediante variables de entorno.
8. Configuración para ejecución local
Requisitos
- Java 21.
- Visual Studio Code o un entorno compatible.
- Acceso a una terminal.
- Maven Wrapper incluido en el proyecto.
Variables de entorno
Antes de ejecutar el sistema, deben configurarse las siguientes variables:
- ADMIN_PASSWORD: contraseña del administrador.
- PERSONAL_PASSWORD: contraseña del usuario de personal.
Para producción también se utilizan:
- SPRING_PROFILES_ACTIVE: perfil de ejecución.
- DB_URL: dirección de conexión a PostgreSQL.
- DB_USER: usuario de la base de datos.
- DB_PASSWORD: contraseña de la base de datos.
Importante: Nunca publicar contraseñas reales ni cadenas de conexión con credenciales.
Ejecutar la aplicación
Abrir una terminal en la carpeta principal del proyecto y ejecutar:
.\mvnw.cmd spring-boot:run
Cuando la aplicación termine de iniciar, abrir:

http://localhost:8080

Para iniciar sesión deben utilizarse las credenciales configuradas en el entorno.
9. Ejecutar pruebas
Para ejecutar las pruebas automatizadas:
.\mvnw.cmd test
Las pruebas utilizan una base de datos H2 temporal en memoria, configurada exclusivamente para el entorno de pruebas.
10. Aplicación publicada
URL pública:

https://sistema-reportes-mantenimiento.onrender.com

La aplicación se encuentra desplegada en Render y utiliza HTTPS.

La publicación se realiza desde la rama principal main del repositorio.
11. Organización del proyecto
El sistema utiliza una arquitectura organizada por capas:
- controller: recibe y procesa las solicitudes HTTP.
- service: contiene la lógica de negocio.
- model: define las entidades del sistema.
- repository: administra el acceso a la base de datos.
- security: contiene la configuración de autenticación y autorización.
- templates: contiene las vistas dinámicas.
- static: contiene los recursos estáticos.
- src/test: contiene las pruebas automatizadas.
12. Control de versiones
El proyecto utiliza Git y GitHub para administrar los cambios.

Ramas principales:
- development: desarrollo y preparación de cambios.
- main: versión utilizada para el despliegue en producción.
13. Institución beneficiada
Colegio Cristiano Mixto Bilingüe Mi Dulce Paraíso

Puerto Barrios, Izabal, Guatemala.
14. Autores
- Maggie Nissi Mía Muñoz Salguero
- Sergio Orellana
Proyecto académico desarrollado para el curso de Programación I.
15. Estado del proyecto
El sistema cuenta con una versión funcional publicada en Render.

Durante la Semana 15 se actualizaron los campos de la entidad, las validaciones, el formulario, el listado y las pruebas de funcionamiento.

También se verificó la persistencia de los reportes después de reiniciar el servicio publicado.