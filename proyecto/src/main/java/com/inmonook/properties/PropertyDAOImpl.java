package com.inmonook.properties;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.google.protobuf.Internal.ListAdapter;
import com.inmonook.SpainProvinces;
import com.inmonook.connections.BDConnection;
import com.inmonook.connections.NoConexion;
import com.inmonook.user.Proprietor;
import com.inmonook.utils.PropertyDAO;

public class PropertyDAOImpl implements PropertyDAO {
	//private LinkedHashSet<Property> properties;
	private HashSet<Property> properties;
	private HashMap<Short, Property> propertiesByCode;
	
	
	//CONSTRUCTORS
	public PropertyDAOImpl() throws SQLException {
		//properties = new LinkedHashSet<Property>();
		//properties = new HashMap<Short, Property>();
		properties = new HashSet<Property>();
		propertiesByCode = new HashMap<Short, Property>();
		this.refresh();
	}
	
	
	
	//GETTERS SETTERS
	@Override
	public Property getByCode(short code) {
		try {
			return propertiesByCode.get(code).clone();
		} catch (CloneNotSupportedException e) {
			assert false : "No se ha podido clonar el objeto.";
			return null;
		}
	}
	@Override
	public ArrayList<Property> getList() {
		ArrayList<Property> ret = new ArrayList<Property>();
		Iterator<Property> it = this.getSortedByCode().iterator();
		
		while ( it.hasNext() ) {
			ret.add( it.next() );
		}
		
		return ret;
	}
	@Override
	public ArrayList<Property> getSortedByCode() {
		HashSet<Property> properties = (HashSet<Property>)this.properties.clone();
		TreeSet<Property> untidyProperties = new TreeSet<Property>( new PropertyCompareCode() );
		ArrayList<Property> orderedProperties = new ArrayList<Property>();
		
		for ( Property i : properties ) {
			untidyProperties.add(i);
		}
		
		for (Property i : untidyProperties) {
			orderedProperties.add(i);
		}
		
		return orderedProperties;
	}
	@Override
	public ArrayList<Property> getSortedByTitle() {
		HashSet<Property> properties = (HashSet<Property>)this.properties.clone();
		TreeSet<Property> untidyProperties = new TreeSet<Property>( new PropertyCompareTitle() );
		ArrayList<Property> orderedProperties = new ArrayList<Property>();
		
		for ( Property i : properties ) {
			untidyProperties.add(i);
		}
		
		for (Property i : untidyProperties) {
			orderedProperties.add(i);
		}
		
		return orderedProperties;
	}
	@Override
	public ArrayList<Property> getSortedByArea() {
		HashSet<Property> properties = (HashSet<Property>)this.properties.clone();
		TreeSet<Property> untidyProperties = new TreeSet<Property>( new PropertyCompareArea() );
		ArrayList<Property> orderedProperties = new ArrayList<Property>();
		
		for ( Property i : properties ) {
			untidyProperties.add(i);
		}
		
		for (Property i : untidyProperties) {
			orderedProperties.add(i);
		}
		
		return orderedProperties;
	}
	@Override
	public ArrayList<Property> getSortedByPrice() {
		HashSet<Property> properties = (HashSet<Property>)this.properties.clone();
		TreeSet<Property> untidyProperties = new TreeSet<Property>( new PropertyComparePrice() );
		ArrayList<Property> orderedProperties = new ArrayList<Property>();
		
		for ( Property i : properties ) {
			untidyProperties.add(i);
		}
		
		for (Property i : untidyProperties) {
			orderedProperties.add(i);
		}
		
		return orderedProperties;
	}
	
	
	
