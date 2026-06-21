-- =====================================================================
--  NutriHealth - Script de Datos de Prueba
--  Bases de datos: bd_nutricionista | bd_paciente | bd_alimento | bd_receta | bd_cita
-- =====================================================================

-- ─────────────────────────────────────────────────────────────────────
--  1. BD_NUTRICIONISTA  (puerto 8081)
--     Tabla: nutricionistas
-- ─────────────────────────────────────────────────────────────────────
USE bd_nutricionista;

INSERT INTO nutricionistas (nombres, apellidos, correo, telefono) VALUES
('Camila',     'Rojas Vega',      'camila.rojas@nutrihealth.cl',    '+56912345678'),
('Sebastián',  'Muñoz Pinto',     'sebastian.munoz@nutrihealth.cl', '+56923456789'),
('Valentina',  'Torres Sánchez',  'valentina.torres@nutrihealth.cl','+56934567890'),
('Felipe',     'Araya Contreras', 'felipe.araya@nutrihealth.cl',    '+56945678901'),
('Daniela',    'Lagos Fuentes',   'daniela.lagos@nutrihealth.cl',   '+56956789012');


-- ─────────────────────────────────────────────────────────────────────
--  2. BD_PACIENTE  (puerto 8085)
--     Tablas: tipos_dieta → pacientes
-- ─────────────────────────────────────────────────────────────────────
USE bd_paciente;

-- 2.1 Tipos de dieta
INSERT INTO tipos_dieta (nombre, descripcion) VALUES
('Normocalórica',  'Dieta estándar equilibrada en macronutrientes para el mantenimiento del peso corporal.'),
('Hipocalórica',   'Dieta con aporte energético reducido, orientada a la pérdida de peso de forma gradual.'),
('Hipercalórica',  'Dieta con mayor aporte energético para personas con necesidades aumentadas o bajo peso.'),
('Hiperproteica',  'Dieta con alto contenido proteico, usada en procesos de recuperación muscular o deportistas.'),
('Vegetariana',    'Dieta sin carnes ni pescados; incluye lácteos y huevos. Variante lacto-ovo-vegetariana.');

-- 2.2 Pacientes
INSERT INTO pacientes (run, nombres, apellidos, correo, telefono, tipo_dieta_id, fecha_registro) VALUES
('12345678-9', 'Andrés',   'Parra Núñez',     'andres.parra@mail.com',    '+56911111111', 1, '2025-01-15'),
('98765432-1', 'Sofía',    'Maripán Castro',  'sofia.maripan@mail.com',   '+56922222222', 2, '2025-02-20'),
('11223344-5', 'Nicolás',  'Aliaga Rivera',   'nicolas.aliaga@mail.com',  '+56933333333', 4, '2025-03-10'),
('55443322-1', 'Isidora',  'Gómez Salas',     'isidora.gomez@mail.com',   '+56944444444', 3, '2025-04-05'),
('66778899-0', 'Tomás',    'Bravo Espinoza',  'tomas.bravo@mail.com',     '+56955555555', 5, '2025-05-01'),
('33221100-K', 'Javiera',  'Soto Poblete',    'javiera.soto@mail.com',    '+56966666666', 2, '2025-06-18'),
('44332211-2', 'Matías',   'Herrera Leal',    'matias.herrera@mail.com',  '+56977777777', 1, '2025-07-22');


-- ─────────────────────────────────────────────────────────────────────
--  3. BD_ALIMENTO  (puerto 8083)
--     Tablas: categorias_alimento → alimento
-- ─────────────────────────────────────────────────────────────────────
USE bd_alimento;

-- 3.1 Categorías de alimento
INSERT INTO categorias_alimento (nombre) VALUES
('Cereales y Legumbres'),
('Carnes y Pescados'),
('Lácteos y Huevos'),
('Frutas'),
('Verduras y Hortalizas'),
('Grasas y Aceites'),
('Frutos Secos');

