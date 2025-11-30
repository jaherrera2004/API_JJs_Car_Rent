# 📖 JJ's Car Rent - Documentación de la API

## 📋 Información General

- **Título:** API JJ's Car Rent
- **Versión:** 1.0.0
- **Base URL:** `http://localhost:8080`
- **Descripción:** Aplicación web dedicada a la renta de automóviles

## 🔐 Autenticación

La API utiliza autenticación basada en **JWT (JSON Web Tokens)**.

### Cómo autenticarse:
1. Realiza una petición POST a `/api/v1/auth` con las credenciales
2. Obtén el `accessToken` de la respuesta
3. Incluye el token en todas las peticiones protegidas:
   ```
   Authorization: Bearer <accessToken>
   ```

### Endpoints Públicos (Sin autenticación requerida)
- `POST /api/v1/auth`
- `POST /api/v1/usuarios`
- `GET /swagger-ui/**`
- `GET /v3/api-docs/**`

---

## 🔑 Auth Controller

Base path: `/api/v1/auth`

### POST `/api/v1/auth` - Iniciar Sesión

Autentica un usuario y retorna un token JWT.

**🔓 Público:** Sí

**Request Body:**
```json
{
  "usuario": "string",      // Requerido - Username del usuario
  "contrasenia": "string"   // Requerido - Contraseña del usuario
}
```

