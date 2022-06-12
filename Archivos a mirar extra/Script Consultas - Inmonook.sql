/* 
 * Consulta para ver una lista de todas las propiedades
 * independientemente del tipo de propiedad (si es un parking, piso, etc).
 */
SELECT p.code,           p.title,   p.area, 
	   p.rentable,       p.price,   p.description,
	   p.property_type,  p.country, p.province, 
       p.city_town,      p.street,  u.name AS "PROPRIETOR_NAME",
       u.dni AS "PROPRIETOR_DNI"
       FROM property p
       JOIN proprietor prtor ON p.proprietor_user_dni = prtor.user_dni
       JOIN users u ON prtor.user_dni = u.dni;


SELECT property_code, photo_file_path FROM photos;

# ACTUALIZAR PROPIEDADES EN GENERAL
UPDATE property SET title				= 'Parking Santos',
					area				= 25,
                    rentable			= 0,
                    price				= 45,
                    description 		= 'Parking de casa Nerja',
                    country				= 'SPAIN',
                    province			= 'Málaga',
                    city_town			= 'Nerja',
                    street				= 'Calle Europa',
                    proprietor_user_dni = '11111111N'
	WHERE code = 3;

# ACTUALIZAR PARKING
UPDATE parking_garage SET place = '2D' 
	WHERE property_code = 3;

#ACTUALIZAR CONSTRUCCION
UPDATE building SET build_conditions   = 'ACCEPTABLE', 
					terrain			   = NULL, 
                    last_reform_date   = str_to_date('05/12/2019', '%d/%m/%Y'), 
                    num_rooms		   = 11, 
                    num_baths		   = 2, 
                    num_bedrooms	   = 3, 
                    pool			   = 1, 
                    energy_certificate = 'B', 
                    inc_parking_garage = 3
	WHERE property_code = 2;

#ACTUALIZAR PISO
UPDATE flat SET block  = NULL, 
				floor  = 4, 
                ladder = NULL, 
                door   = 'E'
	WHERE property_code = 1;

#ACTUALIZAR CASA
UPDATE house SET door  = 2,
				 patio = 1
	WHERE property_code = 2;


   SELECT p.code,           		  p.title,   		  		 p.area, 
		   p.rentable,       		  p.price,   		  		 p.description,
		   p.property_type,  		  p.country, 		 		 p.province, 
		   p.city_town,      		  p.street,  		  		 u.name AS "PROPRIETOR_NAME",
		   u.dni AS "PROPRIETOR_DNI", b.build_conditions, 		 b.terrain,
           b.last_reform_date,		  b.num_rooms,		  		 b.num_baths,
           b.num_bedrooms,			  b.pool,			  		 b.energy_certificate,
           b.building_type,			  b.inc_parking_garage,		 f.block AS "FLAT_BLOCK",
           f.floor AS "FLAT_FLOOR",   f.ladder AS "FLAT_LADDER", f.door AS "FALT_DOOR",
           h.door AS "HOUSE_DOOR",	  h.patio,					 pg.place
       FROM property p
	   INNER JOIN proprietor prtor ON p.proprietor_user_dni = prtor.user_dni
       INNER JOIN users u ON prtor.user_dni = u.dni
	   LEFT JOIN parking_garage pg ON pg.property_code = p.code
       LEFT JOIN building b ON b.property_code = p.code
       LEFT JOIN flat f ON f.property_code = b.property_code
       LEFT JOIN house h ON h.property_code = b.property_code;
       
#INSERCION DE PROPIEDAD
INSERT INTO property (code, title, area, rentable, price, description, property_type, country, province, city_town, street, proprietor_user_dni) 
	VALUES(4,
		   'Titulo',
           350.45,
           1,
           445,
           'Casa que quiero alquilar',
           'BUILDING',
           'SPAIN',
           'Barcelona',
           'Mollet',
           'Calle Mollet del Valles',
           '11111111N'
		);

#INSERTAR IMAGENES
INSERT INTO photos (photo_file_path, property_code) VALUES('', 5);

#INSERCION DE PARKING O GARAJE
INSERT INTO parking_garage (property_code, place) VALUES( 51, '5B');

#INSERCION DE CONSTRUCCION
INSERT INTO building (property_code, build_conditions, terrain, last_reform_date, num_rooms, num_baths, num_bedrooms, pool, energy_certificate, building_type, inc_parking_garage) 
	VALUES(1, 'ACCEPTABLE', 'WOODED', str_to_date('01/03/2017 13:00:00', '%d/%m/%Y %H:%i:%s'), 5, 1, 2, 0, 'C', 'HOUSE', NULL);
    
#INSERCION DE PLANOS
INSERT INTO blueprints (blueprints_file_path, building_property_code) VALUES('', 5);
    
#INSERCION DE PISO
INSERT INTO flat (property_code, block, floor, ladder, door) 
	VALUES(51, 1, 5, 5, '5D');

#INSERCION DE CASA
INSERT INTO house (property_code, door, patio) VALUES(51, '5B', 1);

#BORRAR
DELETE FROM property WHERE code = 4;

#USUARIOS
SELECT u.dni,   u.email,   u.name, 			   u.password, 
	   u.phone, u.money,   u.date_of_birthday, u.user_type
	FROM users u;

#INSERCION DE USUARIOS
INSERT INTO users (u.dni, u.email, u.name, u.password, u.phone, u.money, u.date_of_birthday, u.user_type) 
	VALUES('', '', '', '', '', 5, STR_TO_DATE('--/--/----', '%d/%m/%Y'), '');
    
#BORRAR USUARIO
DELETE FROM users WHERE dni = '';