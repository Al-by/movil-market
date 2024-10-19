create database MovilMarket
-- drop database MovilMarket
use MovilMarket

-- Crear tabla de proveedores
CREATE TABLE proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    contacto VARCHAR(100),
    telefono VARCHAR(15),
    email VARCHAR(100),
    direccion VARCHAR(200)
);

-- Crear tabla de marcas (solo con el campo de nombre)
CREATE TABLE marcas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_marca VARCHAR(50) UNIQUE
);

-- Crear tabla de gama (sin descripción)
CREATE TABLE gama (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_gama VARCHAR(50) UNIQUE
);

-- Crear tabla de celulares (relacionada con marcas y gama)
CREATE TABLE celulares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(50),
    imei VARCHAR(15) UNIQUE,
    fecha_ingreso DATE,
    precio DECIMAL(10, 2),
    stock INT,
    proveedor_id INT,
    marca_id INT,
    gama_id INT,
    FOREIGN KEY (proveedor_id) REFERENCES proveedores(id),
    FOREIGN KEY (marca_id) REFERENCES marcas(id),
    FOREIGN KEY (gama_id) REFERENCES gama(id)
);

-- Crear tabla de clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    telefono VARCHAR(15),
    email VARCHAR(100),
    direccion VARCHAR(200)
);

-- Crear tabla de ventas (relacionada con celulares)
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    celular_id INT,
    fecha_venta DATE,
    cantidad INT,
    precio_venta DECIMAL(10, 2),
    total DECIMAL(10, 2),
    FOREIGN KEY (celular_id) REFERENCES celulares(id)
);

-- Crear tabla intermedia para relacionar ventas con clientes
CREATE TABLE ventas_clientes (
    venta_id INT,
    cliente_id INT,
    PRIMARY KEY (venta_id, cliente_id),
    FOREIGN KEY (venta_id) REFERENCES ventas(id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- INSERTS

INSERT INTO marcas (nombre_marca) VALUES
('Samsung'),
('Apple'),
('Xiaomi'),
('Huawei'),
('Sony'),
('Motorola'),
('OnePlus'),
('Google'),
('Nokia'),
('LG');

INSERT INTO gama (nombre_gama) VALUES
('Alta'),
('Media'),
('Baja');

INSERT INTO proveedores (nombre, contacto, telefono, email, direccion) VALUES
('Proveedor A', 'Juan Pérez', '999888777', 'juan@proveedora.com', 'Av. Principal 123'),
('Proveedor B', 'María Gómez', '998877665', 'maria@proveedorb.com', 'Calle Secundaria 456'),
('Proveedor C', 'Pedro Sánchez', '997766554', 'pedro@proveedorc.com', 'Jr. Las Rosas 789'),
('Proveedor D', 'Lucía Herrera', '996655443', 'lucia@proveedord.com', 'Av. Los Olivos 987'),
('Proveedor E', 'Ana Martínez', '995544332', 'ana@proveedore.com', 'Calle Las Flores 654'),
('Proveedor F', 'Carlos Ramírez', '994433221', 'carlos@proveedorf.com', 'Jr. Las Amapolas 321'),
('Proveedor G', 'Elena Rodríguez', '993322110', 'elena@proveedorg.com', 'Av. Los Pinos 876'),
('Proveedor H', 'Fernando Díaz', '992211009', 'fernando@proveedorh.com', 'Calle Los Cedros 432'),
('Proveedor I', 'Sofía Aguilar', '991100998', 'sofia@proveedori.com', 'Av. La Paz 543'),
('Proveedor J', 'Manuel Torres', '990099887', 'manuel@proveedorj.com', 'Calle Los Tulipanes 234');

INSERT INTO celulares (modelo, imei, fecha_ingreso, precio, stock, proveedor_id, marca_id, gama_id) VALUES
('Galaxy S21', '123456789012345', '2024-01-15', 1200.00, 10, 1, 1, 3),  -- Alta
('iPhone 13', '987654321098765', '2024-01-16', 1500.00, 5, 2, 2, 3),   -- Alta
('Xiaomi Mi 11', '567890123456789', '2024-01-17', 800.00, 15, 3, 3, 2),  -- Media
('Huawei P40', '345678901234567', '2024-01-18', 1000.00, 8, 4, 4, 3),  -- Alta
('Sony Xperia 5', '234567890123456', '2024-01-19', 1100.00, 12, 5, 5, 3),  -- Alta
('Motorola Edge', '789012345678901', '2024-01-20', 700.00, 9, 6, 6, 2),  -- Media
('OnePlus 9', '890123456789012', '2024-01-21', 900.00, 7, 7, 7, 2),   -- Media
('Google Pixel 6', '012345678901234', '2024-01-22', 1400.00, 6, 8, 8, 3),  -- Alta
('Nokia G50', '432109876543210', '2024-01-23', 600.00, 20, 9, 9, 1),  -- Baja
('LG Wing', '210987654321098', '2024-01-24', 950.00, 4, 10, 10, 2);   -- Media

select * from celulares

INSERT INTO clientes (nombre, telefono, email, direccion) VALUES
('Carlos Ruiz', '987654321', 'carlos@gmail.com', 'Av. Libertad 123'),
('María López', '976543210', 'maria@gmail.com', 'Calle Independencia 456'),
('Pedro García', '965432109', 'pedro@gmail.com', 'Jr. La Unión 789'),
('Ana Sánchez', '954321098', 'ana@gmail.com', 'Av. Los Robles 101'),
('Juan Pérez', '943210987', 'juan@gmail.com', 'Calle La Paz 202'),
('Luis Díaz', '932109876', 'luis@gmail.com', 'Jr. Las Acacias 303'),
('Lucía Torres', '921098765', 'lucia@gmail.com', 'Av. Las Palmeras 404'),
('Fernando Martínez', '910987654', 'fernando@gmail.com', 'Calle Los Álamos 505'),
('Sofía Gómez', '999876543', 'sofia@gmail.com', 'Jr. Los Cedros 606'),
('Manuel Rodríguez', '988765432', 'manuel@gmail.com', 'Av. La Floresta 707');

INSERT INTO ventas (celular_id, fecha_venta, cantidad, precio_venta, total) VALUES
(1, '2024-02-01', 1, 1200.00, 1200.00),  -- Galaxy S21
(2, '2024-02-02', 1, 1500.00, 1500.00),  -- iPhone 13
(3, '2024-02-03', 2, 800.00, 1600.00),   -- Xiaomi Mi 11
(4, '2024-02-04', 1, 1000.00, 1000.00),  -- Huawei P40
(5, '2024-02-05', 1, 1100.00, 1100.00),  -- Sony Xperia 5
(6, '2024-02-06', 1, 700.00, 700.00),    -- Motorola Edge
(7, '2024-02-07', 2, 900.00, 1800.00),   -- OnePlus 9
(8, '2024-02-08', 1, 1400.00, 1400.00),  -- Google Pixel 6
(9, '2024-02-09', 3, 600.00, 1800.00),   -- Nokia G50
(10, '2024-02-10', 1, 950.00, 950.00);   -- LG Wing

INSERT INTO ventas_clientes (venta_id, cliente_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

SELECT * FROM marcas;
SELECT * FROM gama;
SELECT * FROM proveedores;
SELECT * FROM celulares;
SELECT * FROM clientes;
SELECT * FROM ventas;
SELECT * FROM ventas_clientes;
