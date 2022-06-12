package com.inmonook.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.inmonook.connections.BDConnection;
import com.inmonook.connections.NoConexion;
import com.inmonook.properties.Property;


public class UserDAOTemp {
	private String email, dni, name, password, phone, userType;
	private int money;
	private LocalDate dateBirth;
	private HashMap<Integer, Property> favorites;
	private HashMap<Short, Property> listPropertyes;
	
	
	
	//CONSTRUCTORS
	public UserDAOTemp(String email, String dni, String name, String password, String phone, int money, LocalDate dateBirth, String userType,
			HashMap<Integer, Property> favorites, HashMap<Short, Property> listPropertyes
	) throws NoConexion, SQLException, InvalidaPassException, InvalidNameException, UserExistException {
		super();
		
		if ( !isPassValid(password) ) {
			throw new InvalidaPassException("La contraseña debe tener al menos 3 caracteres.");
		}
		
		if ( !isValidName(name) ) {
			throw new InvalidNameException("El nombre no puede ser vacío.");
		}
		
		Statement smt = BDConnection.connect();
		
		ResultSet cursor = smt.executeQuery("SELECT u.dni FROM users u;");
		
		while (cursor.next() ) {
			if (dni.equals(cursor.getString("u.dni"))) {
				throw new UserExistException("El usuario que desea crear ya existe.");
			}
		}
			
		smt.executeUpdate(
				"INSERT INTO users (dni, email, name, password, phone, money, date_of_birthday, user_type) "
						+ "	VALUES('" + dni + "',"
						+ "		   '" + email + "', "
						+ "		   '" + name + "', "
						+ "		   '" + password + "', "
						+ "		   '" + phone + "', "
						+ "		   " + money + ", "
						+ "		   STR_TO_DATE('" + dateBirth.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") ) + "', '%d/%m/%Y'), "
						+ "		   '" + userType + "');"
				);

		
		this.email = email;
		this.dni = dni;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.money = money;
		this.dateBirth = dateBirth;
		this.favorites = favorites;
		this.listPropertyes = listPropertyes;
		this.userType = userType;
	}
	
	public UserDAOTemp(String dni, String password) throws InvalidaPassException, SQLException, UserNotFoundException {
		if ( !isPassValid(password) ) {
			throw new InvalidaPassException("La contraseña debe tener al menos 3 caracteres.");
		}
		
		try {
			Statement smt = BDConnection.connect();
			
			ResultSet cursor = smt.executeQuery(
														 "SELECT u.dni,   u.email,   u.name, 			   u.password, "
													   + "	     u.phone, u.money,   u.date_of_birthday, u.user_type"
													   + "	FROM users u"
													   + "		WHERE u.dni LIKE '" + dni +"';"
														);
			
			if ( cursor.next() ) {
				if( !cursor.getString("u.password").equals(password) ) {
					BDConnection.disconnect();
					throw new InvalidaPassException("La contraseña no es correcta.");
				}
				
				this.email = cursor.getString("u.email");
				this.dni = dni;
				this.name = cursor.getString("u.name");
				this.password = password;
				this.phone = cursor.getString("u.phone");
				this.money = cursor.getInt("u.money");
				this.dateBirth = cursor.getDate("u.date_of_birthday").toLocalDate();
				//this.favorites = favorites;
				this.listPropertyes = listPropertyes;
				this.userType = cursor.getString("u.user_type");
				
				cursor.close();
			} else {
				BDConnection.disconnect();
				throw new UserNotFoundException("No se ha encontrado este DNI en la BD.");
			}
		} catch (NoConexion e) {
			System.out.println("No hay conexión a la base de datos.");
		}
	}
	
