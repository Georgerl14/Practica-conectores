CREATE TABLE IF NOT EXISTS ALUMNOS
(
  Id int(10) NOT NULL primary key,
  Id_Escuela int(10),
  Nombre VARCHAR(30),
  PrimerApellido VARCHAR(30),
  SegundoApellido VARCHAR(30),
  codigoPostal int(10) NOT NULL
) ;

CREATE TABLE IF NOT EXISTS ESCUELAS
(
  Id_Escuela int(10) NOT NULL primary key,
  Nombre VARCHAR(30),
  Provincia VARCHAR(30)
);

ALTER TABLE ALUMNOS
	ADD FOREIGN KEY (Id_Escuela)
	REFERENCES ESCUELAS (Id_Escuela)
	ON UPDATE CASCADE
	ON DELETE RESTRICT;
    
commit;