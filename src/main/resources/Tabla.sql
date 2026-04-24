DROP DATABASE IF EXISTS proyecto_hospital;
CREATE DATABASE proyecto_hospital;
USE proyecto_hospital;

-- Tabla de usuarios
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    cedula VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de pacientes
CREATE TABLE paciente (
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
CREATE TABLE doctor (
    id_doctor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    especialidad VARCHAR(50) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de citas médicas
CREATE TABLE cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_doctor INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    motivo VARCHAR(200),
    atendida BOOLEAN DEFAULT FALSE,
    activo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_cita_paciente
        FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    CONSTRAINT fk_cita_doctor
        FOREIGN KEY (id_doctor) REFERENCES doctor(id_doctor)
);

-- Tabla de historial médico
CREATE TABLE historial_medico (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_doctor INT,
    id_cita INT,
    fecha DATE NOT NULL,
    descripcion VARCHAR(500),
    tratamiento VARCHAR(300),
    activo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_historial_paciente
        FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    CONSTRAINT fk_historial_doctor
        FOREIGN KEY (id_doctor) REFERENCES doctor(id_doctor),
    CONSTRAINT fk_historial_cita
        FOREIGN KEY (id_cita) REFERENCES cita(id_cita)
);

-- Tabla de reportes
CREATE TABLE reporte (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    nombre_reporte VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    fecha_reporte DATE NOT NULL,
    estado VARCHAR(50),
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de inventario
CREATE TABLE inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    cantidad INT NOT NULL,
    precio DECIMAL(10,2),
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de facturación
CREATE TABLE facturacion (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    numero_factura VARCHAR(50) NOT NULL,
    cliente VARCHAR(100) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha_factura DATE NOT NULL,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    activo BOOLEAN DEFAULT TRUE
);

-- USUARIO REMOTO PARA SPRING BOOT
DROP USER IF EXISTS 'hospital_user'@'%';
CREATE USER 'hospital_user'@'%' IDENTIFIED BY 'tu_password';
GRANT ALL PRIVILEGES ON proyecto_hospital.* TO 'hospital_user'@'%';
FLUSH PRIVILEGES;