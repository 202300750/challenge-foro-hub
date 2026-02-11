📌 Descripción del Proyecto

Un foro es un espacio donde los usuarios pueden realizar preguntas, compartir conocimientos y colaborar entre sí. En Alura, este sistema es esencial para resolver dudas sobre cursos y proyectos.

ForoHub replica el funcionamiento interno de un foro desde el lado del Back End, permitiendo la gestión completa de los tópicos mediante una API REST desarrollada con Spring Boot.

🎯 Objetivo

Desarrollar una API REST que permita:

Crear un nuevo tópico

Mostrar todos los tópicos

Mostrar un tópico específico

Actualizar un tópico

Eliminar un tópico

Esto se conoce como un sistema CRUD (Create, Read, Update, Delete).

⚙️ Funcionalidades Implementadas

✔ API REST siguiendo las mejores prácticas del modelo REST
✔ Persistencia de datos con Spring Data JPA + MySQL
✔ Validaciones según reglas de negocio
✔ Autenticación y autorización con JWT (JSON Web Token)
✔ Protección de rutas mediante Spring Security
✔ Migraciones de base de datos con Flyway
✔ Manejo de excepciones y códigos de estado HTTP

🛠️ Tecnologías Utilizadas

Java 17+

Spring Boot 3

Spring Web

Spring Security

Spring Data JPA

JWT (JSON Web Token)

MySQL

Flyway

Maven

Hibernate

Lombok

🗂️ Modelo de Funcionalidad

La API se centra en la gestión de tópicos, los cuales permiten:

Asociación con usuarios

Registro de fechas

Estado del tópico

Edición controlada

Eliminación lógica o física

🔐 Autenticación y Seguridad

El sistema implementa autenticación con JWT, lo que permite:

Iniciar sesión mediante:

POST /login


Recibir un token JWT.

Enviar el token en cada petición protegida mediante el header:

Authorization: Bearer <TOKEN>


Validación automática del token mediante filtros de seguridad.

📡 Endpoints Principales
🔑 Autenticación
POST /login

🧵 Tópicos (requieren JWT)
POST   /topicos
GET    /topicos
GET    /topicos/{id}
PUT    /topicos/{id}
DELETE /topicos/{id}

🗄️ Base de Datos

Motor: MySQL

Migraciones automáticas con Flyway

Configuración en application.properties

▶️ Ejecución del Proyecto

Clonar el repositorio:

git clone https://github.com/tu-usuario/foro-hub.git


Configurar la base de datos en application.properties.

Ejecutar el proyecto desde IntelliJ o usando:

mvn spring-boot:run


Acceder a la API desde:

http://localhost:8080

🧪 Pruebas

Puedes probar la API usando:

Postman

Insomnia

Thunder Client (VS Code)

📌 Organización del Proyecto

El proyecto sigue una arquitectura clara y ordenada:

controller → Controladores REST

service → Lógica de negocio

repository → Acceso a datos

domain → Entidades

security → Configuración JWT y filtros

infra → Manejo de excepciones y configuraciones

📈 Metodología de Trabajo

Se utilizó un enfoque ágil, apoyado con Trello, organizando las tareas en:

Listos para iniciar

En desarrollo

Pausado

Concluido

👨‍💻 Autor

Nelson Ramirez
Proyecto desarrollado como parte del programa Oracle Next Education - Alura LATAM

🏁 Conclusión

Este proyecto demuestra la implementación completa de una API REST profesional, con seguridad, arquitectura limpia, buenas prácticas y control de acceso.
