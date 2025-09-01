# TFM – Microservicio Usuarios + Auth

API REST para gestión de **usuarios**, **roles** e **instrumentos**, con **JWT** para autenticación y autorización por **roles**.  
Tecnologías: Spring Boot, Spring Security (JWT), JPA, MySQL, Docker, GitHub Actions, Swagger/OpenAPI.

---

## 📦 Requisitos

- Java 21 (o 17 si ajustas el Dockerfile)
- Maven 3.9+
- Docker / Docker Compose
- (Opcional) Postman o cURL

---

## 🚀 Arranque local (sin Docker)

```bash
# 1) Exporta variables o configura application.yml
export SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/bandas_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export SPRING_DATASOURCE_USERNAME="bandas_user"
export SPRING_DATASOURCE_PASSWORD="bandas_pass"

# 2) Arranca la app
mvn spring-boot:run
```

> Asegúrate de tener MySQL disponible en `localhost:3306` o en su defecto en el puerto disponible que configures en `SPRING_DATASOURCE_URL`.
> El `DataInitializer` creará **roles**, **instrumentos** y **usuarios demo**.

---

## 🐳 Arranque con Docker Compose (recomendado)

`docker-compose.yml` levanta **MySQL** y la **app**:

```bash
docker compose up -d
docker compose logs -f usuarios   # ver logs de la app
docker compose down               # parar
```

- App: http://localhost:8080  
- MySQL: localhost:3306 (usuario: `bandas_user`, pass: `bandas_pass`, db: `bandas_db`)

---

## 🧱 Dockerfile (multi-stage)

- **Stage build**: compila con Maven (cachea dependencias).  
- **Stage runtime**: usa JRE ligera y ejecuta como **usuario no root**.

Comandos útiles:

```bash
# Construir imagen local
docker build -t usuarios:dev .

# Ejecutar imagen (apuntando a una DB externa)
docker run --rm -p 8080:8080   -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/bandas_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"   -e SPRING_DATASOURCE_USERNAME="bandas_user"   -e SPRING_DATASOURCE_PASSWORD="bandas_pass"   usuarios:dev
```

---

## 🔐 Seguridad

- **Login**: `POST /auth/login` → `{ "email", "password" }` → devuelve **JWT**.
- **Refresh**: `POST /auth/refresh` (con `Authorization: Bearer <token>`).
- Endpoints bajo `/api/**` protegidos.  
- Permisos:
  - `ADMIN`: CRUD completo de usuarios/roles/instrumentos.
  - `MUSICIAN`: lectura de catálogos (según configuración actual).

**Cabecera correcta**:

```
Authorization: Bearer <TU_TOKEN>
```

---

## 📖 Swagger / OpenAPI

- UI: http://localhost:8080/swagger-ui.html  
- JSON: http://localhost:8080/v3/api-docs

> Se permiten sin token en `SecurityConfig` con:
> `/swagger-ui.html`, `/swagger-ui/**`, `/v3/api-docs/**`, `/auth/**`.

---

## 📄 Paginación

Endpoints con `Pageable` aceptan:

- `page` (0..n)
- `size` (por defecto 10/20)
- `sort` (ej: `email,asc`)

Ejemplos:
```
GET /api/users?page=0&size=10&sort=email,asc
GET /api/instruments?page=1&size=5&sort=instrumentName,asc
```

Si usas `PaginatedResponse<T>`, la respuesta incluye:
```json
{
  "content": [ ... ],
  "page": 0,
  "size": 10,
  "totalElements": 20,
  "totalPages": 2,
  "first": true,
  "last": false
}
```

---

## 🧪 Colecciones Postman

- Colección **completa** de endpoints (auth, users, roles, instruments).  
- Colección de **pruebas negativas** (errores, validaciones, 401/403).  

> Importa las colecciones y crea una variable de entorno `jwt_token`.  
> 1) Lanza **/auth/login** → copia el token → pégalo en `jwt_token`.  
> 2) Ejecuta el resto de peticiones protegidas.

---

## 🗂 Estructura recomendada

```
src/main/java/com/tubanda/usuarios
├── auth/ (AuthController, JwtUtil, DTOs)
├── config/ (SecurityConfig, JwtFilter, ExceptionHandlers, OpenApiConfig, DataInitializer)
├── controller/ (UserController, RoleController, InstrumentController)
├── dto/ (UserDTO, UserCreateDTO, RoleDTO, InstrumentDTO, ...)
├── mapper/ (MapStruct mappers)
├── model/ (User, Role, Instrument)
├── repository/ (UserRepository, RoleRepository, InstrumentRepository)
└── service/
    ├── impl/ (UserServiceImpl, ...)
    └── (UserService, RoleService, InstrumentService)
```

---

## ⚙️ GitHub Actions

### CI – Build & Test (`.github/workflows/ci.yml`)
- Se ejecuta en cada **push/PR**.
- Instala JDK, cachea Maven, corre `mvn verify`.

### Docker Publish (`.github/workflows/docker-publish.yml`)
- Construye y **publica la imagen** al hacer **push a main** o crear **tag** `vX.Y.Z`.
- Publica en **Docker Hub** (si configuras `DOCKERHUB_USERNAME`/`DOCKERHUB_TOKEN`) o en **GHCR** por defecto.

**Secrets (si usas Docker Hub):**
- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN` (access token de Docker Hub)

**Tags automáticos**:
- `:main`, `:sha-<commit>`, y `:1.0.0` si subes tag `v1.0.0`.

---

## ▶️ Guía rápida de uso (cURL)

```bash
# 1) Login
curl -s -X POST http://localhost:8080/auth/login   -H "Content-Type: application/json"   -d '{"email":"test@bandas.com","password":"123456"}'

# 2) Lista usuarios (reemplaza <TOKEN>)
curl -s http://localhost:8080/api/users   -H "Authorization: Bearer <TOKEN>"

# 3) Crear usuario
curl -s -X POST http://localhost:8080/api/users   -H "Authorization: Bearer <TOKEN>"   -H "Content-Type: application/json"   -d '{
    "firstName":"Nuevo","lastName1":"Usuario","email":"nuevo@bandas.com",
    "password":"123456","roleIds":[2],"instrumentIds":[1]
  }'
```
