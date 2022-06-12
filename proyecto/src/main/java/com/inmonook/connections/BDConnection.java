package com.inmonook.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.CommunicationException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public abstract class BDConnection {
	//He cambiado la base de datos para que me funcione a mi ya que tengo una contraseña diferente y nombres diferentes
	private final static String STRING_CONNECTION = "jdbc:mysql://localhost:3306/proyecto_programacion_final";
	private final static String USER_BD = "root";
	private final static String PASSWORD_BD = "2222";
	private static Connection connection; // singleton

	public static Statement connect() throws NoConexion {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(STRING_CONNECTION, USER_BD, PASSWORD_BD);
			}
			return connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.err.println("Sin conexón a la base de datos.");
			//Deberas de decirle que pasa si no tienes conexion
			//e.printStackTrace();
			connection = null;
			
			throw new NoConexion("Sin conexión a la base de datos.");
		}
	}
	
	
	public static void disconnect() {
		if(connection!=null) {
			try {
				connection.close();
				connection=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				connection=null;
			}
		}
	}
	
	public static Statement getStmConne() {
		//Posible null, quizas hay que lanzar un throw en vez de usar try catch, arreglrlo en mi tiempo libre
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Ocurrió un error inesperado a la hora de crear una decalración a la base de datos. "
					+ "Por favor reporte el suceso a los desarrolladores para reparar el error. Gracias..");
			
			assert false : e.getMessage();
			return null;
		}
	}
	
	public static boolean isConnected() {
		if (connection != null) {
			return true;
		} else {
			return false;
		}
	}
}