-- 3.2 Alimentos  (proteínaG | grasaG | carbohidratoG | calorías por 100 g)
INSERT INTO alimento (nombre, proteinag, grasag, carbohidratog, calorias, categoria_id) VALUES
-- Cereales y Legumbres (id=1)
('Arroz cocido',         2.7,  0.3, 28.2, 130, 1),
('Avena en hojuelas',    13.0, 7.0, 67.0, 389, 1),
('Pan integral',          8.0, 2.5, 41.0, 247, 1),
('Lentejas cocidas',      9.0, 0.4, 20.0, 116, 1),
('Garbanzos cocidos',     9.0, 3.0, 27.0, 164, 1),

-- Carnes y Pescados (id=2)
('Pechuga de pollo',     31.0, 3.6,  0.0, 165, 2),
('Salmón al vapor',      25.0, 13.0, 0.0, 206, 2),
('Carne de vacuno magra',26.0, 7.0,  0.0, 172, 2),
('Atún en agua',         26.0, 1.0,  0.0, 116, 2),
('Merluza al horno',     19.0, 1.0,  0.0,  82, 2),

-- Lácteos y Huevos (id=3)
('Leche semidescremada',  3.4, 1.5,  4.8,  46, 3),
('Yogur natural sin azúcar',5.0,2.0, 3.5,  53, 3),
('Queso fresco',         12.0, 6.0,  2.0, 112, 3),
('Huevo entero cocido',  13.0,10.0,  1.1, 155, 3),
('Clara de huevo',       11.0, 0.2,  0.7,  52, 3),

-- Frutas (id=4)
('Manzana',               0.3, 0.2, 14.0,  52, 4),
('Plátano',               1.1, 0.3, 23.0,  89, 4),
('Naranja',               0.9, 0.1, 12.0,  47, 4),
('Frutilla',              0.7, 0.3,  8.0,  32, 4),
('Kiwi',                  1.1, 0.5, 15.0,  61, 4),

-- Verduras y Hortalizas (id=5)
('Brócoli cocido',        2.8, 0.4,  5.2,  35, 5),
('Espinaca cruda',        2.9, 0.4,  3.6,  23, 5),
('Zanahoria cruda',       0.9, 0.2, 10.0,  41, 5),
('Tomate',                0.9, 0.2,  3.9,  18, 5),
('Pepino',                0.7, 0.1,  3.6,  15, 5),

-- Grasas y Aceites (id=6)
('Aceite de oliva extra virgen', 0.0, 100.0, 0.0, 884, 6),
('Palta',                        2.0,  15.0, 9.0, 160, 6),
('Mantequilla',                  0.9,  81.0, 0.1, 717, 6),

-- Frutos Secos (id=7)
('Almendras',           21.0, 50.0, 20.0, 579, 7),
('Nueces',              15.0, 65.0, 14.0, 654, 7);


-- ─────────────────────────────────────────────────────────────────────
--  4. BD_RECETA  (puerto 8082)
--     Tablas: receta → receta_ingredientes
-- ─────────────────────────────────────────────────────────────────────
USE bd_receta;

-- 4.1 Recetas
INSERT INTO receta (id_paciente, nombre_plato, preparacion, estado, anotaciones) VALUES
(1, 'Bowl de Pollo con Arroz y Verduras',
 'Cocinar el arroz y el brócoli al vapor. Grillar la pechuga de pollo con sal, pimienta y ajo. Servir en bowl con zanahoria rallada.',
 'ACTIVA',
 'Apta para dieta normocalórica. Alta en proteínas y fibra.'),

(2, 'Salmón al Horno con Ensalada',
 'Marinar el salmón con limón y hierbas. Hornear 20 min a 180°C. Preparar ensalada de espinaca, tomate y pepino con aceite de oliva.',
 'ACTIVA',
 'Rica en omega-3. Recomendada para control de peso.'),

(3, 'Omelette de Claras con Verduras',
 'Batir las claras de huevo con sal. Saltear brócoli, tomate y espinaca. Incorporar al omelette y cocinar a fuego medio.',
 'ACTIVA',
 'Alta en proteínas y baja en calorías. Ideal para hiperproteica.'),

