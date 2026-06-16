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
- [Control de Acceso y Roles](#-control-de-aceso-y-roles)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Contribución](#-contribución)
- [Contacto](#-contacto)

---

## 🧬 Acerca del Proyecto

**NutriHealth** es una plataforma backend de gestión nutricional diseñada con una arquitectura de **microservicios**, donde cada dominio del negocio opera de forma independiente y se comunica a través de un **API Gateway centralizado**.

El sistema nace ante la necesidad de digitalizar y profesionalizar el flujo de trabajo clínico entre nutricionistas y sus pacientes: desde el registro de perfiles y asignación de dietas, hasta la creación de recetas personalizadas con control detallado de ingredientes y macronutrientes.

---

## ✨ Características

- 🏥 **Gestión de Nutricionistas** — Registro, consulta, actualización y eliminación de perfiles profesionales.
- 🧑‍⚕️ **Gestión de Pacientes** — Administración completa de pacientes con asignación de tipo de dieta personalizada.
- 🥦 **Catálogo de Alimentos** — Base de datos de alimentos con información nutricional detallada, organizada por categorías.
- 📒 **Gestión de Recetas** — Creación de recetas personalizadas por paciente, con lista de ingredientes y cantidades.
- 📅 **Gestión de Citas** — Programación y seguimiento de citas entre nutricionistas y pacientes.
- 📝 **Minutas y Dietas** — Planificación de comidas diarias personalizadas para cada paciente.
- 📋 **Antecedentes Médicos** — Registro de antecedentes de salud, alergias y medicamentos.
- 🏋️ **Rutinas de Ejercicio** — Creación y asignación de planes de entrenamiento asociados a la dieta.
- 🔗 **API Gateway** — Punto de entrada único para enrutar el tráfico a todos los microservicios desde el puerto `9090`.
- 🔑 **Seguridad y JWT** — Microservicio de autenticación centralizado y validación de tokens Bearer.

---

## 🛠️ Tecnologías Utilizadas

### Backend & Seguridad
*   Java 21
*   Spring Boot 3.x (Spring Data JPA, Spring Security, Spring Cloud Gateway)
*   JWT (JSON Web Token)
*   Lombok & Hibernate

### Base de Datos
*   MySQL 8 (Administrado localmente vía XAMPP o similar)

### Herramientas
| Herramienta | Uso / Descripción |
|---|---|
| Maven | Gestión de dependencias y empaquetado (build) |
| Git & GitHub | Control de versiones y repositorio de código |
| Postman | Pruebas de endpoints REST y seguridad |
| XAMPP | Servidor local Apache y base de datos MySQL |
| Swagger UI / OpenAPI | Documentación interactiva de APIs de los microservicios |

---

## 🏗️ Arquitectura

El sistema está compuesto por los siguientes servicios:
1.  **apigateway** (Puerto `9090`)
2.  **auth-service** (Puerto `8090`)
3.  **servicio-nutricionista** (Puerto `8081`)
4.  **servicio-paciente** (Puerto `8085`)
5.  **servicio-recetas** (Puerto `8082`)
6.  **servicio-alimentos** (Puerto `8083`)
7.  **servicio-citas** (Puerto `8084`)
8.  **servicio-minutas** (Puerto `8086`)
9.  **servicio-antecedentes** (Puerto `8087`)
10. **servicio-rutinas** (Puerto `8089`)

> 📷 El diagrama de arquitectura del sistema se encuentra en [`Diagrama de arquitectura.png`](./Diagrama%20de%20arquitectura.png) en la raíz del repositorio.

---

## 🔐 Control de Acceso y Roles

El acceso a los recursos del ecosistema está protegido y regulado bajo la siguiente **Matriz de Control de Permisos**:

| Acción / Método HTTP | ADMINISTRADOR | NUTRICIONISTA | PACIENTE | ADMINISTRATIVO | INTERNAL (WebClient) |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **GET** (Consultar/Listar) | **Permitido** | **Permitido** | **Permitido** | *Denegado* | **Permitido** |
| **POST** (Crear nuevo) | **Permitido** | **Permitido** | *Denegado* | *Denegado* | **Permitido** |
| **PUT** (Modificar existente) | **Permitido** | **Permitido** | *Denegado* | *Denegado* | **Permitido** |
| **DELETE** (Eliminar recurso) | **Permitido** | *Denegado* | *Denegado* | *Denegado* | **Permitido** |

*Nota:* Las peticiones internas que se realizan mediante `WebClient` utilizan una firma secreta (`X-Internal-Request`) que les otorga el rol automático `INTERNAL`, permitiendo el flujo de datos necesario entre servicios.

---

## 🚀 Instalación y Ejecución

### Prerrequisitos
Asegúrate de tener instalado lo siguiente en tu equipo:
*   [Java 21+](https://adoptium.net/)
*   [Maven 3.9+](https://maven.apache.org/)
*   [Git](https://git-scm.com/)
*   [XAMPP](https://www.apachefriends.org/es/index.html) (para base de datos MySQL)

Sigue estos pasos para clonar, configurar y ejecutar todo el ecosistema de NutriHealth localmente:

### 1. Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/nutrihealth-parra-maripan-aliaga.git
cd nutrihealth-parra-maripan-aliaga
```

### 2. Iniciar XAMPP
1. Abre el panel de control de **XAMPP** en tu equipo.
2. Inicia el módulo **Apache** haciendo clic en *Start*.
3. Inicia el módulo **MySQL** haciendo clic en *Start*.

### 3. Crear las Bases de Datos
Abre phpMyAdmin (`http://localhost/phpmyadmin`) o tu cliente MySQL favorito y ejecuta la creación de las bases de datos requeridas:
```sql
CREATE DATABASE bd_seguridad;
CREATE DATABASE bd_nutricionista;
CREATE DATABASE bd_paciente;
CREATE DATABASE bd_receta;
CREATE DATABASE bd_alimento;
CREATE DATABASE bd_cita;
CREATE DATABASE bd_minuta;
CREATE DATABASE bd_antecedente;
CREATE DATABASE bd_rutina;
```

### 4. Activar y Ejecutar todas las APIs
Cada servicio se inicia independientemente. Puedes importarlos en tu IDE favorito (como IntelliJ IDEA, Spring Tool Suite o VS Code) e iniciarlos todos en paralelo, o bien ejecutar los siguientes comandos en terminales separadas:

```bash
# Iniciar Auth-Service (Puerto 8090)
cd backend/auth-service
mvn spring-boot:run

# Iniciar Nutricionista (Puerto 8081)
cd ../nutricionista
mvn spring-boot:run

# Iniciar Paciente (Puerto 8085)
cd ../paciente
mvn spring-boot:run

# Iniciar Recetas (Puerto 8082)
cd ../api-recetas
mvn spring-boot:run

# Iniciar Alimentos (Puerto 8083)
cd ../api-alimentos
mvn spring-boot:run

# Iniciar Citas (Puerto 8084)
cd ../cita
mvn spring-boot:run

# Iniciar Minutas (Puerto 8086)
cd ../api-minutas
mvn spring-boot:run

# Iniciar Antecedentes (Puerto 8087)
cd ../api-antecedentes
mvn spring-boot:run

# Iniciar Rutinas (Puerto 8089)
cd ../api-rutinas
mvn spring-boot:run

# Iniciar API Gateway (Puerto 9090) - ¡Iniciar al final!
cd ../apigateway
mvn spring-boot:run
```

---

## 📡 Endpoints de la API

Todos los endpoints se consumen de manera centralizada a través del **API Gateway** en `http://localhost:9090`.

### 🔐 Autenticación — Servicio: `http://localhost:8090`
*   `POST /auth/register` — Registro de usuarios asignando roles (`ADMINISTRADOR`, `NUTRICIONISTA`, `PACIENTE`).
*   `POST /auth/login` — Autenticación para obtener el token Bearer JWT.

### 🧑‍⚕️ Gestión Médica y Nutricional
*   **Nutricionistas (`/api/v1/nutricionistas/**`)** — Registro y visualización de perfiles clínicos.
*   **Pacientes (`/api/v1/pacientes/**`)** — Administración de pacientes y tipos de dieta.
*   **Alimentos (`/api/v1/alimentos/**` y `/api/v1/categorias/**`)** — Catálogo general y macronutrientes.
*   **Recetas (`/api/v1/recetas/**` e `/api/v1/ingredientes/**`)** — Recetarios personalizados.
*   **Citas (`/api/v1/citas/**`)** — Agendamiento y estados.
*   **Minutas (`/api/v1/minutas/**`)** — Dietas y comidas.
*   **Antecedentes (`/api/v1/antecedentes/**`)** — Historial clínico, alergias y medicamentos.
*   **Rutinas (`/api/v1/rutinas/**`)** — Planes de ejercicios.

---

## 📬 Contacto

**Autores:**
*   [Javier Parra](https://github.com/JavierPKS)
*   [Antonia Maripan](https://github.com/AbigailMny)
*   [Nicol Aliaga](https://github.com/Ni-maxd)

---
<p align="center">
  NutriHealth © 2026
</p>
