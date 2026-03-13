drop database proyecto_hospital;
-- Crear base de datos
CREATE DATABASE IF NOT EXISTS proyecto_hospital;
USE proyecto_hospital;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    cedula VARCHAR(20) NOT NULL UNIQUE, 
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);


-- Tabla de pacientes
CREATE TABLE IF NOT EXISTS paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE,
    direccion VARCHAR(150),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de doctores
CREATE TABLE IF NOT EXISTS doctor (
    id_doctor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    especialidad VARCHAR(50) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de citas médicas
CREATE TABLE IF NOT EXISTS cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_doctor INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    motivo VARCHAR(200),
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_doctor) REFERENCES doctor(id_doctor)
);
CREATE TABLE IF NOT EXISTS historial_medico (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_doctor INT,
    fecha DATE NOT NULL,
    descripcion VARCHAR(500),
    tratamiento VARCHAR(300),
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_doctor) REFERENCES doctor(id_doctor)
);
CREATE TABLE IF NOT EXISTS reporte (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    fecha DATE NOT NULL,
    tipo VARCHAR(50), -- administrativo, clínico, financiero
    activo BOOLEAN DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS inventario (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    cantidad INT NOT NULL,
    unidad VARCHAR(20), -- cajas, unidades, litros, etc.
    precio DECIMAL(10,2),
    activo BOOLEAN DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS facturacion (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_cita INT,
    fecha DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(50), -- efectivo, tarjeta, transferencia
    estado VARCHAR(20) DEFAULT 'PENDIENTE', -- pagada, pendiente, anulada
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_cita) REFERENCES cita(id_cita)
);

CREATE TABLE IF NOT EXISTS paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE,
    direccion VARCHAR(150),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);




-- USUARIO REMOTO PARA SPRING BOOT
CREATE USER 'hospital_user'@'%' IDENTIFIED BY 'tu_password';
GRANT ALL PRIVILEGES ON proyecto_hospital.* TO 'hospital_user'@'%';
FLUSH PRIVILEGES;