(4, 'Avena con Frutas y Almendras',
 'Preparar avena con leche semidescremada. Agregar frutillas, plátano y almendras picadas. Opcional: endulzar con miel.',
 'ACTIVA',
 'Desayuno hipercalórico nutritivo. Rica en fibra y micronutrientes.'),

(5, 'Ensalada de Lentejas Vegetariana',
 'Cocinar lentejas. Mezclar con tomate, pepino, zanahoria y palta. Aliñar con aceite de oliva y limón.',
 'ACTIVA',
 '100% vegetariana. Excelente fuente de proteína vegetal.'),

(6, 'Atún con Garbanzos y Verduras',
 'Mezclar atún en agua escurrido con garbanzos cocidos. Añadir tomate, espinaca y pepino. Aliñar con aceite de oliva.',
 'ACTIVA',
 'Ideal para hipocalórica. Alta en proteína y fibra, baja en grasa.'),

(7, 'Pechuga de Pollo con Pan Integral',
 'Grillar la pechuga de pollo. Servir con 2 rebanadas de pan integral, tomate y yogur natural como salsa.',
 'BORRADOR',
 'En revisión nutricional. Balanceada en macronutrientes.');


-- 4.2 Ingredientes de recetas
--  Receta 1: Bowl de Pollo con Arroz y Verduras
INSERT INTO receta_ingredientes (id_alimento, cantidad_gramos, id_receta) VALUES
(6,  150.0, 1),  -- Pechuga de pollo
(1,  100.0, 1),  -- Arroz cocido
(21,  80.0, 1),  -- Brócoli cocido
(23,  50.0, 1),  -- Zanahoria cruda
(26,  10.0, 1);  -- Aceite de oliva

--  Receta 2: Salmón al Horno con Ensalada
INSERT INTO receta_ingredientes (id_alimento, cantidad_gramos, id_receta) VALUES
(7,  180.0, 2),  -- Salmón al vapor
(22,  60.0, 2),  -- Espinaca cruda
(24,  80.0, 2),  -- Tomate
(25,  60.0, 2),  -- Pepino
(26,  10.0, 2);  -- Aceite de oliva

--  Receta 3: Omelette de Claras con Verduras
INSERT INTO receta_ingredientes (id_alimento, cantidad_gramos, id_receta) VALUES
(15, 200.0, 3),  -- Clara de huevo (≈4 claras)
(21,  70.0, 3),  -- Brócoli cocido
(24,  60.0, 3),  -- Tomate
(22,  40.0, 3),  -- Espinaca cruda
(26,   5.0, 3);  -- Aceite de oliva

--  Receta 4: Avena con Frutas y Almendras
INSERT INTO receta_ingredientes (id_alimento, cantidad_gramos, id_receta) VALUES
(2,   80.0, 4),  -- Avena en hojuelas
(11, 200.0, 4),  -- Leche semidescremada
(19,  80.0, 4),  -- Frutilla
(17,  70.0, 4),  -- Plátano
(29,  25.0, 4);  -- Almendras

--  Receta 5: Ensalada de Lentejas Vegetariana
INSERT INTO receta_ingredientes (id_alimento, cantidad_gramos, id_receta) VALUES
(4,  150.0, 5),  -- Lentejas cocidas
(24,  80.0, 5),  -- Tomate
(25,  60.0, 5),  -- Pepino
(23,  50.0, 5),  -- Zanahoria cruda
(27, 100.0, 5),  -- Palta
(26,  10.0, 5);  -- Aceite de oliva

--  Receta 6: Atún con Garbanzos y Verduras
INSERT INTO receta_ingredientes (id_alimento, cantidad_gramos, id_receta) VALUES
(9,  120.0, 6),  -- Atún en agua
(5,  100.0, 6),  -- Garbanzos cocidos
(24,  70.0, 6),  -- Tomate
(22,  50.0, 6),  -- Espinaca cruda
(25,  50.0, 6),  -- Pepino
(26,  10.0, 6);  -- Aceite de oliva