**Response 200 OK:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "usuario": {
    "id": 1,
    "cedula": "1234567890",
    "email": "usuario@email.com",
    "username": "usuario123",
    "nombre": "Juan",
    "apellido": "Pérez",
    "telefono": "3001234567",
    "edad": 25,
    "rol": "ADMIN"
  }
}
```

---

## 👥 Usuario Controller

Base path: `/api/v1/usuarios`

### POST `/api/v1/usuarios` - Registrar Usuario

Registra un nuevo usuario en el sistema.

**🔓 Público:** Sí

**Request Body:**
```json
{
  "cedula": "string",       // Requerido - Solo números
  "nombre": "string",       // Requerido
  "apellido": "string",     // Requerido
  "username": "string",     // Requerido
  "edad": 18,               // Requerido - Mínimo 18 años
  "email": "string",        // Requerido
  "telefono": "string",     // Requerido
  "contrasenia": "string"   // Requerido - Mínimo 8 caracteres
}
```

**Response 200 OK:** Void (sin contenido)

**Validaciones:**
- `cedula`: Solo debe contener números
- `edad`: Debe ser mayor o igual a 18
- `contrasenia`: Mínimo 8 caracteres

---

### GET `/api/v1/usuarios` - Obtener Lista de Usuarios

Retorna la lista completa de usuarios registrados.

**🔒 Permiso requerido:** `usuario:obtener-lista-usuarios`

**Response 200 OK:**
```json
[
  {
    "id": 1,
    "cedula": "1234567890",
    "email": "usuario@email.com",
    "username": "usuario123",
    "nombre": "Juan",
    "apellido": "Pérez",
    "telefono": "3001234567",
    "edad": 25,
    "rol": "CLIENTE"
  }
]
```

---

### GET `/api/v1/usuarios/{id}` - Obtener Usuario por ID

Retorna los datos de un usuario específico.

**🔒 Permiso requerido:** `usuario:obtener-usuario`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del usuario |

**Response 200 OK:**
```json
{
  "id": 1,
  "cedula": "1234567890",
  "email": "usuario@email.com",
  "username": "usuario123",
  "nombre": "Juan",
  "apellido": "Pérez",
  "telefono": "3001234567",
  "edad": 25,
  "rol": "CLIENTE"
}
```

---

### PUT `/api/v1/usuarios/{id}` - Activar Usuario

Activa un usuario previamente desactivado.

**🔒 Permiso requerido:** `usuario:activar-usuario`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del usuario |

**Response 200 OK:** Void

---

### DELETE `/api/v1/usuarios/{id}` - Desactivar Usuario

Desactiva un usuario del sistema (soft delete).

**🔒 Permiso requerido:** `usuario:desactivar-usuario`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del usuario |

**Response 200 OK:** Void

---

## 🚙 Vehículo Controller

Base path: `/api/v1/vehiculos`

### POST `/api/v1/vehiculos` - Agregar Vehículo

Agrega un nuevo vehículo al inventario.

**🔒 Permiso requerido:** `vehiculo:agregar-vehiculo`

**Content-Type:** `multipart/form-data`

**Request Parts:**
| Parte | Tipo | Descripción |
|-------|------|-------------|
| `data` | JSON | Datos del vehículo |
| `foto` | File | Imagen del vehículo |

**Estructura de `data`:**
```json
{
  "placa": "ABC-123",       // Requerido - Formato: AAA-111
  "anio": 2020,             // Requerido - Rango: 1900-2024
  "kilometraje": 15000.5,   // Requerido - Mayor o igual a 0
  "valorDia": 150000.0,     // Requerido - Mayor a 0
  "color": "Rojo",          // Requerido
  "idModelo": 1             // Requerido - ID del modelo
}
```

**Response 200 OK:**
```json
{
  "status": true,
  "message": "vehiculo añadido!"
}
```

**Validaciones:**
- `placa`: Formato `AAA-111` (3 letras mayúsculas, guión, 3 números)
- `anio`: Entre 1900 y 2024
- `kilometraje`: Número positivo o cero
- `valorDia`: Número positivo

---

### GET `/api/v1/vehiculos` - Obtener Lista de Vehículos

Retorna la lista de todos los vehículos.

**🔒 Permiso requerido:** `vehiculo:obtener-lista`

**Response 200 OK:**
```json
[
  {
    "id": 1,
    "placa": "ABC-123",
    "anio": 2020,
    "kilometraje": 15000.5,
    "valorDia": 150000.0,
    "color": "Rojo",
    "activo": true,
    "marca": "Toyota",
    "modelo": "Corolla"
  }
]
```

---

### GET `/api/v1/vehiculos/fotos` - Obtener Vehículos con Fotos

Retorna la lista de vehículos incluyendo sus imágenes en base64.

**🔒 Permiso requerido:** `vehiculo:obtener-lista-fotos`

**Response 200 OK:**
```json
[
  {
    "VehiculoInfo": {
      "id": 1,
      "placa": "ABC-123",
      "anio": 2020,
      "kilometraje": 15000.5,
      "valorDia": 150000.0,
      "color": "Rojo",
      "activo": true,
      "marca": "Toyota",
      "modelo": "Corolla"
    },
    "base64Image": "iVBORw0KGgoAAAANSUhEUgAAAAUA...",
    "mediaType": "image/webp"
  }
]
```

---

### GET `/api/v1/vehiculos/disponibles` - Obtener Vehículos Disponibles

Retorna los vehículos disponibles para un tipo y rango de fechas.

**🔒 Permiso requerido:** `vehiculo:obtener-lista-disponibles`

**Query Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `idTipoVehiculo` | Integer | ID del tipo de vehículo |
| `fechaInicio` | LocalDate | Fecha de inicio (YYYY-MM-DD) |
| `fechaFin` | LocalDate | Fecha de entrega (YYYY-MM-DD) |

**Ejemplo:** `/api/v1/vehiculos/disponibles?idTipoVehiculo=1&fechaInicio=2024-01-15&fechaFin=2024-01-20`

**Response 200 OK:**
```json
[
  {
    "VehiculoInfo": {
      "id": 1,
      "placa": "ABC-123",
      "anio": 2020,
      "kilometraje": 15000.5,
      "valorDia": 150000.0,
      "color": "Rojo",
      "activo": true,
      "marca": "Toyota",
      "modelo": "Corolla"
    },
    "base64Image": "iVBORw0KGgoAAAANSUhEUgAAAAUA...",
    "mediaType": "image/webp"
  }
]
```

---

### GET `/api/v1/vehiculos/{id}` - Obtener Vehículo por ID

Retorna un vehículo específico con su imagen.

**🔒 Permiso requerido:** `vehiculo:obtener-por-id`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del vehículo |

**Response 200 OK:**
```json
{
  "VehiculoInfo": {
    "id": 1,
    "placa": "ABC-123",
    "anio": 2020,
    "kilometraje": 15000.5,
    "valorDia": 150000.0,
    "color": "Rojo",
    "activo": true,
    "marca": "Toyota",
    "modelo": "Corolla"
  },
  "base64Image": "iVBORw0KGgoAAAANSUhEUgAAAAUA...",
  "mediaType": "image/webp"
}
```

---

### PUT `/api/v1/vehiculos/{id}` - Activar Vehículo

Activa un vehículo previamente desactivado.

**🔒 Permiso requerido:** `vehiculo:activar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del vehículo |

