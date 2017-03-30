create TABLE Materiales
( codigoMaterial VARCHAR,
  nombre VARCHAR,
  CONSTRAINT PKMATERIALES PRIMARY KEY (codigoMaterial)
);

-----------------------------------------------------------------------------------------------------

CREATE TABLE TipoMaterial
( codigoTM VARCHAR,
  Tmaterial VARCHAR,
  precio FLOAT,
  CONSTRAINT PKTipoMaterial PRIMARY KEY (codigoTM),
  CONSTRAINT FKTipoMaterial FOREIGN KEY (Tmaterial) REFERENCES Materiales (codigoMaterial) 
);

---------------------------------------------------------------------------------------------------

CREATE TABLE Bulto
( codigoBulto VARCHAR,
  tipoBulto INT,
  pesoBulto FLOAT,
  materialBulto VARCHAR,
  estado INT, --si es 1 = bulto comprado 0 = vendido
  CONSTRAINT PKBulto PRIMARY KEY (codigoBulto),
  CONSTRAINT FKBulto FOREIGN KEY (materialBulto) REFERENCES TipoMaterial (codigoTM) 
);

------------------------------------------------------------------------------------------------------------------

CREATE TABLE RegistroCompra
( numCompra INT,
  pesoTotal FLOAT,
  CONSTRAINT PKRegistroCompra PRIMARY KEY (numCompra)
);

-----------------------------------------------------------------------------------------------------------------

CREATE TABLE RegCompra_U_Bulto
( regCompra INT,
  bulto VARCHAR,
  CONSTRAINT FKRegCompra_U_Bulto FOREIGN KEY (regCompra) REFERENCES RegistroCompra (numCompra) ,
  CONSTRAINT FKRegCompra_U_Bulto2 FOREIGN KEY (bulto) REFERENCES Bulto (codigoBulto) 
);

-----------------------------------------------------------------------------------------------------------

CREATE TABLE TotalMaterialComprado
( materialComprado VARCHAR,
  regComp INT,
  pesoTotalC FLOAT,
  CONSTRAINT PKTotalMaterialComprado  PRIMARY KEY (materialComprado,regComp),
  CONSTRAINT FKTotalMaterialComprado1 FOREIGN KEY (materialComprado) REFERENCES Materiales,
  CONSTRAINT FKTotalMaterialComprado2 FOREIGN KEY (regComp) REFERENCES RegistroCompra
);

-------------------------------------------------------------------------------------------------------

CREATE TABLE Inventario
( material VARCHAR,
  cantidadT FLOAT,
  precioT FLOAT,
  CONSTRAINT PKInventario  PRIMARY KEY (material),
  CONSTRAINT FKInventario FOREIGN KEY (material) REFERENCES Materiales
);

------------------------------------------------------------------------------------------------------

CREATE TABLE Proveedor
( codigo VARCHAR,
  nombre VARCHAR,
  descripcion VARCHAR,
  CONSTRAINT PKProveedor  PRIMARY KEY (codigo)
);

------------------------------------------------------------------------------------------------------

CREATE TABLE ReporteCompra
( numero SERIAL,
  regCompra INTEGER,
  fecha DATE,
  proveedor VARCHAR,
  chofer VARCHAR,
  placaVehiculo VARCHAR,
  CONSTRAINT PKReporteCompra  PRIMARY KEY (numero),
  CONSTRAINT FKReporteCompra1 FOREIGN KEY (regCompra) REFERENCES RegistroCompra,
  CONSTRAINT FKReporteCompra2 FOREIGN KEY (proveedor) REFERENCES Proveedor
);

------------------------------------------------------------------------------------------------------

CREATE TABLE Usuarios
( identificacion VARCHAR,
  nombre VARCHAR,
  numTelefono FLOAT,
  puesto INTEGER,
  contraseña VARCHAR,
  CONSTRAINT PKUsuarios PRIMARY KEY (identificacion)
);

--------------------------------------------------------------------------------------  

  create sequence sec_numeroRegCompra
  start with 1
  increment by 1
  cycle;

------------------------------------------------------------------------------------   

INSERT INTO Usuarios VALUES ('12345','Daniel N.',23234323,1,'123d');
INSERT INTO Usuarios VALUES ('6789','Geovanny',92372,2,'456g');
INSERT INTO Usuarios VALUES ('777','Juan',20342032,3,'777f');