--  Receta 7: Pechuga con Pan Integral
INSERT INTO receta_ingredientes (id_alimento, cantidad_gramos, id_receta) VALUES
(6,  120.0, 7),  -- Pechuga de pollo
(3,   60.0, 7),  -- Pan integral (2 rebanadas aprox.)
(24,  60.0, 7),  -- Tomate
(12,  80.0, 7);  -- Yogur natural sin azúcar

-- ─────────────────────────────────────────────────────────────────────
--  6. BD_MEDICIONES  (puerto 8091)
--     Tabla: mediciones
-- ─────────────────────────────────────────────────────────────────────
USE bd_mediciones;

INSERT INTO mediciones (paciente_id, fecha_medicion, peso_kg, talla_cm, grasa_porcentaje, musculo_porcentaje) VALUES
(1, '2025-06-01 08:00:00', 72.5, 175.0, 18.2, 42.7),
(2, '2025-06-03 09:30:00', 68.4, 168.5, 22.1, 39.2),
(3, '2025-06-05 07:45:00', 82.0, 180.0, 24.5, 37.5),
(4, '2025-06-08 10:15:00', 59.2, 162.0, 20.0, 35.8),
(5, '2025-06-10 11:00:00', 75.1, 170.0, 19.0, 40.0),
(1, '2025-06-15 08:15:00', 71.8, 175.0, 17.8, 43.0);

-- ─────────────────────────────────────────────────────────────────────
--  6. BD_META  (puerto 8088)
--     Tablas: metas → progresos_meta
-- ─────────────────────────────────────────────────────────────────────
USE bd_meta;

INSERT INTO metas (paciente_id, nombre_meta, valor_objetivo, unidad_medida) VALUES
(1, 'Reducir grasa corporal', 18.0, '%'),
(2, 'Aumentar masa muscular', 78.0, 'kg'),
(3, 'Mantener peso estable', 82.0, 'kg'),
(4, 'Mejorar índice de masa corporal', 22.0, 'kg/m2'),
(5, 'Aumentar fuerza en press de banca', 110.0, 'kg');

INSERT INTO progresos_meta (meta_id, fecha_registro, valor_alcanzado) VALUES
(1, '2025-06-05', 20.5),
(1, '2025-06-20', 19.2),
(2, '2025-06-08', 74.0),
(2, '2025-06-22', 76.5),
(3, '2025-06-10', 82.0),
(4, '2025-06-12', 23.0),
(5, '2025-06-14', 110.0);

-- ─────────────────────────────────────────────────────────────────────
--  7. BD_CITA  (puerto 8084)
--     Tabla: citas
-- ─────────────────────────────────────────────────────────────────────
USE bd_cita;

INSERT INTO citas (id_paciente, id_nutricionista, fecha_hora_inicio, motivo, estado) VALUES
(1, 1, '2025-06-01 10:00:00', 'Consulta inicial de evaluación nutricional.', 'R'),
(2, 1, '2025-06-01 11:30:00', 'Seguimiento de dieta hipocalórica.', 'R'),
(3, 2, '2025-06-02 09:00:00', 'Asesoría para aumento de masa muscular.', 'C'),
(4, 3, '2025-06-02 15:00:00', 'Control mensual de peso y medidas.', 'C'),
(5, 4, '2025-06-03 10:00:00', 'Revisión de dieta vegetariana.', 'P'),
(1, 2, '2025-06-15 10:00:00', 'Control de progreso y ajuste de plan.', 'P'),
(6, 5, '2025-06-04 12:00:00', 'Consulta por alergias alimentarias.', 'X');

-- ─────────────────────────────────────────────────────────────────────
--  7. BD_MINUTA  (puerto 8086)
--     Tabla: minutas
-- ─────────────────────────────────────────────────────────────────────
USE bd_minuta;

