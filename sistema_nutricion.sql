CREATE DATABASE IF NOT EXISTS sistema_nutricion;
USE sistema_nutricion;

-- ==========================================================
-- 1. CATÁLOGOS BÁSICOS
-- ==========================================================
CREATE TABLE tipos_dieta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE categorias_alimento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE metodos_pago (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE, -- Ej: 'Débito', 'Crédito', 'Efectivo'
    activo BOOLEAN DEFAULT TRUE
);

-- ==========================================================
-- 2. USUARIOS Y PERSONAL
-- ==========================================================
CREATE TABLE nutricionistas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20)
);

CREATE TABLE horarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nutricionista_id BIGINT NOT NULL,
    dia_semana ENUM('LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO') NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    CONSTRAINT fk_horario_nutri FOREIGN KEY (nutricionista_id) REFERENCES nutricionistas(id) ON DELETE CASCADE
);

CREATE TABLE pacientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    run VARCHAR(15) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    tipo_dieta_id BIGINT,
    fecha_registro DATE NOT NULL,
    CONSTRAINT fk_paciente_dieta FOREIGN KEY (tipo_dieta_id) REFERENCES tipos_dieta(id),
);

-- ==========================================================
-- 3. DOMINIO CLÍNICO
-- ==========================================================
CREATE TABLE enfermedades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE alergias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE medicamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE antecedentes_clinicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL UNIQUE,
    observaciones_generales TEXT,
    CONSTRAINT fk_antecedente_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

-- Tablas puente para resolver N:M
CREATE TABLE paciente_enfermedad (
    antecedente_id BIGINT NOT NULL,
    enfermedad_id BIGINT NOT NULL,
    PRIMARY KEY (antecedente_id, enfermedad_id),
    CONSTRAINT fk_pe_antecedente FOREIGN KEY (antecedente_id) REFERENCES antecedentes_clinicos(id) ON DELETE CASCADE,
    CONSTRAINT fk_pe_enfermedad FOREIGN KEY (enfermedad_id) REFERENCES enfermedades(id)
);

CREATE TABLE paciente_alergia (
    antecedente_id BIGINT NOT NULL,
    alergia_id BIGINT NOT NULL,
    PRIMARY KEY (antecedente_id, alergia_id),
    CONSTRAINT fk_pa_antecedente FOREIGN KEY (antecedente_id) REFERENCES antecedentes_clinicos(id) ON DELETE CASCADE,
    CONSTRAINT fk_pa_alergia FOREIGN KEY (alergia_id) REFERENCES alergias(id)
);

CREATE TABLE paciente_medicamento (
    antecedente_id BIGINT NOT NULL,
    medicamento_id BIGINT NOT NULL,
    dosis VARCHAR(100),
    PRIMARY KEY (antecedente_id, medicamento_id),
    CONSTRAINT fk_pm_antecedente FOREIGN KEY (antecedente_id) REFERENCES antecedentes_clinicos(id) ON DELETE CASCADE,
    CONSTRAINT fk_pm_medicamento FOREIGN KEY (medicamento_id) REFERENCES medicamentos(id)
);

-- ==========================================================
-- 4. CITAS Y FACTURACIÓN
-- ==========================================================
CREATE TABLE citas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    nutricionista_id BIGINT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    motivo VARCHAR(255),
    estado ENUM('PENDIENTE', 'CONFIRMADA', 'REALIZADA', 'CANCELADA') DEFAULT 'PENDIENTE',
    CONSTRAINT fk_cita_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE,
    CONSTRAINT fk_cita_nutri FOREIGN KEY (nutricionista_id) REFERENCES nutricionistas(id)
);

CREATE TABLE pagos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cita_id BIGINT NOT NULL UNIQUE,
    metodo_pago_id BIGINT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha_pago DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('PENDIENTE', 'COMPLETADO', 'RECHAZADO', 'REEMBOLSADO') DEFAULT 'PENDIENTE',
    numero_transaccion VARCHAR(100) UNIQUE,
    CONSTRAINT fk_pago_cita FOREIGN KEY (cita_id) REFERENCES citas(id),
    CONSTRAINT fk_pago_metodo FOREIGN KEY (metodo_pago_id) REFERENCES metodos_pago(id)
);

-- ==========================================================
-- 5. NUTRICIÓN Y FITNESS
-- ==========================================================
CREATE TABLE minutas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    nutricionista_id BIGINT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    url_archivo VARCHAR(255),
    estado ENUM('ACTIVA', 'INACTIVA') DEFAULT 'ACTIVA',
    CONSTRAINT fk_minuta_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE,
    CONSTRAINT fk_minuta_nutri FOREIGN KEY (nutricionista_id) REFERENCES nutricionistas(id)
);

CREATE TABLE rutinas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    CONSTRAINT fk_rutina_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

CREATE TABLE ejercicios_rutina (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rutina_id BIGINT NOT NULL,
    nombre_ejercicio VARCHAR(100) NOT NULL,
    series INT NOT NULL,
    repeticiones INT NOT NULL,
    descanso_segundos INT NOT NULL,
    CONSTRAINT fk_ejercicio_rutina FOREIGN KEY (rutina_id) REFERENCES rutinas(id) ON DELETE CASCADE
);

-- Tablas de Alimentos y Recetas
CREATE TABLE alimentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    categoria_id BIGINT,
    nombre VARCHAR(150) NOT NULL UNIQUE,
    proteina_g DECIMAL(5,2) NOT NULL,
    grasa_g DECIMAL(5,2) NOT NULL,
    carbohidrato_g DECIMAL(5,2) NOT NULL,
    calorias_100g DECIMAL(6,2) NOT NULL,
    CONSTRAINT fk_alimento_cat FOREIGN KEY (categoria_id) REFERENCES categorias_alimento(id)
);

CREATE TABLE recetas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    nombre_plato VARCHAR(150) NOT NULL,
    preparacion TEXT NOT NULL,
    estado ENUM('PENDIENTE', 'APROBADO', 'RECHAZADO') DEFAULT 'PENDIENTE',
    CONSTRAINT fk_receta_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

CREATE TABLE receta_ingredientes (
    receta_id BIGINT NOT NULL,
    alimento_id BIGINT NOT NULL,
    cantidad_gramos DECIMAL(8,2) NOT NULL,
    PRIMARY KEY (receta_id, alimento_id),
    CONSTRAINT fk_ing_receta FOREIGN KEY (receta_id) REFERENCES recetas(id) ON DELETE CASCADE,
    CONSTRAINT fk_ing_alimento FOREIGN KEY (alimento_id) REFERENCES alimentos(id)
);

-- ==========================================================
-- 6. SEGUIMIENTO
-- ==========================================================
CREATE TABLE mediciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    fecha_medicion DATE NOT NULL,
    peso_kg DECIMAL(5,2) NOT NULL,
    talla_cm DECIMAL(5,2) NOT NULL,
    grasa_porcentaje DECIMAL(5,2),
    musculo_porcentaje DECIMAL(5,2),
    CONSTRAINT fk_medicion_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

CREATE TABLE metas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    nombre_meta VARCHAR(100) NOT NULL, 
    valor_objetivo DECIMAL(8,2) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    CONSTRAINT fk_meta_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id) ON DELETE CASCADE
);

CREATE TABLE progresos_meta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meta_id BIGINT NOT NULL,
    fecha_registro DATE NOT NULL,
    valor_alcanzado DECIMAL(8,2) NOT NULL,
    CONSTRAINT fk_progreso_meta FOREIGN KEY (meta_id) REFERENCES metas(id) ON DELETE CASCADE
);