# User App Básico

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)](https://spring.io/projects/spring-boot) [![Maven](https://img.shields.io/badge/Maven-3.8.5-red)](https://maven.apache.org/) [![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

---

## 🚀 Descripción

**User App Básico** es una API REST sencilla para gestionar usuarios. Construida con Spring Boot, Spring Data JPA y MySQL, permite realizar operaciones CRUD sobre una entidad `User` con los campos:

- `id` (Long)
- `nombre` (String)
- `email` (String)

---

## ✨ Características

- API REST con endpoints para **GET**, **POST**, **PUT**, **PATCH** y **DELETE**
- Persistencia con Spring Data JPA y MySQL
- Respuestas con códigos HTTP adecuados (200, 201, 204, 404)
- CORS habilitado para desarrollo frontend
- Recarga en caliente con DevTools
- Monitoreo de salud con Actuator

---

## 🛠️ Tecnologías

| Herramienta     | Versión |
| --------------- | ------- |
| Java            | 17      |
| Spring Boot     | 3.x     |
| Spring Data JPA | 3.x     |
| MySQL           | 8.x     |
| Maven           | 3.8.5   |

---

## 🔧 Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior
- MySQL 8.x (o similar)

---

## 📝 Configuración

> **Nota:** Antes de ejecutar, crea la base de datos `usersdb` en MySQL o ajusta el nombre de la base de datos en `application.properties` según tu configuración.

Renombra o crea `src/main/resources/application.properties` con tus credenciales de MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/usersdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# DevTools hot reload
spring.devtools.restart.enabled=true

# Actuator endpoints
management.endpoints.web.exposure.include=health,info
```

---

## ▶️ Ejecución

1. Clona este repositorio
   ```bash
   git clone https://github.com/cifp-villa-aguimes/user-app-basic.git
   ```
2. Entra al directorio del proyecto
   ```bash
   cd user-app-basic
   ```
3. Compila y ejecuta con Maven
   ```bash
   mvn clean spring-boot:run
   ```
4. La API estará disponible en `http://localhost:8080`

---

## 📖 Endpoints

| Método | Ruta                 | Descripción                        | Código de respuesta  |
| ------ | -------------------- | ---------------------------------- | -------------------- |
| GET    | `/api/v1/users`      | Obtener todos los usuarios         | 200 OK               |
| GET    | `/api/v1/users/{id}` | Obtener usuario por ID             | 200 OK / 404         |
| POST   | `/api/v1/users`      | Crear un nuevo usuario             | 201 Created          |
| PUT    | `/api/v1/users/{id}` | Reemplazar un usuario existente    | 200 OK / 404         |
| PATCH  | `/api/v1/users/{id}` | Actualizar parcialmente un usuario | 200 OK / 404         |
| DELETE | `/api/v1/users/{id}` | Eliminar un usuario                | 204 No Content / 404 |

---

## 📋 Ejemplos con cURL

- **Listar usuarios**
  ```bash
  curl -X GET http://localhost:8080/api/v1/users
  ```
- **Crear usuario**
  ```bash
  curl -X POST \
       -H "Content-Type: application/json" \
       -d '{"nombre":"Ana","email":"ana@mail.com"}' \
       http://localhost:8080/api/v1/users
  ```
- **Actualizar usuario**
  ```bash
  curl -X PUT \
       -H "Content-Type: application/json" \
       -d '{"nombre":"Ana María","email":"ana.maria@mail.com"}' \
       http://localhost:8080/api/v1/users/1
  ```
- **Parchear usuario**
  ```bash
  curl -X PATCH \
       -H "Content-Type: application/json" \
       -d '{"email":"nuevo@mail.com"}' \
       http://localhost:8080/api/v1/users/1
  ```
- **Eliminar usuario**
  ```bash
  curl -X DELETE http://localhost:8080/api/v1/users/1
  ```

---

## 📘 Colección Postman

Puedes probar la API fácilmente usando [Postman](https://www.postman.com/), una herramienta popular para testear APIs REST.

- Abre Postman.
- Configura el método HTTP, los encabezados (`Content-Type: application/json`) y el cuerpo de la petición según el endpoint que quieras probar.
- Ejecuta las solicitudes y revisa las respuestas directamente desde Postman.

> **Nota:** Asegúrate de que la API esté en ejecución (`http://localhost:8080`) antes de realizar las pruebas.

---

## 📝 Licencia

Este proyecto está bajo la licencia [MIT](LICENSE).