INSERT INTO minutas (paciente_id, nutricionista_id, fecha_inicio, fecha_fin, url_archivo, estado) VALUES
(1, 1, '2025-06-01', '2025-06-30', 'https://nutrihealth.cl/archivos/minutas/paciente_1_junio.pdf', 'ACTIVA'),
(2, 1, '2025-06-01', '2025-06-15', 'https://nutrihealth.cl/archivos/minutas/paciente_2_junio_quincena.pdf', 'ACTIVA'),
(3, 2, '2025-06-02', '2025-07-02', 'https://nutrihealth.cl/archivos/minutas/paciente_3_musculacion.pdf', 'ACTIVA'),
(4, 3, '2025-06-02', '2025-07-02', 'https://nutrihealth.cl/archivos/minutas/paciente_4_mantenimiento.pdf', 'ACTIVA'),
(5, 4, '2025-06-03', '2025-07-03', 'https://nutrihealth.cl/archivos/minutas/paciente_5_vegetariana.pdf', 'ACTIVA'),
(1, 2, '2025-05-01', '2025-05-31', 'https://nutrihealth.cl/archivos/minutas/paciente_1_mayo.pdf', 'INACTIVA'),
(6, 5, '2025-06-04', '2025-07-04', 'https://nutrihealth.cl/archivos/minutas/paciente_6_alergias.pdf', 'ACTIVA');

-- ─────────────────────────────────────────────────────────────────────
--  8. BD_ANTECEDENTES  (puerto 8087)
--     Tabla: antecedente
-- ─────────────────────────────────────────────────────────────────────

USE bd_antecedente;

INSERT INTO enfermedades (nombre, descripcion) VALUES
('Diabetes Tipo 2', 'Enfermedad crónica que afecta la forma en que el cuerpo procesa el azúcar en sangre.'),
('Hipertensión Arterial', 'Presión arterial alta crónica.');

INSERT INTO alergias (nombre, descripcion) VALUES
('Maní', 'Reacción alérgica al maní o productos derivados.'),
('Gluten', 'Intolerancia permanente al gluten (Enfermedad celíaca).'),
('Lactosa', 'Intolerancia a la lactosa.');

INSERT INTO medicamentos (nombre, descripcion) VALUES
('Metformina', 'Medicamento oral para controlar el azúcar en sangre en personas con diabetes tipo 2.'),
('Losartán', 'Medicamento utilizado para tratar la presión arterial alta (hipertensión).');

INSERT INTO antecedentes_clinicos (paciente_id, tipo_sangre, observaciones_generales) VALUES
(1, 'O+', 'Paciente diabético controlado con dieta y medicación.'),
(2, 'A-', 'Paciente vegetariana con intolerancia severa al gluten.');

INSERT INTO antecedente_enfermedad (antecedente_id, enfermedad_id) VALUES
(1, 1),
(2, 2);

INSERT INTO antecedente_alergia (antecedente_id, alergia_id) VALUES
(1, 1),
(2, 2),
(2, 3);

INSERT INTO antecedente_medicamento (antecedente_id, medicamento_id, dosis) VALUES
(1, 1, '850mg cada 12 horas'),
(2, 2, '50mg una vez al día'); 

-- ─────────────────────────────────────────────────────────────────────
--  10. BD_RUTINA  (puerto 8089)
--      Tablas: rutinas → ejercicios_rutina
-- ─────────────────────────────────────────────────────────────────────
USE bd_rutina;

-- 10.1 Rutinas (paciente_id referencia los IDs de bd_paciente)
INSERT INTO rutinas (paciente_id, nombre, fecha_inicio, fecha_fin) VALUES
(1, 'Rutina de Fuerza - Andrés',      '2025-06-01', '2025-08-31'),
(1, 'Cardio Matutino - Andrés',       '2025-07-01', NULL),
(2, 'Plan Tonificación - Sofía',      '2025-05-15', '2025-07-15'),
(3, 'Masa Muscular - Nicolás',        '2025-04-01', NULL),
(4, 'Rehabilitación - Isidora',       '2025-06-10', '2025-09-10'),
(5, 'Pérdida de Peso - Tomás',        '2025-05-01', NULL),
(7, 'Mantenimiento General - Matías', '2025-07-15', NULL);

