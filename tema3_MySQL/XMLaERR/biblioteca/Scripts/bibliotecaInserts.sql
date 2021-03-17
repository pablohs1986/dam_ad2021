# Libros
INSERT INTO `libro` (`isbn`, `titulo`, `autor`, `numeroPaginas`, `fechaPublicacion`, `prestadoSiNo`, `fechaDevolucion`) 
VALUES ('000000001', 'Clean Code', 'Robert Martin', 400, '1998-09-12', 1, '2021-03-10'); 
INSERT INTO `libro` (`isbn`, `titulo`, `autor`, `numeroPaginas`, `fechaPublicacion`, `prestadoSiNo`, `fechaDevolucion`) 
VALUES ('000000002', 'The Pragmatic Programmer', 'David Thomas', 420, '2001-01-13', 0, '2021-02-1'); 
INSERT INTO `libro` (`isbn`, `titulo`, `autor`, `numeroPaginas`, `fechaPublicacion`, `prestadoSiNo`, `fechaDevolucion`) 
VALUES ('000000003', 'Java The Complete Reference', 'Herbert Schildt', 500, '2011-05-22', 1, '2021-03-19'); 
INSERT INTO `libro` (`isbn`, `titulo`, `autor`, `numeroPaginas`, `fechaPublicacion`, `prestadoSiNo`, `fechaDevolucion`) 
VALUES ('000000004', 'JavaScript: The Definitive Guide', 'David Flanagan', 480, '1995-07-25', 1, '2021-03-28'); 

# Socios
INSERT INTO `socio` (`codigoSocio`, `nombreSocio`, `apellidoSocio`, `telefono`) 
VALUES ('1', 'Pablo', 'Herrero Sánchez', 666111111); 
INSERT INTO `socio` (`codigoSocio`, `nombreSocio`, `apellidoSocio`, `telefono`) 
VALUES ('2', 'Manuela', 'Ferreras Zubizarreta', 666222222); 
INSERT INTO `socio` (`codigoSocio`, `nombreSocio`, `apellidoSocio`, `telefono`) 
VALUES ('3', 'Jose Clementino', 'Zuzunaga Sorní', 666333333); 
INSERT INTO `socio` (`codigoSocio`, `nombreSocio`, `apellidoSocio`, `telefono`) 
VALUES ('4', 'Laura María', 'Bonachera Pregonas', 666444444); 

# Prestamos
INSERT INTO `prestamo` (`libro_isbn`, `codigoSocio`) 
VALUES ('000000004', '1'); 
INSERT INTO `prestamo` (`libro_isbn`, `codigoSocio`) 
VALUES ('000000003', '2'); 
INSERT INTO `prestamo` (`libro_isbn`, `codigoSocio`) 
VALUES ('000000002', '3'); 