	//METODOS PROPIOS
	@Override
	public int upload(Property property) throws InvalidProperty, SQLException {
		refresh();
		int numRowInserted = 0;
		if ( this.propertiesByCode.containsKey(property.getCode()) ) {
			throw new InvalidProperty("Esta propiedad ya existe.");
		}
		
		try {
			Statement smt = BDConnection.connect();
			
			if (property.getClass() == ParkingGarage.class) {
				//INSERCION DE PROPIEDAD
				numRowInserted += smt.executeUpdate(
													"INSERT INTO property (code, title, area, rentable, price, description, property_type, country, province, city_town, street, proprietor_user_dni)"
												  + "				VALUES(" + property.getCode() + ","
												  + "					   '" + property.getTitle() + "',"
												  + "			           " + property.getArea() + ","
												  + "			           " + (property.isRentable() ? 1 : 0) + ","
												  + "			           " + property.getPrecie() + ","
												  + "			           '" + property.getDescription() + "',"
												  + "			           'PARKING_GARAGE',"
												  + "			           '" + property.getCountry() + "',"
												  + "			           '" + property.getProvince() + "',"
												  + "			           '" + property.getCityTown() + "',"
												  + "			           '" + property.getStreet() + "',"
												  + "			           '" + property.getProprietor().getDni() + "'"
												  + "					);"
												);
				
				//INSERCION DE PARKING O GARAJE
				numRowInserted += smt.executeUpdate(
													"INSERT INTO parking_garage (property_code, place) "
												  + "	VALUES(" + property.getCode() + ", " + ( ((ParkingGarage) property).getPlace()!=null ? "'" + ((ParkingGarage) property).getPlace() + "'" : null) + ");"
												   );
				
				try {
					numRowInserted += this.sumitImage(property.getCode(), "property_code", "photos", "photo_file_path", property.getPhotos() );
				} catch (InvalidProperty e) {
					System.out.println("El inmueble especificado no existe.");
					assert false : "El inmueble especificado no existe.";
				}
			} else if (property.getClass() == Flat.class) {
				//INSERCION DE PROPIEDAD
				numRowInserted += smt.executeUpdate(
													"INSERT INTO property (code, title, area, rentable, price, description, property_type, country, province, city_town, street, proprietor_user_dni)"
												  + "				VALUES(" + property.getCode() + ","
												  + "					   '" + property.getTitle() + "',"
												  + "			           " + property.getArea() + ","
												  + "			           " + (property.isRentable() ? 1 : 0) + ","
												  + "			           " + property.getPrecie() + ","
												  + "			           '" + property.getDescription() + "',"
												  + "			           'BUILDING',"
												  + "			           '" + property.getCountry() + "',"
												  + "			           '" + property.getProvince() + "',"
												  + "			           '" + property.getCityTown() + "',"
												  + "			           '" + property.getStreet() + "',"
												  + "			           '" + property.getProprietor().getDni() + "'"
												  + "					);"
												);
				
				//INSERCION DE CONSTRUCCION
				numRowInserted += smt.executeUpdate(
													"INSERT INTO building (property_code, build_conditions, terrain, last_reform_date, num_rooms, num_baths, num_bedrooms, pool, energy_certificate, building_type, inc_parking_garage)"
												  + "		VALUES(" + property.getCode() + ","
												  + " 	   		   '" + ((Building) property).getCondition() + "',"
												  + "		       " + ( ((Building) property).getTerrain()!=null ? "'" + ((Building) property).getTerrain() + "'" : null) + ", "
												  + "		       str_to_date('" + ((Building) property).getLastReformDate().format( DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") ) + "', '%d/%m/%Y %H:%i:%s'), "
												  + "		       " + ((Building) property).getNumRooms() + ", "
												  + "		       " + ((Building) property).getNumBaths() + ", "
												  + "		       " + ((Building) property).getNumBedrooms() + ", "
												  + "		       " + ((Building) property).havePool() + ", "
												  + "		       '" + ((Building) property).getEnergyCertificate() + "', "
												  + "		       'FLAT', "
												  + "		       " + ( ((Building) property).getIncludedPG()!=null ? ((Building) property).getIncludedPG() : null) + ");"
												   );
				
				//INSERCION DE PISO
				numRowInserted += smt.executeUpdate(
													"INSERT INTO flat (property_code, block, floor, ladder, door) "
												  + "		VALUES(" + property.getCode() + ","
												  + "			   " + ( ((Flat) property).getBlock()<0 ? null : ((Flat) property).getBlock() ) + ","
												  + "			   " + ((Flat) property).getFloor() + ","
												  + "			   " + ( ((Flat) property).getLadder()==-253 ? null : ((Flat) property).getLadder() ) + ","
												  + "			   '" + ((Flat) property).getDoor() + "');"
												   );
				
				this.properties.add(property);
				this.propertiesByCode.put(property.getCode(), property);
				try {
					numRowInserted += this.sumitImage(property.getCode(), "property_code", "photos", "photo_file_path", property.getPhotos() );
					numRowInserted += this.sumitImage(property.getCode(), "building_property_code", "blueprints", "building_property_code", ((Building) property).getBlueprints() );
				} catch (InvalidProperty e) {
					System.out.println("El inmueble especificado no existe.");
					assert false : "El inmueble especificado no existe.";
				}
			} else if (property.getClass() == House.class) {
				//INSERCION DE PROPIEDAD
				numRowInserted += smt.executeUpdate(
													"INSERT INTO property (code, title, area, rentable, price, description, property_type, country, province, city_town, street, proprietor_user_dni)"
												  + "				VALUES(" + property.getCode() + ","
												  + "					   '" + property.getTitle() + "',"
												  + "			           " + property.getArea() + ","
												  + "			           " + (property.isRentable() ? 1 : 0) + ","
												  + "			           " + property.getPrecie() + ","
												  + "			           '" + property.getDescription() + "',"
												  + "			           'BUILDING',"
												  + "			           '" + property.getCountry() + "',"
												  + "			           '" + property.getProvince() + "',"
												  + "			           '" + property.getCityTown() + "',"
												  + "			           '" + property.getStreet() + "',"
												  + "			           '" + property.getProprietor().getDni() + "'"
												  + "					);"
												);
				
				//INSERCION DE CONSTRUCCION
				numRowInserted += smt.executeUpdate(
													"INSERT INTO building (property_code, build_conditions, terrain, last_reform_date, num_rooms, num_baths, num_bedrooms, pool, energy_certificate, building_type, inc_parking_garage)"
												  + "		VALUES(" + property.getCode() + ","
												  + " 	   		   '" + ((Building) property).getCondition() + "',"
												  + "		       " + ( ((Building) property).getTerrain()!=null ? "'" + ((Building) property).getTerrain() + "'" : null) + ", "
												  + "		       str_to_date('" + ((Building) property).getLastReformDate().format( DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") ) + "', '%d/%m/%Y %H:%i:%s'), "
												  + "		       " + ((Building) property).getNumRooms() + ", "
												  + "		       " + ((Building) property).getNumBaths() + ", "
												  + "		       " + ((Building) property).getNumBedrooms() + ", "
												  + "		       " + ((Building) property).havePool() + ", "
												  + "		       '" + ((Building) property).getEnergyCertificate() + "', "
												  + "		       'HOUSE', "
												  + "		       " + ( ((Building) property).getIncludedPG()!=null ? ((Building) property).getIncludedPG() : null) + ");"
												   );
				
				numRowInserted += smt.executeUpdate(
													"INSERT INTO house (property_code, door, patio) "
												  + "	VALUES(" + property.getCode() + ", '" + ((House) property).getDoor() + "', " + ( ((House) property).havePool() ? 1 : 0) + ");"
												   );
				
				try {
					numRowInserted += this.sumitImage(property.getCode(), "property_code", "photos", "photo_file_path", property.getPhotos() );
					numRowInserted += this.sumitImage(property.getCode(), "building_property_code", "blueprints", "building_property_code", ((Building) property).getBlueprints() );
				} catch (InvalidProperty e) {
					System.out.println("El inmueble especificado no existe.");
					assert false : "El inmueble especificado no existe.";
				}
			} else {
				System.out.println("Ha ocurrido un error inesperado. No se ha podido insertar ningúna fila. Si el error permanece contacte con el desarrollador.");
				assert false : "Error al insertar su propiedad, no se actualizó ninguna fila. Las clases deben ser solo Pisos, Casas o Parkings/Garajes.";
				assert numRowInserted > 0 : "Se inseetó datos a pesar de haber fallado.";
				
				return numRowInserted;
			}
			BDConnection.disconnect();
		} catch (NoConexion e) {
			System.out.println("Sin conexión a la base de datos. Para más información contactar con el desarrollador.");
			assert false : e.getMessage();
			
			return 0;
		} catch (SQLException e) {
			System.out.println("Error al introducir el dato en la base de datos. Compruebe que los datos son correctos. Si el problema persiste, pongase en contacto co el desarrollador.");
			assert false : e.getMessage();
			
			return 0;
		}
		
		if (numRowInserted > 0) {
			try {
				this.refresh();
			} catch (SQLException e) {
				System.out.println("No se ha podido actulaizar los datos, intentelo mas tarde.");
			}
		}
		
		return numRowInserted;
	}
	@Override
	public int update(Property property) throws NoConexion, SQLException, InvalidProperty {
		refresh();
		int numRowUpdated = 0;
		
		if ( ! this.propertiesByCode.containsKey(property.getCode()) ) {
			throw new InvalidProperty("No existe esta propiedad.");
		}
		
		if ( property.getClass() != this.propertiesByCode.get( property.getCode() ).getClass() ) {
			throw new InvalidProperty("El tipo de propiedad pasada por parametros no coincide con el tipo de la propiedad original.");
		}
		
		Statement smt = BDConnection.connect();
		
		//ACTUALIZAR INMUEBLE
		smt.executeUpdate(
						   "UPDATE property SET title				 = '" + property.getTitle() + "',"
						 + "					area				 = " + property.getArea() + ","
						 + "                    rentable			 = " + (property.isRentable() ? 1 : 0) + ","
						 + "                    price				 = " + property.getPrecie() + ","
						 + "                    description		 	 = '" + property.getDescription() + "',"
						 + "                    country			 	 = '" + property.getCountry() +"',"
						 + "                    province			 = '" + property.getProvince() + "',"
						 + "                    city_town			 = '" + property.getCityTown() + "',"
						 + "                    street				 = '" + property.getStreet() + "',"
						 + "                    proprietor_user_dni  = '" + property.getProprietor().getDni() + "'"
						 + "	WHERE code = " + property.getCode() + ";"
						);
		
		if (property.getClass() == ParkingGarage.class) {
			//ACTUALIZAR PARKING O GARAJE
			numRowUpdated = smt.executeUpdate(
												"UPDATE parking_garage SET place = '" + ((ParkingGarage) property).getPlace() + "'"
											  + "	WHERE property_code = 3;"
											);
			
			if (numRowUpdated > 0) {
				try {
					this.propertiesByCode.replace(property.getCode(), property.clone() );
				} catch (CloneNotSupportedException e) {
					this.refresh();
				}
			}
		} else if (property.getClass() == Flat.class) {
			//ACTUALIZAR CONSTRUCCION
			smt.executeUpdate(
								"UPDATE building SET build_conditions   = " + ( ((Building) property).getCondition()!=null ? "'" + ((Building) property).getCondition() + "'" : null ) + ","
							  + "					 terrain			= " + ( ((Building) property).getTerrain()!=null ? "'" + ((Building) property).getTerrain() + "'" : null ) + ","
							  + "                    last_reform_date   = " + ((Building) property).getLastReformDate().getLong(ChronoField.EPOCH_DAY) + ","
							  + "                    num_rooms		    = " + ((Building) property).getNumRooms() + ","
							  + "                    num_baths		    = " + ((Building) property).getNumBaths() + ","
							  + "                    num_bedrooms	    = " + ((Building) property).getNumBedrooms() + ","
							  + "                    pool			    = " + ( ((Building) property).havePool() ? 1 : 0 ) + ","
							  + "                    energy_certificate = '" + ((Building) property).getEnergyCertificate() + "',"
							  + "                    inc_parking_garage = " + ((Building) property).getIncludedPG().getCode()
							  + "	WHERE property_code = " + ((Building) property).getCode() + ";"
							);
			
			//ACTUALIZAR PISO
			numRowUpdated = smt.executeUpdate(
												"UPDATE flat SET block  = " + ((Flat) property).getBlock() + ","
											  + "				 floor  = " + ((Flat) property).getFloor() + ","
											  + "                ladder = " + ((Flat) property).getLadder() + ","
											  + "                door   = '" + ((Flat) property).getDoor() + "'"
											  + "	WHERE property_code = " + ((Flat) property).getCode() + ";"
											);
			
			if (numRowUpdated > 0) {
				try {
					this.propertiesByCode.replace(property.getCode(), property.clone() );
				} catch (CloneNotSupportedException e) {
					this.refresh();
				}
			}
		} else if (property.getClass() == House.class) {
			//ACTUALIZAR CONSTRUCCION
			smt.executeUpdate(
								"UPDATE building SET build_conditions   = " + ( ((Building) property).getCondition()!=null ? "'" + ((Building) property).getCondition() + "'" : null ) + ","
							  + "					 terrain			= " + ( ((Building) property).getTerrain()!=null ? "'" + ((Building) property).getTerrain() + "'" : null ) + ","
							  + "                    last_reform_date   = str_to_date('" + ((Building) property).getLastReformDate().format( DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") ) + "','%d/%m/%Y %H:%i:%s'),"
							  + "                    num_rooms		    = " + ((Building) property).getNumRooms() + ","
							  + "                    num_baths		    = " + ((Building) property).getNumBaths() + ","
							  + "                    num_bedrooms	    = " + ((Building) property).getNumBedrooms() + ","
							  + "                    pool			    = " + ( ((Building) property).havePool() ? 1 : 0 ) + ","
							  + "                    energy_certificate = '" + ((Building) property).getEnergyCertificate() + "',"
							  + "                    inc_parking_garage = " + ((Building) property).getIncludedPG().getCode()
							  + "	WHERE property_code = " + ((Building) property).getCode() + ";"
							);
			
			//ACTUALIZAR CASA
			numRowUpdated = smt.executeUpdate(
												"UPDATE house SET door  = " + ( ((House) property).getDoor()!=null ? "'" + ((House) property).getDoor() + "'" : null) + ","
											  + "				  patio = " + ( ((House) property).havePatio() ? 1 : 0 )
											  + "	WHERE property_code = " + ((House) property).getCode() + ";"
											);
			
			if (numRowUpdated > 0) {
				try {
					this.propertiesByCode.replace(property.getCode(), property.clone() );
				} catch (CloneNotSupportedException e) {
					assert false : "No se ha podído clonar el objeto.";
					this.refresh();
				}
			}
		} else {
			System.out.println("Ha ocurrido un error inesperado. No se ha podido actualizar ningúna fila. Si el error permanece contacte con el desarrollador.");
			assert false : "Error al actualizar filas, no se actualizó ninguna fila. Las clases deben ser solo Pisos, Casas o Parkings/Garajes.";
		}
		BDConnection.disconnect();
		
		return numRowUpdated;
	}
	@Override
	public int delete(short propertyCode) throws InvalidProperty {
		int numRowsDeleted = 0;
		
		if ( ! this.propertiesByCode.containsKey(propertyCode) ) {
			throw new InvalidProperty("No existe ningun inmueble con el código especificado por argumentos.");
		}
		
		try {
			Statement smt = BDConnection.connect();
			
			numRowsDeleted += smt.executeUpdate(
												"DELETE FROM property WHERE code = " + propertyCode + ";"
											  );
		} catch (NoConexion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numRowsDeleted;
	}
	@Override
	public void refresh() throws SQLException {
		try {
			Statement smt = BDConnection.connect();
			HashMap<Short, ArrayList<File>> listPhotosByCode = null;
			HashMap<Short, ArrayList<File>> listBlueprintsByCode = null;
			ArrayDeque<Short[]> listPGByCode = new ArrayDeque<Short[]>();
			try {
				listPhotosByCode = loadImages("photos", "property_code", "photo_file_path");
				listBlueprintsByCode = loadImages("blueprints", "building_property_code", "blueprints_file_path");
			} catch (InvalidHashKey e) {
				System.out.println("No se han podido cargar algunas imagenes.");
				assert false : e.getMessage();
			} catch (SQLException e) {
				System.out.println("No se han podido cargar algunas imagenes.");
				assert false : e.getMessage();
			}
			ResultSet cursor = smt.executeQuery(
												"SELECT p.code,           		  	 p.title,   		  		  p.area, "
											  + "		p.rentable,       		  	 p.price,   		  		  p.description, "
											  + "		p.property_type,  		  	 p.country, 		 		  p.province, "
											  + "		p.city_town,      		  	 p.street,  		  		  u.name AS \"PROPRIETOR_NAME\", "
											  + "		u.dni AS \"PROPRIETOR_DNI\", b.build_conditions, 		  b.terrain, "
											  + "       b.last_reform_date,		  	 b.num_rooms,		  		  b.num_baths, "
											  + "       b.num_bedrooms,			  	 b.pool,			  		  b.energy_certificate, "
											  + "       b.building_type,			 b.inc_parking_garage,		  f.block AS \"FLAT_BLOCK\", "
											  + "       f.floor AS \"FLAT_FLOOR\",   f.ladder AS \"FLAT_LADDER\", f.door AS \"FALT_DOOR\", "
											  + "       h.door AS \"HOUSE_DOOR\",	 h.patio,					  pg.place "
											  + "	FROM property p "
											  + "	INNER JOIN proprietor prtor ON p.proprietor_user_dni = prtor.user_dni "
											  + "   INNER JOIN users u ON prtor.user_dni = u.dni "
											  + "	LEFT JOIN parking_garage pg ON pg.property_code = p.code "
											  + "   LEFT JOIN building b ON b.property_code = p.code "
											  + "   LEFT JOIN flat f ON f.property_code = b.property_code "
											  + "   LEFT JOIN house h ON h.property_code = b.property_code;"
											);
			
			
			while ( cursor.next() ) {
				if ( cursor.getString(7).equals("PARKING_GARAGE") ) {
					Proprietor proprietor = new Proprietor(null, cursor.getString(13), cursor.getString(12), null, null, -1, null, null, null);
					ArrayList<File> photos = listPhotosByCode.get( cursor.getShort(1) );
					ParkingGarage pG = new ParkingGarage(cursor.getString(8),		//pais
														 cursor.getString(9),		//provincia
														 cursor.getString(10),		//ciudad o pueblo
														 cursor.getString(11),		//calle
														 cursor.getShort(1),		//codigo
														 cursor.getString(2),		//titulo
														 cursor.getString(6),		//descripcion
														 cursor.getFloat(3),		//superficie
														 cursor.getBoolean(4),		//esta en alquiler
														 cursor.getDouble(5),		//precio
														 proprietor,				//propietario
														 photos,					//fotos
														 cursor.getString(30)		//lugar en parking
														 );
					
					this.propertiesByCode.put(pG.getCode(), pG);
					this.properties.add( pG );
				} else if ( cursor.getString(22).equals("FLAT") ) {
					Proprietor proprietor = new Proprietor(null, cursor.getString(13), cursor.getString(12), null, null, -1, null, null, null);
					ArrayList<File> photos = listPhotosByCode.get( cursor.getShort(1) );
					ArrayList<File> blueprints = listBlueprintsByCode.get( cursor.getShort(1) );
					Flat flat = new Flat(cursor.getString(8), 							//pais
										 cursor.getString(9), 							//provincia
										 cursor.getString(10), 							//ciudad o pueblo
										 cursor.getString(11), 							//calle
										 cursor.getShort(1), 							//codigo
										 cursor.getString(2), 							//tirulo
										 cursor.getString(6), 							//descripcion
										 cursor.getFloat(3), 							//superficie
										 cursor.getBoolean(4), 							//esta en alquiler
										 cursor.getDouble(5), 							//precio
										 proprietor, 									//propietario
										 photos, 										//fotos
										 cursor.getString(14), 							//estado
										 cursor.getString(15), 							//terreno
										 cursor.getTimestamp(16).toLocalDateTime(), 	//fecha de ultima reforma
										 cursor.getByte(17), 							//numero de habitaciones totales
										 cursor.getByte(18), 							//numero de baños
										 cursor.getByte(19), 							//numero de dormitorios
										 null, 											//parking o garaje
										 blueprints, 									//planos
										 cursor.getBoolean(20), 						//lleva piscina
										 cursor.getString(21).charAt(0),				//certificado energetico
										 cursor.getByte(24), 							//bloque
										 cursor.getByte(26), 							//escalera
										 cursor.getByte(25), 							//piso
										 cursor.getString(28)							//puerta
										 );
					
					this.propertiesByCode.put( flat.getCode(), flat);
					this.properties.add(flat);
					
					if (cursor.getShort(23) != 0) {
						listPGByCode.add( new Short[] {cursor.getShort(1), cursor.getShort(23)} );
					}
				} else if ( cursor.getString(22).equals("HOUSE") ) {
					Proprietor proprietor = new Proprietor(null, cursor.getString(13), cursor.getString(12), null, null, -1, null, null, null);
					ArrayList<File> photos = listPhotosByCode.get( cursor.getShort(1) );
					ArrayList<File> blueprints = listBlueprintsByCode.get( cursor.getShort(1) );
					House house = new House(cursor.getString(8), 							//pais
										    cursor.getString(9), 							//provincia
										    cursor.getString(10), 							//ciudad o pueblo
										    cursor.getString(11), 							//calle
										    cursor.getShort(1), 							//codigo
										    cursor.getString(2), 							//tirulo
										    cursor.getString(6), 							//descripcion
										    cursor.getFloat(3), 							//superficie
										    cursor.getBoolean(4), 							//esta en alquiler
										    cursor.getDouble(5), 							//precio
										    proprietor, 									//propietario
										    photos, 										//fotos
										    cursor.getString(14), 							//estado
										    cursor.getString(15), 							//terreno
										    cursor.getTimestamp(16).toLocalDateTime(), 		//fecha de ultima reforma
										    cursor.getByte(17), 							//numero de habitaciones totales
										    cursor.getByte(18), 							//numero de baños
										    cursor.getByte(19), 							//numero de dormitorios
										    null, 											//parking o garaje
										    blueprints, 									//planos
										    cursor.getBoolean(20), 							//lleva piscina
										    cursor.getString(21).charAt(0),					//certificado energetico
											cursor.getString(28), 							//puerta
											cursor.getBoolean(29) 							//lleva patio
											);
					
					this.properties.add(house);
					this.propertiesByCode.put(house.getCode(), house);
					
					if (cursor.getShort(23) != 0) {
						listPGByCode.add( new Short[] {cursor.getShort(1), cursor.getShort(23)} );
					}
				} else {
					assert false : "Error al cargar propiedad. No es un Parkin/Garaje, ni Piso ni Casa. Resultado omitido.";
				}
			}
			
			cursor.close();
			
			while ( ! listPGByCode.isEmpty() ) {
				if ( this.propertiesByCode.get( listPGByCode.peekLast()[1]).getClass() == ParkingGarage.class ) {
					( 
						(Building) this.propertiesByCode.get( listPGByCode.peekLast()[0] ) 
					).setIncludedPG( (ParkingGarage) this.propertiesByCode.get( listPGByCode.pollLast()[1]) );
				} else {
					assert false : "Se ha añadido una propiedad que no es un parking a la lista.";
					listPGByCode.removeLast();
				}
			}
			
		} catch (NoConexion e) {
			System.out.println("Sin conexión a la base de datos. Para más información contactar con el desarrollador.");
			//throw new AssertionError(e.getMessage(), e);
			//Mensaje de error tecnico oculto al usuario comun
			assert false : e.getMessage();
			//throw new NoConexion(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			BDConnection.disconnect();
		}
	}
	
	public int sumitImage(short propertyCode, String columnCodeName, String tableName, String columnPathName, ArrayList<File> listImages) throws InvalidProperty {
		int numRowsSumited = 0;
		if ( ! this.propertiesByCode.containsKey(propertyCode) ) {
			throw new InvalidProperty("No existe ningún inmueble con el código especificado por argumentos.");
		}
		
		if (listImages == null) {
			return 0;
		}
		
		try {
			Statement smt = BDConnection.connect();
			
			for (File i : listImages) {
				numRowsSumited += smt.executeUpdate(
														"INSERT INTO " + tableName + " (" + columnPathName + ", " + columnCodeName + ") "
													  + "	VALUES('" + i.getPath().replace('\\', '/') + "', " + propertyCode + ");"
													);
			}
		} catch (NoConexion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numRowsSumited;
	}

	private static HashMap<Short, ArrayList<File>> loadImages(String sqlTable, String sqlCodeColumnName, String sqlPathColumnName) throws SQLException, NoConexion, InvalidHashKey {
		if ( BDConnection.isConnected() ) {
			HashMap<Short, ArrayList<File>> codeToPhoto = new HashMap<Short, ArrayList<File>>();
			Statement smt = BDConnection.getStmConne();
			ResultSet cursor2 = smt.executeQuery("SELECT " + sqlCodeColumnName + ", " + sqlPathColumnName + " FROM " + sqlTable + ";");
				
			while ( cursor2.next() ) {
				if ( cursor2.getShort(1) == 0 ) {
					throw new InvalidHashKey("Clave nula, clave del parametro sqlCodeColumnName probablemente incorrecta.");
				}
				
				if ( codeToPhoto.containsKey( cursor2.getShort(1) ) ) {
					//codeToPhoto.put(cursor2.getShort(1), arrayList.add( new File( cursor2.getString(2)) ));
					codeToPhoto.get( cursor2.getShort(1) ).add( new File( cursor2.getString(2)) );
				} else {
					codeToPhoto.put(cursor2.getShort(1), new ArrayList<File>() );
					codeToPhoto.get( cursor2.getShort(1) ).add( new File( cursor2.getString(2)) );
				}
			}
				
			cursor2.close();
			
			return codeToPhoto;
		} else {
			HashMap<Short, ArrayList<File>> codeToPhoto = new HashMap<Short, ArrayList<File>>();
			Statement smt = BDConnection.connect();
			ResultSet cursor2 = smt.executeQuery("SELECT " + sqlCodeColumnName + ", " + sqlPathColumnName + " FROM " + sqlTable + ";");
				
			while ( cursor2.next() ) {
				if ( cursor2.getShort(1) == 0 ) {
					throw new InvalidHashKey("Clave nula, clave del parametro sqlCodeColumnName probablemente incorrecta.");
				}
				
				if ( codeToPhoto.containsKey( cursor2.getShort(1) ) ) {
					//codeToPhoto.put(cursor2.getShort(1), arrayList.add( new File( cursor2.getString(2)) ));
					codeToPhoto.get( cursor2.getShort(1) ).add( new File( cursor2.getString(2)) );
				} else {
					codeToPhoto.put(cursor2.getShort(1), new ArrayList<File>() );
					codeToPhoto.get( cursor2.getShort(1) ).add( new File( cursor2.getString(2)) );
				}
			}
				
			cursor2.close();
			BDConnection.disconnect();
			
			return codeToPhoto;
		}
	}

	private class PropertyCompareCode implements Comparator<Property> {

		public PropertyCompareCode() {}
		
		@Override
		public int compare(Property o1, Property o2) {
			if (o1 == null) {
				return 0;
			}
			
			//assert ( o1.getCode() - o2.getCode() ) == 0 : "o1 y o2 tienen el mismo codigo y no debería haber duplicados.";
			
			return o1.getCode() - o2.getCode();
		}
		
	}
	
	private class PropertyCompareTitle implements Comparator<Property> {

		public PropertyCompareTitle() {}
		
		@Override
		public int compare(Property o1, Property o2) {
			if (o1 == null) {
				return 0;
			}
			
			int comparator = o1.getTitle().compareTo( o2.getTitle() );
			if (comparator != 0) {
				return comparator;
			} else {
				assert ( o1.getCode() - o2.getCode() ) == 0 : "o1 y o2 tienen el mismo codigo y no debería haber duplicados.";
				return o1.getCode() - o2.getCode();
			}
		}
		
	}
	
	private class PropertyCompareArea implements Comparator<Property> {

		public PropertyCompareArea() {}
		
		@Override
		public int compare(Property o1, Property o2) {
			if (o1 == null) {
				return 0;
			}
			
			int comparator = Float.compare( o1.getArea(), o2.getArea() );
			if (comparator != 0) {
				return comparator;
			} else {
				assert ( o1.getCode() - o2.getCode() ) == 0 : "o1 y o2 tienen el mismo codigo y no debería haber duplicados.";
				return o1.getCode() - o2.getCode();
			}
		}
		
	}
	
	private class PropertyComparePrice implements Comparator<Property> {

		public PropertyComparePrice() {}
		
		@Override
		public int compare(Property o1, Property o2) {
			if (o1 == null) {
				return 0;
			}
			
			int comparator = Double.compare(o1.getPrecie(), o2.getPrecie());
			if (comparator != 0) {
				return comparator;
			} else {
				assert ( o1.getCode() - o2.getCode() ) == 0 : "o1 y o2 tienen el mismo codigo y no debería haber duplicados.";
				return o1.getCode() - o2.getCode();
			}
		}
		
	}
	
}