INSERT INTO Proveedor VALUES ('p01','Proveedor1','Alajuela');
INSERT INTO Proveedor VALUES ('p02','Proveedor2','Heredia');





select * from TotalMaterialComprado;
select * from Inventario;
 
 ------------------------------------------------------------------------------------------------------

create TABLE Cliente
( codigoC VARCHAR,
  nombreC VARCHAR,
  CONSTRAINT PKCliente  PRIMARY KEY (codigoC)
);

------------------------------------------------------------------------------------------------------

create TABLE Conductor
( identificacionConductor VARCHAR,
  nombreConductor VARCHAR,
  nacionalidadConductor VARCHAR,
  fechaNacimientoConductor DATE,
  CONSTRAINT PKConductor  PRIMARY KEY (identificacionConductor)
);

------------------------------------------------------------------------------------------------------

CREATE TABLE ListaEmpaque
( codigoListEm INT,
  fechaLE DATE,
  medioTransporte INT,
  clienteLE VARCHAR,
  destino VARCHAR,
  pesoBruto FLOAT,
  pesoNeto FLOAT,
  conductor VARCHAR,
  placa VARCHAR,
  marca VARCHAR,
  chasis VARCHAR,
  furgon VARCHAR,
  estado INTEGER,  --si es 1 = lista facturada / 0 = lista pendiente por facturar
  CONSTRAINT PKListaEmpaque PRIMARY KEY (codigoListEm),
  CONSTRAINT FKListaEmpaque FOREIGN KEY (clienteLE) REFERENCES Cliente (codigoC),
  CONSTRAINT FKListaEmpaque2 FOREIGN KEY (conductor) REFERENCES Conductor (identificacionConductor)
);

------------------------------------------------------------------------------------------------------

CREATE TABLE ListEmpaque_U_Bulto
( listEmpaque INT,
  bultoVendido VARCHAR,
  CONSTRAINT FKListEmpaque_U_Bulto FOREIGN KEY (listEmpaque) REFERENCES ListaEmpaque (codigoListEm) ,
  CONSTRAINT FKListEmpaque_U_Bulto2 FOREIGN KEY (bultoVendido) REFERENCES Bulto (codigoBulto) 
);

---------------------------------------------------------------

create or replace function ingresarTotalMaterialComprado(IN XtipoBultoComprado character varying, IN XregCompra integer,IN XpesoBulto FLOAT)-------Procedimiento que se llamará desde la aplicacion Java para insertar y/o actualizar el/los material(es) ingresados en el registro de Compra
RETURNS integer AS
$BODY$
  DECLARE
  v_cant int DEFAULT 0;
  BEGIN
SELECT COUNT(1) INTO v_cant FROM TotalMaterialComprado WHERE  regComp = XregCompra
and materialComprado = XtipoBultoComprado;
IF V_CANT > 0 THEN ------------si ya existe un registro con material XtipoBultoComprado actualice el pesototal 

UPDATE TotalMaterialComprado SET pesoTotalC = pesoTotalC + XpesoBulto
WHERE  regComp = XregCompra
and materialComprado = XtipoBultoComprado;
RETURN 0;
     END IF;
-- SINO
INSERT INTO TotalMaterialComprado(materialComprado,regComp,pesoTotalC)
VALUES (XtipoBultoComprado,XregCompra,XpesoBulto);

     RETURN 1;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
------------------------------------------------------------------------------------
   
create or replace function actualizarCantidadInv()
RETURNS TRIGGER AS $actualizarCantidadInv$
  DECLARE
  BEGIN
UPDATE Inventario SET cantidadT = cantidadT + NEW.pesoBulto,
precioT = precioT + (NEW.pesoBulto * TipoMaterial.precio) 
from Materiales,Bulto,TipoMaterial
where Inventario.material = TipoMaterial.Tmaterial 
and TipoMaterial.codigoTM = NEW.materialBulto;
   RETURN NEW;
  END;
$actualizarCantidadInv$ LANGUAGE plpgsql;
---------------------------------------------------------------------------------------------------

CREATE TRIGGER actualizarCantidadInv 
  BEFORE INSERT ON Bulto 
  for each row EXECUTE PROCEDURE actualizarCantidadInv();
------------------------------------------------------------------------------------

