CREATE DATABASE actividades_db;
USE actividades_db;

CREATE TABLE actividades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    nombre_archivo VARCHAR(255),
    ruta VARCHAR(500)
);

INSERT INTO actividades (nombre) VALUES 
('R1-A1-S3 Lo que mueve al sistema: historia, ciclos y decisiones'),
('R1-A2-S6 Gestor invisible: memoria, jerarquías y flujo de datos'),
('R1-A3-S8 Automatiza sin ver: el poder de CMD y PowerShell'),
('R2-A1-S9 Instalar para aprender: de la virtualización al terminal'),
('R2-A1-S11 Comandos con propósito: arquitectura y control en Linux'),
('R3-A2-S12 Diagnóstico Binario: ¿Por qué falla el sistema educativo?'),
('R3-A2-S13 Kernel de la Solución.'),
('R3-A3-S14 Boot de Soluciones Éticas'),
('R3-A4-S15 Compilando el Futuro');