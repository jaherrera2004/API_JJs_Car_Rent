# 🚗 JJ's Car Rent API

API REST para un sistema de renta de vehículos desarrollado con Spring Boot.

## 📋 Descripción

JJ's Car Rent es una aplicación web dedicada a la gestión y renta de automóviles. Esta API proporciona todos los endpoints necesarios para administrar vehículos, usuarios, reservas, marcas, modelos y facturación.

## 👤 Autor

**Juan Andrés Herrera**

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.3.4**
- **Spring Security** - Autenticación y autorización con JWT
- **Spring Data JPA** - Persistencia de datos
- **MySQL** - Base de datos
- **MapStruct 1.5.5** - Mapeo de objetos DTO
- **Lombok** - Reducción de código boilerplate
- **SpringDoc OpenAPI 2.6.0** - Documentación Swagger
- **JWT (JSON Web Tokens)** - Autenticación stateless
- **Maven** - Gestión de dependencias

## 📁 Estructura del Proyecto

```
src/main/java/com/JJsCarRent/
├── config/                 # Configuraciones (Security, Swagger, JWT)
├── controllers/            # Controladores REST
├── models/
│   ├── dto/               # Data Transfer Objects
│   ├── entity/            # Entidades de base de datos
│   ├── exceptions/        # Excepciones personalizadas
│   ├── request/           # Objetos de solicitud
│   └── response/          # Objetos de respuesta
├── repository/            # Repositorios de acceso a datos
├── services/              # Lógica de negocio
└── utils/                 # Utilidades y helpers
```

## 🔌 Endpoints Principales

### 🔐 Autenticación
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/auth` | Iniciar sesión |

### 👥 Usuarios
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/usuarios` | Registrar usuario |
| GET | `/api/v1/usuarios` | Obtener lista de usuarios |
| GET | `/api/v1/usuarios/{id}` | Obtener usuario por ID |
| PUT | `/api/v1/usuarios/{id}` | Activar usuario |
| DELETE | `/api/v1/usuarios/{id}` | Desactivar usuario |

### 🚙 Vehículos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/vehiculos` | Agregar vehículo |
| GET | `/api/v1/vehiculos` | Obtener lista de vehículos |
| GET | `/api/v1/vehiculos/fotos` | Obtener vehículos con fotos |
| GET | `/api/v1/vehiculos/disponibles` | Obtener vehículos disponibles |
| GET | `/api/v1/vehiculos/{id}` | Obtener vehículo por ID |
| PUT | `/api/v1/vehiculos/{id}` | Activar vehículo |
| DELETE | `/api/v1/vehiculos/{id}` | Desactivar vehículo |

### 📅 Reservas
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/reservas` | Crear reserva |
| GET | `/api/v1/reservas` | Obtener lista de reservas |
| GET | `/api/v1/reservas/usuario/{idUsuario}` | Obtener reservas por usuario |
| PUT | `/api/v1/reservas/{idReserva}/{idEstado}` | Actualizar estado de reserva |

### 🏭 Marcas
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/marcas` | Agregar marca |
| GET | `/api/v1/marcas` | Obtener lista de marcas |
| PUT | `/api/v1/marcas/{id}` | Activar marca |
| DELETE | `/api/v1/marcas/{id}` | Desactivar marca |

### 🚘 Modelos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/modelos` | Agregar modelo |
| GET | `/api/v1/modelos` | Obtener lista de modelos |
| GET | `/api/v1/modelos/{id}` | Obtener modelo por ID |
| PUT | `/api/v1/modelos/{id}` | Activar modelo |
| DELETE | `/api/v1/modelos/{id}` | Desactivar modelo |

### 🚐 Tipos de Vehículo
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/tipo-vehiculos` | Agregar tipo de vehículo |
| GET | `/api/v1/tipo-vehiculos` | Obtener lista de tipos |
| PUT | `/api/v1/tipo-vehiculos/{id}` | Activar tipo de vehículo |
| DELETE | `/api/v1/tipo-vehiculos/{id}` | Desactivar tipo de vehículo |

### 🧾 Facturas
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/facturas` | Obtener lista de facturas |

### 📊 Dashboard
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/dashboard` | Obtener datos del dashboard |

## ⚙️ Configuración

### Requisitos Previos

- Java 21 o superior
- MySQL 8.0 o superior
- Maven 3.8+

### Variables de Configuración

Configurar el archivo `application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/reservas_vehiculos
spring.datasource.username=root
spring.datasource.password=tu_contraseña

# JWT
jwt.secret.key=tu_clave_secreta

# Archivos
spring.servlet.multipart.max-file-size=3MB
spring.servlet.multipart.max-request-size=4MB
```

## 🚀 Instalación y Ejecución

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/jaherrera2004/API_JJs_Car_Rent.git
   cd API_JJs_Car_Rent
   ```

2. **Configurar la base de datos MySQL**
   ```sql
   CREATE DATABASE reservas_vehiculos;
   ```

3. **Configurar credenciales** en `application.properties`

4. **Ejecutar la aplicación**
   ```bash
   # Windows
   .\mvnw.cmd spring-boot:run
   
   # Linux/Mac
   ./mvnw spring-boot:run
   ```

5. **Acceder a la documentación Swagger**
   ```
   http://localhost:8080/swagger-ui.html
   ```

## 🔒 Seguridad

La API utiliza **JWT (JSON Web Tokens)** para la autenticación. Para acceder a los endpoints protegidos:

1. Obtener un token mediante el endpoint `/api/v1/auth`
2. Incluir el token en el header de las peticiones:
   ```
   Authorization: Bearer <tu_token>
   ```

### Endpoints Públicos (Sin autenticación)
- `POST /api/v1/auth` - Login
- `POST /api/v1/usuarios` - Registro
- `/swagger-ui/**` - Documentación
- `/v3/api-docs/**` - OpenAPI specs

## 📄 Licencia

Este proyecto es de uso académico/personal.

---

⭐ Si este proyecto te fue útil, ¡considera darle una estrella en GitHub!
