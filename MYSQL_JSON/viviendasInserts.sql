# Clientes
INSERT INTO `cliente` (`nombre`, `apellido`, `vip`) 
VALUES ('Cliente1', 'Apellido1', false); 
INSERT INTO `cliente` (`nombre`, `apellido`, `vip`) 
VALUES ('Cliente2', 'Apellido2', true); 

# Viviendas
INSERT INTO `vivienda` (`calle`, `cp`, `metrosCuadrados`, `Cliente_id`) 
VALUES ('Calle1', '33001', 90, 1); 
INSERT INTO `vivienda` (`calle`, `cp`, `metrosCuadrados`, `Cliente_id`) 
VALUES ('Calle2', '33002', 120, 1); 

# Pólizas
INSERT INTO `poliza` (`id`,`fechaVencimiento`,`precioActual`, `precioRenovacion`, `descuento`, `Vivienda_id`) 
VALUES ('2','2021-04-20','300', '400', '0', 1); 

# Siniestros
INSERT INTO `siniestro` (`id`,`fechaCreacion`, `fechaVisitaTecnico`, `resuelto`, `Poliza_id`, `Tecnico_id`) 
VALUES ('1','2021-03-04', '2021-03-07', false, 1, 1); 

# Técnicos
INSERT INTO `tecnico` (`id`,`nombre`, `telefono`) 
VALUES ('1','Técnico1', '666666661'); 