-- 10.2 Ejercicios de cada rutina
-- Rutina 1: Fuerza - Andrés
INSERT INTO ejercicios_rutina (rutina_id, nombre_ejercicio, series, repeticiones, descanso_segundos) VALUES
(1, 'Sentadilla con barra',    4, 10, 90),
(1, 'Press de banca',          4, 8,  90),
(1, 'Peso muerto',             3, 6,  120),
(1, 'Remo con barra',          3, 10, 60),
(1, 'Curl de bíceps',          3, 12, 45),

-- Rutina 2: Cardio Matutino - Andrés
(2, 'Trote en cinta',          1, 1,  0),
(2, 'Salto de cuerda',         4, 50, 30),
(2, 'Burpees',                 3, 15, 45),
(2, 'Bicicleta estática',      1, 1,  0),

-- Rutina 3: Tonificación - Sofía
(3, 'Zancadas alternadas',     3, 15, 45),
(3, 'Sentadilla sumo',         3, 15, 45),
(3, 'Elevaciones de cadera',   3, 20, 30),
(3, 'Plancha frontal',         3, 1,  60),
(3, 'Fondos de tríceps',       3, 12, 45),
(3, 'Remo con mancuerna',      3, 12, 45),

-- Rutina 4: Masa Muscular - Nicolás
(4, 'Press militar',           4, 8,  90),
(4, 'Dominadas',               4, 6,  90),
(4, 'Sentadilla hack',         4, 10, 90),
(4, 'Extensión de cuádriceps', 3, 12, 60),
(4, 'Press de hombros',        3, 10, 60),
(4, 'Encogimientos de hombros',3, 15, 45),

-- Rutina 5: Rehabilitación - Isidora
(5, 'Caminata en cinta (baja intensidad)', 1, 1,  0),
(5, 'Bicicleta estática suave',           1, 1,  0),
(5, 'Estiramiento de isquiotibiales',     3, 1,  30),
(5, 'Fortalecimiento de glúteo medio',    3, 15, 30),
(5, 'Plancha lateral',                    3, 1,  30),

-- Rutina 6: Pérdida de Peso - Tomás
(6, 'Sentadilla con salto',    4, 12, 45),
(6, 'Mountain climbers',       4, 20, 30),
(6, 'Estocadas con salto',     3, 10, 45),
(6, 'Burpees',                 3, 12, 60),
(6, 'Abdominales bicicleta',   3, 20, 30),
(6, 'Plancha con toque de hombro', 3, 16, 30),

-- Rutina 7: Mantenimiento General - Matías
(7, 'Press de banca inclinado', 3, 10, 60),
(7, 'Sentadilla goblet',        3, 12, 60),
(7, 'Remo en polea baja',       3, 12, 60),
(7, 'Elevaciones laterales',    3, 15, 45),
(7, 'Curl de martillo',         3, 12, 45),
(7, 'Abdominales en crunch',    3, 20, 30);

-- ─────────────────────────────────────────────────────────────────────
--  11. BD_SEGURIDAD  (puerto 8090)
--      Tablas: roles, usuarios, usuario_roles
-- ─────────────────────────────────────────────────────────────────────
USE bd_seguridad;

INSERT INTO roles (nombre_rol) VALUES
('ROLE_NUTRICIONISTA'),
('ROLE_ADMIN'),
('ROLE_PACIENTE');

-- Contraseña para todos: 123456 (hash bcrypt común $2a$10$...)
INSERT INTO usuarios (nombre_usuario, contrasena, correo) VALUES
('nutri1', '$2a$10$QyG.48mBHMq6enSN1rqvnu7zJLMiUUfGxnKiHly5St0QuY6xlbxnW', 'nutri1@nutrihealth.cl'),
('admin1', '$2a$10$kbBO8YTn6PaoOjhzx2fUUutzovgSMUOXFS.75AHGqfxLSqpmiWydO', 'user@example.com');

INSERT INTO usuario_roles (usuario_id, rol_id) VALUES
(1, 1), -- nutri1 -> ROLE_NUTRICIONISTA
(2, 2); -- admin -> ROLE_ADMIN

-- =====================================================================
--  FIN DEL SCRIPT
-- =====================================================================