**Response 200 OK:**
```json
{
  "status": true,
  "message": "Vehiculo activado exitosamente"
}
```

---

### DELETE `/api/v1/vehiculos/{id}` - Desactivar Vehículo

Desactiva un vehículo del inventario (soft delete).

**🔒 Permiso requerido:** `vehiculo:desactivar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del vehículo |

**Response 200 OK:** Void

---

## 📅 Reserva Controller

Base path: `/api/v1/reservas`

### POST `/api/v1/reservas` - Crear Reserva

Crea una nueva reserva de vehículo.

**🔒 Permiso requerido:** `reserva:crear`

**Request Body:**
```json
{
  "idUsuario": 1,           // Requerido
  "idVehiculo": 1,          // Requerido
  "fechaInicio": "2024-01-15",   // Requerido - Formato YYYY-MM-DD
  "fechaEntrega": "2024-01-20"   // Requerido - Formato YYYY-MM-DD
}
```

**Response 200 OK:**
```json
{
  "status": true,
  "message": "Reserva creada existosamente"
}
```

---

### GET `/api/v1/reservas` - Obtener Lista de Reservas

Retorna todas las reservas del sistema.

**🔒 Permiso requerido:** `reserva:obtener-lista`

**Response 200 OK:**
```json
[
  {
    "id": 1,
    "placaVehiculo": "ABC-123",
    "cedula": "1234567890",
    "estado": "PENDIENTE",
    "fechaInicio": "2024-01-15",
    "fechaEntrega": "2024-01-20"
  }
]
```

---

### GET `/api/v1/reservas/usuario/{idUsuario}` - Obtener Reservas por Usuario

Retorna las reservas de un usuario específico.

**🔒 Permiso requerido:** `reserva:obtener-lista-por-usuario`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `idUsuario` | Integer | ID del usuario |

**Response 200 OK:**
```json
[
  {
    "id": 1,
    "placaVehiculo": "ABC-123",
    "cedula": "1234567890",
    "estado": "PENDIENTE",
    "fechaInicio": "2024-01-15",
    "fechaEntrega": "2024-01-20"
  }
]
```

---

### PUT `/api/v1/reservas/{idReserva}/{idEstado}` - Actualizar Estado de Reserva

Actualiza el estado de una reserva existente.

**🔒 Permiso requerido:** `reserva:actualizar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `idReserva` | Integer | ID de la reserva |
| `idEstado` | Integer | ID del nuevo estado |

**Response 200 OK:**
```json
{
  "status": true,
  "message": "Reserva actualizada exitosamente"
}
```

---

## 🏭 Marca Controller

Base path: `/api/v1/marcas`

### POST `/api/v1/marcas` - Agregar Marca

Agrega una nueva marca de vehículos.

**🔒 Permiso requerido:** `marca:agregar`

**Content-Type:** `multipart/form-data`

**Request Parts:**
| Parte | Tipo | Descripción |
|-------|------|-------------|
| `data` | JSON | Datos de la marca |
| `logo` | File | Logo de la marca |

**Estructura de `data`:**
```json
{
  "marca": "Toyota"   // Requerido - Nombre de la marca
}
```

**Response 200 OK:**
```json
{
  "status": true,
  "message": "Marca agregada exitosamente"
}
```

---

### GET `/api/v1/marcas` - Obtener Lista de Marcas

Retorna todas las marcas registradas.

**🔒 Permiso requerido:** `marca:obtener-lista`

