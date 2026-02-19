CREATE DATABASE repositorio_web;
USE repositorio_web;

CREATE TABLE cadi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    total_actividades INT NOT NULL
);

CREATE TABLE rea (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    cadi_id BIGINT NOT NULL,
    FOREIGN KEY (cadi_id) REFERENCES cadi(id)
        ON DELETE CASCADE
);

CREATE TABLE actividad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    archivo VARCHAR(500),
    fecha_subida DATETIME DEFAULT CURRENT_TIMESTAMP,
    rea_id BIGINT NOT NULL,
    
    FOREIGN KEY (rea_id) REFERENCES rea(id)
        ON DELETE CASCADE
);

CREATE TABLE recurso_adicional (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    archivo VARCHAR(500),
    fecha_subida DATETIME DEFAULT CURRENT_TIMESTAMP,
    rea_id BIGINT NOT NULL,
    usuario_id BIGINT,

    FOREIGN KEY (rea_id) REFERENCES rea(id)
        ON DELETE CASCADE
);

INSERT INTO cadi (nombre, descripcion, total_actividades)
VALUES (
'Sistemas Operativos',
'EMPLEAR LAS ACTIVIDADES BASICAS Y ESENCIALES DE LOS SISTEMAS OPERATIVOS MEDIANTE LA ADMINISTRACION Y GESTION DEL SOFTWARE A LA MEDIDA TANTO A NIVEL DE USUARIO COMO A NIVEL DE PROGRAMACION.',
9
);
INSERT INTO rea (nombre, descripcion, cadi_id)
VALUES (
'Rea 1',
'Diseñar soluciones basicas de automatizacion y administracion de recursos en sistemas operativos Windows, mediante el analisis progresivo de su arquitectura, la simulacion de algoritmos de planificacion y multitarea, y la creacion de scripts funcionales con CMD y PowerShell, en contextos industriales y academicos que requieren eficiencia, escalabilidad y seguridad.',
1
);
INSERT INTO rea (nombre, descripcion, cadi_id)
VALUES (
'Rea 2',
'Gestionar tareas de administracion en sistemas Linux mediante el uso de comandos intermedios y avanzados en terminal, scripts de automatizacion y buenas practicas operativas, con el fin de asegurar la eficiencia, seguridad y estabilidad en entornos simulados y reales.',
1
);
INSERT INTO rea (nombre, descripcion, cadi_id)
VALUES (
'Rea 3',
'Integrar soluciones tecnologicas colaborativas a partir del analisis de problemas reales en instituciones educativas, integrando herramientas de sistemas operativos, software libre, inteligencia artificial y ciberseguridad, mediante fases de diagnostico, planificacion, prototipado etico y socializacion tecnica, en entornos academicos que demandan impacto social y sostenibilidad.',
1
);

INSERT INTO actividad (titulo, descripcion, rea_id) VALUES
('R1-A1-S3 Lo que mueve al sistema: historia, ciclos y decisiones',
 'Analisis de la evolucion de los sistemas operativos, sus ciclos de desarrollo y toma de decisiones arquitectonicas.',
 1),

('R1-A2-S6 Gestor invisible: memoria, jerarquías y flujo de datos',
 'Estudio de la gestion de memoria, jerarquias de almacenamiento y flujo de datos en sistemas operativos.',
 1),

('R1-A3-S8 Automatiza sin ver: el poder de CMD y PowerShell',
 'Creacion de scripts de automatizacion utilizando CMD y PowerShell en entornos Windows.',
 1);
INSERT INTO actividad (titulo, descripcion, rea_id) VALUES
('R2-A1-S9 Instalar para aprender: de la virtualización al terminal',
 'Proceso de instalacion de sistemas Linux mediante virtualizacion y uso inicial del terminal.',
 2),

('R2-A1-S11 Comandos con propósito: arquitectura y control en Linux',
 'Uso de comandos avanzados en Linux para control, administracion y analisis del sistema.',
 2);

INSERT INTO actividad (titulo, descripcion, rea_id) VALUES
('R3-A2-S12 Diagnóstico Binario: ¿Por qué falla el sistema educativo?',
 'Analisis del problema educativo desde una perspectiva tecnologica y sistemica.',
 3),

('R3-A2-S13 Kernel de la Solución',
 'Diseño estructural de la solucion propuesta integrando herramientas tecnologicas.',
 3),

('R3-A3-S14 Boot de Soluciones Éticas',
 'Desarrollo de soluciones con enfoque etico y social.',
 3),

('R3-A4-S15 Compilando el Futuro',
 'Presentacion final y consolidacion del proyecto desarrollado.',
 3);
