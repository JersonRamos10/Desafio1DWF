-- Create the database
CREATE DATABASE mydatabase;

-- usar la databese creada
USE mydatabase;

-- Create la tabla de Empleados 
CREATE TABLE Empleados (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    numeroDui VARCHAR(10) unique NOT NULL,
    nombrePersona VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    numeroTelefono VARCHAR(9) NOT NULL,
    correoInstitucional VARCHAR(50) NOT NULL,
    fechaNacimiento DATE NOT NULL
);

-- Create la tabla departamentos
CREATE TABLE Departamento (
    idDepartamento INT AUTO_INCREMENT PRIMARY KEY,
    nombreDepartamento VARCHAR(50) NOT NULL,
    descripcionDepartamento TEXT
);

-- Create la tabla de cargos 
CREATE TABLE Cargos (
    idCargo INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL,
    descripcionCargo TEXT,
    jefatura BOOLEAN NOT NULL
);

-- Create la tabla de tipos de contratacion
CREATE TABLE TipoContratacion (
    idTipoContratacion INT AUTO_INCREMENT PRIMARY KEY,
    tipoContratacion VARCHAR(100) NOT NULL
);

-- Create la tabla de contrataciones
CREATE TABLE Contrataciones (
    idContratacion INT AUTO_INCREMENT PRIMARY KEY,
    idDepartamento INT NOT NULL,
    idEmpleado INT NOT NULL,
    idCargo INT NOT NULL,
    idTipoContratacion INT NOT NULL,
    fechaContratacion DATE NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    estado BOOLEAN NOT NULL,
    FOREIGN KEY (idDepartamento) REFERENCES Departamento(idDepartamento),
    FOREIGN KEY (idEmpleado) REFERENCES Empleados(idEmpleado),
    FOREIGN KEY (idCargo) REFERENCES Cargos(idCargo),
    FOREIGN KEY (idTipoContratacion) REFERENCES TipoContratacion(idTipoContratacion)
);

-- Inserta valores en la tabla de Empledos
INSERT INTO Empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento)
VALUES ('12345678-9', 'Juan Perez', 'jperez', '1234-5678', 'jperez@empresa.com', '1985-05-10');
