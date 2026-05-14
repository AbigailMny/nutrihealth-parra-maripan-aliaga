<h1 align="center">
  🥗 NutriHealth
</h1>

<p align="center">
  <strong>Plataforma de gestión nutricional basada en microservicios</strong><br/>
  Conecta nutricionistas, pacientes, alimentos y recetas en un único ecosistema backend.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Arquitectura-Microservicios-blueviolet?style=for-the-badge" alt="Microservices"/>
  <img src="https://img.shields.io/badge/Estado-En_Desarrollo-yellow?style=for-the-badge" alt="Estado"/>
  <img src="https://img.shields.io/badge/Licencia-MIT-green?style=for-the-badge" alt="Licencia MIT"/>
</p>

---

## 📋 Tabla de Contenidos

- [Acerca del Proyecto](#-acerca-del-proyecto)
- [Características](#-características)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Arquitectura](#-arquitectura)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Contribución](#-contribución)
- [Contacto](#-contacto)

---

## 🧬 Acerca del Proyecto

**NutriHealth** es una plataforma backend de gestión nutricional diseñada con una arquitectura de **microservicios**, donde cada dominio del negocio opera de forma independiente y se comunica a través de un **API Gateway centralizado**.

El sistema nace ante la necesidad de digitalizar y profesionalizar el flujo de trabajo clínico entre nutricionistas y sus pacientes: desde el registro de perfiles y asignación de dietas, hasta la creación de recetas personalizadas con control detallado de ingredientes y macronutrientes.

### Problema que resuelve

> En la práctica clínica nutricional, la gestión manual de fichas de pacientes, cálculos de macronutrientes y elaboración de planes alimentarios representa una carga operativa significativa. NutriHealth automatiza y centraliza este flujo, permitiendo a los profesionales enfocarse en la atención al paciente.

---

## ✨ Características

- 🏥 **Gestión de Nutricionistas** — Registro, consulta, actualización y eliminación de perfiles profesionales.
- 🧑‍⚕️ **Gestión de Pacientes** — Administración completa de pacientes con asignación de tipo de dieta personalizada.
- 🥦 **Catálogo de Alimentos** — Base de datos de alimentos con información nutricional detallada (proteínas, grasas, carbohidratos y calorías por cada 100 g), organizada por categorías.
- 📒 **Gestión de Recetas** — Creación de recetas personalizadas por paciente, con lista de ingredientes y cantidades en gramos.
- 🔗 **API Gateway** — Punto de entrada único para enrutar el tráfico a todos los microservicios desde el puerto `9090`.
- 📐 **Arquitectura** — Cada servicio posee su propia base de datos MySQL, garantizando total independencia de despliegue.
- 🧱 **Patrón DTO** — Separación entre la capa de persistencia y la capa de presentación en todos los servicios.

---

## 🛠️ Tecnologías Utilizadas

### Backend

| Tecnología | Versión | Descripción |
|---|---|---|
| Java | 21 | Lenguaje principal del proyecto |
| Spring Boot | 3.x | Framework base para cada microservicio |
| Spring Data JPA | — | Capa de acceso a datos (ORM) |
| Spring Cloud Gateway | — | API Gateway para el enrutamiento centralizado |
| Hibernate | — | Implementación JPA / DDL automático |
| Lombok | — | Reducción de código boilerplate |
| Jackson | — | Serialización/deserialización JSON |

### Base de Datos

| Base de Datos | Motor | Descripción |
|---|---|---|
| `bd_nutricionista` | MySQL 8 | Datos de los profesionales nutricionales |
| `bd_paciente` | MySQL 8 | Perfiles de pacientes y tipos de dieta |
| `bd_alimento` | MySQL 8 | Catálogo de alimentos y categorías |
| `bd_receta` | MySQL 8 | Recetas e ingredientes asociados |

### Herramientas

| Herramienta | Uso |
|---|---|
| Maven | Gestión de dependencias y build |
| Git & GitHub | Control de versiones |
| Postman / Insomnia | Pruebas de endpoints REST |
| MySQL Workbench | Administración de bases de datos |

---

## 🏗️ Arquitectura

```
                         ┌──────────────────────┐
                         │    API Gateway :9090  │
                         └──────────┬───────────┘
                                    │
              ┌─────────────────────┼──────────────────────┐
              │                     │                       │                        │
   ┌──────────▼──────┐   ┌──────────▼──────┐   ┌──────────▼──────┐   ┌──────────▼──────┐
   │  Nutricionista  │   │    Paciente      │   │   api-recetas   │   │  api-alimentos  │
   │   :8081         │   │    :8085         │   │   :8082         │   │   :8083         │
   └──────────┬──────┘   └──────────┬──────┘   └──────────┬──────┘   └──────────┬──────┘
              │                     │                       │                        │
   ┌──────────▼──────┐   ┌──────────▼──────┐   ┌──────────▼──────┐   ┌──────────▼──────┐
   │bd_nutricionista │   │  bd_paciente    │   │   bd_receta     │   │  bd_alimento    │
   └─────────────────┘   └─────────────────┘   └─────────────────┘   └─────────────────┘
```

> 📷 El diagrama de arquitectura del sistema se encuentra en [`Diagrama de arquitectura.png`](./Diagrama%20de%20arquitectura.png) en la raíz del repositorio.

---

## 🚀 Instalación y Ejecución

### Prerrequisitos

Asegúrate de tener instalado lo siguiente en tu equipo:

- [Java 21+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [MySQL 8.0+](https://dev.mysql.com/downloads/mysql/)
- [Git](https://git-scm.com/)

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/nutrihealth-parra-maripan-aliaga.git
cd nutrihealth-parra-maripan-aliaga
```

### 2. Crear las bases de datos en MySQL

```sql
CREATE DATABASE bd_nutricionista;
CREATE DATABASE bd_paciente;
CREATE DATABASE bd_alimento;
CREATE DATABASE bd_receta;
```

### 3. Configurar credenciales

Cada microservicio tiene su propio `application.properties`:

```
backend/nutricionista/src/main/resources/application.properties
backend/paciente/src/main/resources/application.properties
backend/api-alimentos/src/main/resources/application.properties
backend/api-recetas/src/main/resources/application.properties
```

```properties
spring.datasource.username=root
spring.datasource.password=
```

### 4. Cargar datos de prueba (opcional)

Una vez que los servicios hayan creado las tablas automáticamente (gracias a `ddl-auto=update`), puedes ejecutar el script de datos de prueba:

```bash
mysql -u root -p < backend/datos_prueba.sql
```

> ⚠️ **Importante:** Ejecuta primero todos los microservicios al menos una vez antes de cargar el script, para que Hibernate genere las tablas.

### 5. Ejecutar los microservicios

Debes ejecutar Los microservicos al mismo tiempo que la apigateway en tu IDE de preferencia o usando la terminal.

Abre **5 terminales** independientes y ejecuta cada servicio:

```bash
# Terminal 1 — Nutricionista (puerto 8081)
cd backend/nutricionista
mvn spring-boot:run
```

```bash
# Terminal 2 — Paciente (puerto 8085)
cd backend/paciente
mvn spring-boot:run
```

```bash
# Terminal 3 — API Alimentos (puerto 8083)
cd backend/api-alimentos
mvn spring-boot:run
```

```bash
# Terminal 4 — API Recetas (puerto 8082)
cd backend/api-recetas
mvn spring-boot:run
```

```bash
# Terminal 5 — API Gateway (puerto 9090) — Iniciar AL FINAL
cd backend/apigateway
mvn spring-boot:run
```

### 6. Verificar el sistema

Todos los servicios son accesibles directamente en sus puertos individuales, o a través del **API Gateway** en `http://localhost:9090`.

```bash
# Ejemplo: listar todos los nutricionistas vía API Gateway
curl http://localhost:9090/api/v1/nutricionistas
```

---

## 📡 Endpoints de la API

Todos los endpoints son accesibles a través del **API Gateway** en `http://localhost:9090`.

### 👨‍⚕️ Nutricionistas — Servicio: `http://localhost:8081`

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/v1/nutricionistas` | Listar todos los nutricionistas |
| `GET` | `/api/v1/nutricionistas/{id}` | Obtener nutricionista por ID |
| `POST` | `/api/v1/nutricionistas` | Crear nuevo nutricionista |
| `PUT` | `/api/v1/nutricionistas/{id}` | Actualizar nutricionista existente |
| `DELETE` | `/api/v1/nutricionistas/{id}` | Eliminar nutricionista |

### 🧑‍⚕️ Pacientes — Servicio: `http://localhost:8085`

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/v1/pacientes` | Listar todos los pacientes |
| `GET` | `/api/v1/pacientes/{id}` | Obtener paciente por ID |
| `POST` | `/api/v1/pacientes` | Crear nuevo paciente |
| `PUT` | `/api/v1/pacientes/{id}` | Actualizar paciente existente |
| `DELETE` | `/api/v1/pacientes/{id}` | Eliminar paciente |
| `GET` | `/api/v1/tipos-dieta` | Listar todos los tipos de dieta |
| `GET` | `/api/v1/tipos-dieta/{id}` | Obtener tipo de dieta por ID |
| `POST` | `/api/v1/tipos-dieta` | Crear tipo de dieta |
| `PUT` | `/api/v1/tipos-dieta/{id}` | Actualizar tipo de dieta |
| `DELETE` | `/api/v1/tipos-dieta/{id}` | Eliminar tipo de dieta |

### 🥦 Alimentos — Servicio: `http://localhost:8083`

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/v1/alimentos` | Listar todos los alimentos |
| `GET` | `/api/v1/alimentos/{id}` | Obtener alimento por ID |
| `POST` | `/api/v1/alimentos` | Crear nuevo alimento |
| `PUT` | `/api/v1/alimentos/{id}` | Actualizar alimento existente |
| `DELETE` | `/api/v1/alimentos/{id}` | Eliminar alimento |
| `GET` | `/api/v1/categorias` | Listar todas las categorías |
| `GET` | `/api/v1/categorias/{id}` | Obtener categoría por ID |
| `POST` | `/api/v1/categorias` | Crear nueva categoría |
| `PUT` | `/api/v1/categorias/{id}` | Actualizar categoría |
| `DELETE` | `/api/v1/categorias/{id}` | Eliminar categoría |

### 📒 Recetas — Servicio: `http://localhost:8082`

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/v1/recetas` | Listar todas las recetas |
| `GET` | `/api/v1/recetas/{id}` | Obtener receta por ID |
| `POST` | `/api/v1/recetas` | Crear nueva receta |
| `PUT` | `/api/v1/recetas/{id}` | Actualizar receta existente |
| `DELETE` | `/api/v1/recetas/{id}` | Eliminar receta |
| `GET` | `/api/v1/ingredientes` | Listar todos los ingredientes |
| `GET` | `/api/v1/ingredientes/{id}` | Obtener ingrediente por ID |
| `POST` | `/api/v1/ingredientes` | Añadir ingrediente a receta |
| `PUT` | `/api/v1/ingredientes/{id}` | Actualizar ingrediente |
| `DELETE` | `/api/v1/ingredientes/{id}` | Eliminar ingrediente |

---

## 📬 Contacto

¿Tienes preguntas o sugerencias? No dudes en ponerte en contacto.

**Autores:**

- [Javier Parra](https://github.com/JavierPKS)
- [Antonia Maripan](https://github.com/AbigailMny)
- [Nicol Aliaga](https://github.com/Ni-maxd)

---

<p align="center">
  NutriHealth © 2026
</p>