	private static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		return !name.isBlank();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws SQLException, NoConexion {
		Statement smt = BDConnection.connect();

		smt.executeUpdate(
				"UPDATE users SET email  = " + email
				+ "	WHERE dni LIKE '" + this.dni + "';"
				);
		
		BDConnection.disconnect();

		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	@Deprecated
	public void setDni(String dni) throws NoConexion, SQLException {
		Statement smt = BDConnection.connect();

		smt.executeUpdate(
				"UPDATE users SET dni  = " + dni
				+ "	WHERE dni LIKE '" + this.dni + "';"
				);
		
		BDConnection.disconnect();

		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws NoConexion, SQLException {
		Statement smt = BDConnection.connect();

		smt.executeUpdate(
				"UPDATE users SET name  = " + name
				+ "	WHERE dni LIKE '" + this.dni + "';"
				);
		
		BDConnection.disconnect();
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws NoConexion, SQLException {
		Statement smt = BDConnection.connect();

		smt.executeUpdate(
				"UPDATE users SET password  = " + password
				+ "	WHERE dni LIKE '" + this.dni + "';"
				);
		
		BDConnection.disconnect();
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws NoConexion, SQLException {
		Statement smt = BDConnection.connect();

		smt.executeUpdate(
				"UPDATE users SET phone  = " + phone
				+ "	WHERE dni LIKE '" + this.dni + "';"
				);
		
		BDConnection.disconnect();
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) throws InvalidUserType, NoConexion, SQLException {
		if (!userType.toUpperCase().equals("AGENT")  &&  !userType.toUpperCase().equals("PROPRIETOR") ) {
			throw new InvalidUserType("El usuairo solo puede ser Agente inmobiliario o Proprietario.");
		}
		
		Statement smt = BDConnection.connect();

		smt.executeUpdate(
				"UPDATE users SET user_type = " + userType
				+ "	WHERE dni LIKE '" + this.dni + "';"
				);
		
		BDConnection.disconnect();
		
		this.userType = userType;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) throws NoConexion, SQLException {
		if ( money < 0) {
			Statement smt = BDConnection.connect();

			smt.executeUpdate(
					"UPDATE users SET money = " + 0
					+ "	WHERE dni LIKE '" + this.dni + "';"
					);
			
			BDConnection.disconnect();
			
			this.money = 0;
		} else {
			Statement smt = BDConnection.connect();

			smt.executeUpdate(
					"UPDATE users SET money = " + money
					+ "	WHERE dni LIKE '" + this.dni + "';"
					);
			
			BDConnection.disconnect();
			this.money = money;
		}
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) throws NoConexion, SQLException {
		Statement smt = BDConnection.connect();

		smt.executeUpdate(
				"UPDATE users SET STR_TO_DATE('" + dateBirth.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") ) + "', '%d/%m/%Y'), "
				+ "	WHERE dni LIKE '" + this.dni + "';"
				);
		
		BDConnection.disconnect();
		this.dateBirth = dateBirth;
	}

	public HashMap<Integer, Property> getFavorites() {
		return favorites;
	}

	public void setFavorites(HashMap<Integer, Property> favorites) {
		this.favorites = favorites;
	}

	public HashMap<Short, Property> getListPropertyes() {
		return listPropertyes;
	}

	public void setListPropertyes(HashMap<Short, Property> listPropertyes) {
		this.listPropertyes = listPropertyes;
	}
	
	private static boolean isPassValid(String pass) {
		try {
			if (pass.length() < 3) {
				return false;
			} else {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		}

	}
	
	public void deleteUser(String dni, String password) throws InvalidaPassException, InvalidNameException, SQLException, UserNotFoundException {
		if ( !isPassValid(password) ) {
			throw new InvalidaPassException("La contraseña debe tener al menos 3 caracteres.");
		}
		
		if ( !isValidName(name) ) {
			throw new InvalidNameException("El nombre no puede ser vacío.");
		}
		
		try {
			Statement smt = BDConnection.connect();
			
			ResultSet cursor = smt.executeQuery(
														 "SELECT u.dni,   u.email,   u.name, 			   u.password, "
													   + "	     u.phone, u.money,   u.date_of_birthday, u.user_type"
													   + "	FROM users u"
													   + "		WHERE u.dni LIKE '" + dni +"';"
														);
			
			if ( cursor.next() ) {
				if( !cursor.getString("u.password").equals(password) ) {
					BDConnection.disconnect();
					throw new InvalidaPassException("La contraseña no es correcta.");
				}
				
				smt.executeUpdate("DELETE FROM users WHERE dni = '" + dni + "';");
			} else {
				BDConnection.disconnect();
				throw new UserNotFoundException("No se ha encontrado este DNI en la BD.");
			}
		} catch (NoConexion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "UserDAOTemp [email=" + email + ", dni=" + dni + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", userType=" + userType + ", money=" + money + ", dateBirth=" + dateBirth + ", favorites="
				+ favorites + ", listPropertyes=" + listPropertyes + "]";
	}
	
	public void refresh() throws SQLException, InvalidaPassException, UserNotFoundException {
		try {
			Statement smt = BDConnection.connect();
			
			ResultSet cursor = smt.executeQuery(
														 "SELECT u.dni,   u.email,   u.name, 			   u.password, "
													   + "	     u.phone, u.money,   u.date_of_birthday, u.user_type"
													   + "	FROM users u"
													   + "		WHERE u.dni LIKE '" + this.dni +"';"
														);
			
			if ( cursor.next() ) {
				this.email = cursor.getString("u.email");
				this.dni = cursor.getString("u.dni");
				this.name = cursor.getString("u.name");
				this.password = cursor.getString("u,.password");
				this.phone = cursor.getString("u.phone");
				this.money = cursor.getInt("u.money");
				this.dateBirth = cursor.getDate("u.date_of_birthday").toLocalDate();
				//this.favorites = favorites;
				//this.listPropertyes = listPropertyes;
				this.userType = cursor.getString("u.user_type");
				
				cursor.close();
			} else {
				BDConnection.disconnect();
				throw new UserNotFoundException("No se ha encontrado este DNI en la BD.");
			}
		} catch (NoConexion e) {
			System.out.println("No hay conexión a la base de datos.");
		} finally {
			BDConnection.disconnect();
		}
	}
}