**Response 200 OK:**
```json
[
  {
    "id": 1,
    "marca": "Toyota",
    "activo": true
  }
]
```

---

### GET `/api/v1/marcas/{id}` - Obtener Marca por ID

Retorna los datos de una marca específica.

**🔒 Permiso requerido:** `marca:obtener`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID de la marca |

**Response 200 OK:**
```json
{
  "id": 1,
  "marca": "Toyota",
  "activo": true
}
```

---

### GET `/api/v1/marcas/logos` - Obtener Marcas con Logo

Retorna todas las marcas con sus logos en base64.

**🔒 Permiso requerido:** `marca:obtener-marca-logo`

**Response 200 OK:**
```json
[
  {
    "marcaInfo": {
      "id": 1,
      "marca": "Toyota",
      "activo": true
    },
    "base64Image": "iVBORw0KGgoAAAANSUhEUgAAAAUA...",
    "mediaType": "image/png"
  }
]
```

---

### GET `/api/v1/marcas/logo/{id}` - Obtener Logo de Marca

Retorna el archivo de imagen del logo de una marca.

**🔒 Permiso requerido:** `marca:obtener-logo`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID de la marca |

**Response 200 OK:** Archivo binario de imagen

**Content-Type:** `image/png`, `image/jpeg`, `image/webp`, etc.

---

### PUT `/api/v1/marcas/{id}` - Activar Marca

Activa una marca previamente desactivada.

**🔒 Permiso requerido:** `marca:activar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID de la marca |

**Response 200 OK:**
```json
{
  "status": true,
  "message": "Marca activada exitosamente"
}
```

---

### DELETE `/api/v1/marcas/{id}` - Desactivar Marca

Desactiva una marca del sistema (soft delete).

**🔒 Permiso requerido:** `marca:desactivar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID de la marca |

**Response 200 OK:** Void

---

### DELETE `/api/v1/marcas/logo/{id}` - Eliminar Logo de Marca

Elimina el logo de una marca.

**🔒 Permiso requerido:** `marca:eliminar-logo`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID de la marca |

**Response 200 OK:** Void

---

## 🚘 Modelo Controller

Base path: `/api/v1/modelos`

### POST `/api/v1/modelos` - Agregar Modelo

Agrega un nuevo modelo de vehículo.

**🔒 Permiso requerido:** `modelo:agregar-modelo`

**Request Body:**
```json
{
  "modelo": "Corolla",      // Requerido - Nombre del modelo
  "idMarca": 1,             // Requerido - ID de la marca
  "idTipoVehiculo": 1       // Requerido - ID del tipo de vehículo
}
```

**Response 200 OK:**
```json
{
  "status": true,
  "message": "Modelo agregado existosamente"
}
```

---

### GET `/api/v1/modelos` - Obtener Lista de Modelos

Retorna todos los modelos registrados.

**🔒 Permiso requerido:** `modelo:obtener-lista`

**Response 200 OK:**
```json
[
  {
    "id": 1,
    "modelo": "Corolla",
    "activo": true,
    "marca": "Toyota",
    "tipoVehiculo": "Sedán"
  }
]
```

---

### GET `/api/v1/modelos/{id}` - Obtener Modelo por ID

Retorna los datos de un modelo específico.

**🔒 Permiso requerido:** `modelo:obtener-modelo`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del modelo |

**Response 200 OK:**
```json
{
  "id": 1,
  "modelo": "Corolla",
  "activo": true,
  "idMarca": 1,
  "idTipoVehiculo": 1
}
```

---

### PUT `/api/v1/modelos/{id}` - Activar Modelo

Activa un modelo previamente desactivado.

**🔒 Permiso requerido:** `modelo:activar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del modelo |

**Response 200 OK:**
```json
{
  "status": true,
  "message": "El modelo ha sido activado"
}
```

---

### DELETE `/api/v1/modelos/{id}` - Desactivar Modelo

Desactiva un modelo del sistema (soft delete).

**🔒 Permiso requerido:** `modelo:desactivar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del modelo |