CREATE TABLE TotalMaterialVendido
( materialVendido VARCHAR,
  listEmpaque INT,
  cantBultosV INT,
  pesoTotalV FLOAT,
  precioUnid FLOAT,
  importe FLOAT,
  CONSTRAINT PKTotalMaterialVendido  PRIMARY KEY (materialVendido,listEmpaque),
  CONSTRAINT FKTotalMaterialVendido1 FOREIGN KEY (materialVendido) REFERENCES Materiales,
  CONSTRAINT FKTotalMaterialVendido2 FOREIGN KEY (listEmpaque) REFERENCES ListaEmpaque
);

---------------------------------------------------------------------------------------------------

create or replace function ingresarTotalMaterialVendido(IN XtipoBultoVendido character varying, IN XlistaEmpaque integer,IN XpesoBulto FLOAT)-------Procedimiento que se llamará desde la aplicacion Java para insertar y/o actualizar el/los material(es) ingresados en la lista de empaque
RETURNS integer AS
$BODY$
  DECLARE
  v_cant int DEFAULT 0;
  BEGIN
SELECT COUNT(1) INTO v_cant FROM TotalMaterialVendido WHERE  listEmpaque = XlistaEmpaque
and materialVendido = XtipoBultoVendido;
IF V_CANT > 0 THEN ------------si ya existe un registro con material XtipoBultoVendido actualice el pesototal 

UPDATE TotalMaterialVendido SET pesoTotalV = pesoTotalV + XpesoBulto,
		cantBultosV = cantBultosV + 1
WHERE  listEmpaque = listEmpaque
and materialVendido = XtipoBultoVendido;
RETURN 0;
     END IF;
-- SINO
INSERT INTO TotalMaterialVendido(materialVendido,listEmpaque,pesoTotalV,precioUnid,importe)
VALUES (XtipoBultoVendido,XlistaEmpaque,XpesoBulto,0.0,0.0);

     RETURN 1;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

INSERT INTO Materiales VALUES ('fbi','Fibra Blanco Impreso');
INSERT INTO Materiales VALUES ('fmp','Fibra Mezclado de Primera');
INSERT INTO Materiales VALUES ('fms','Fibra Mezclado Segunda');
INSERT INTO Materiales VALUES ('ftm','Fibra Termomecánica');
INSERT INTO Materiales VALUES ('fmepe','Fibra Mezclado Pega');
INSERT INTO Materiales VALUES ('ffm','Fibra Folder Manilla');
INSERT INTO Materiales VALUES ('fpi','Fibra Periodico Impreso');
INSERT INTO Materiales VALUES ('fc','Fibra Cartoncillo');

INSERT INTO Inventario VALUES ('fbi',0.0,0.0);
INSERT INTO Inventario VALUES ('fmp',0.0,0.0);
INSERT INTO Inventario VALUES ('fms',0.0,0.0);
INSERT INTO Inventario VALUES ('ftm',0.0,0.0);
INSERT INTO Inventario VALUES ('fmepe',0.0,0.0);
INSERT INTO Inventario VALUES ('ffm',0.0,0.0);
INSERT INTO Inventario VALUES ('fpi',0.0,0.0);
INSERT INTO Inventario VALUES ('fc',0.0,0.0);

INSERT INTO TipoMaterial VALUES ('Pfbi','fbi',135);
INSERT INTO TipoMaterial VALUES ('Sfbi','fbi',130);
INSERT INTO TipoMaterial VALUES ('Pfmp','fmp',110);
INSERT INTO TipoMaterial VALUES ('Sfmp','fmp',105);
INSERT INTO TipoMaterial VALUES ('Pfms','fms',60);
INSERT INTO TipoMaterial VALUES ('Sfms','fms',55);
INSERT INTO TipoMaterial VALUES ('Pftm','ftm',70);
INSERT INTO TipoMaterial VALUES ('Sftm','ftm',65);
INSERT INTO TipoMaterial VALUES ('Pfmepe','fmepe',50);
INSERT INTO TipoMaterial VALUES ('Sfmepe','fmepe',45);
INSERT INTO TipoMaterial VALUES ('Pffm','ffm',35);
INSERT INTO TipoMaterial VALUES ('Sffm','ffm',30);
INSERT INTO TipoMaterial VALUES ('Pfpi','fpi',70);
INSERT INTO TipoMaterial VALUES ('Sfpi','fpi',65);
INSERT INTO TipoMaterial VALUES ('Pfc','fc',35);
INSERT INTO TipoMaterial VALUES ('Sfc','fc',30);