**Response 200 OK:** Void

---

## 🚐 Tipo Vehículo Controller

Base path: `/api/v1/tipo-vehiculos`

### POST `/api/v1/tipo-vehiculos` - Agregar Tipo de Vehículo

Agrega un nuevo tipo de vehículo.

**🔒 Permiso requerido:** `tipo-vehiculo:agregar`

**Request Body:**
```json
{
  "tipo": "Sedán",                          // Requerido
  "descripcion": "Vehículo de 4 puertas"    // Requerido
}
```

**Response 200 OK:**
```json
{
  "status": true,
  "message": "Tipo de vehiculo agregado exitosamente"
}
```

---

### GET `/api/v1/tipo-vehiculos` - Obtener Lista de Tipos

Retorna todos los tipos de vehículos registrados.

**🔒 Permiso requerido:** `tipo-vehiculo:obtener-lista`

**Response 200 OK:**
```json
[
  {
    "id": 1,
    "tipo": "Sedán",
    "descripcion": "Vehículo de 4 puertas",
    "activo": true
  }
]
```

---

### PUT `/api/v1/tipo-vehiculos/{id}` - Activar Tipo de Vehículo

Activa un tipo de vehículo previamente desactivado.

**🔒 Permiso requerido:** `tipo-vehiculo:activar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del tipo de vehículo |

**Response 200 OK:**
```json
{
  "status": true,
  "message": "El tipo de vehiculo ha sido activado"
}
```

---

### DELETE `/api/v1/tipo-vehiculos/{id}` - Desactivar Tipo de Vehículo

Desactiva un tipo de vehículo del sistema (soft delete).

**🔒 Permiso requerido:** `tipo-vehiculo:desactivar`

**Path Parameters:**
| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `id` | Integer | ID del tipo de vehículo |

**Response 200 OK:** Void

---

## 🧾 Facturas Controller

Base path: `/api/v1/facturas`

### GET `/api/v1/facturas` - Obtener Lista de Facturas

Retorna todas las facturas del sistema.

**🔒 Permiso requerido:** `facturas:obtener-lista`

**Response 200 OK:**
```json
[
  {
    "id": 1,
    "valor": 750000.0,
    "fecha": "2024-01-20",
    "idReserva": 1
  }
]
```

---

## 📊 Dashboard Controller

Base path: `/api/v1/dashboard`

### GET `/api/v1/dashboard` - Obtener Dashboard

Retorna estadísticas generales del sistema.

**🔒 Permiso requerido:** `dashboard:obtener-dashboard`

**Response 200 OK:**
```json
{
  "totalGanancias": 5250000.0,
  "totalClientes": 150,
  "totalReservas": 75,
  "totalVehiculos": 25
}
```

---

## ⚠️ Códigos de Error

### Respuestas de Error Comunes

| Código | Descripción |
|--------|-------------|
| `400` | Bad Request - Datos de entrada inválidos |
| `401` | Unauthorized - Token inválido o expirado |
| `403` | Forbidden - Sin permisos para esta acción |
| `404` | Not Found - Recurso no encontrado |
| `500` | Internal Server Error - Error del servidor |

### Estructura de Error de Autenticación
```json
{
  "status": "401",
  "message": "Sesión inválida, inicie sesión nuevamente."
}
```

### Estructura de Error de Validación
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "errors": [
    {
      "field": "cedula",
      "message": "La cédula solo debe contener números"
    }
  ]
}
```

---

## 📝 Notas Adicionales

### Formatos de Fecha
- Todas las fechas utilizan el formato ISO 8601: `YYYY-MM-DD`
- Ejemplo: `2024-01-15`

### Límites de Archivos
- Tamaño máximo de archivo: **3MB**
- Tamaño máximo de request: **4MB**
- Formatos de imagen soportados: PNG, JPEG, WebP

### Paginación
Actualmente la API no implementa paginación. Todos los endpoints de lista retornan todos los registros disponibles.

---

## 🔗 Enlaces Útiles

- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

---

**Autor:** Juan Andrés Herrera  
**Última actualización:** Noviembre 2025
