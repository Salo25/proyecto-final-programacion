package com;

import com.inmonook.gui.Window;

public class Main {
	private static Window mainWindow;

	public static void main(String[] args) {
		/*
		File f = new File(;
		
		try {
			System.out.println(f.createNewFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		mainWindow = new Window();
		
		
		
		
//		PropertyDAOImpl probar = new PropertyDAOImpl();
//		
//		Iterator<Property> i = probar.getSortedByArea();
//		while (i.hasNext()) {
//			System.out.println( i.next() );
//		}
//		System.out.println("\n\n\n\n------------------------------------------");
//		
//		Property p2 = probar.getByCode( (short) 2 );
//		
//		((House) p2).setDoor("10");
//		((House) p2).setCondition(BuildCondition.BAD);
//		((House) p2).setProprietor(new Proprietor("FETUCHINI@gmail.com", "6666666Z", "Hackeado", null, null, -500, null, null, null));
//		
//		
//		ArrayList<File> fotos = new ArrayList<File>();
//		
//		fotos.add( new File("./media/004_PROPRIETOR_MOLLET_02.jpg") );
//		fotos.add( new File("./media/004_PROPRIETOR_MOLLET_01.jpg") );
//		
//		System.out.println(fotos.get(0).getPath() );
//		System.out.println(fotos.get(1).getPath() );
//		assert fotos.get(0) != null : "Foto vacia en posicion 0.";
//		assert fotos.get(1) != null : "Foto vacia en posicion 1.";
//		
//		Property p3 = new Flat(SpainProvinces.BARCELONA, 
//							   "Mollet", 
//							   "Calle Mollet", 
//							   (short) 5,
//							   "Mi antigüa casa.", 
//							   "Casa que quiero alquilar", 
//							   350.45f, 
//							   true, 
//							   445.0, 
//							   new Proprietor(null, "11111111N", "Pepe", null, null, 0, null, null, null), 
//							   fotos, 
//							   BuildCondition.EXCELLENT, 
//							   null, 
//							   LocalDateTime.of(2004, 7, 13, 12, 32), 
//							   (byte) 8, 
//							   (byte) 1, 
//							   (byte) 2, 
//							   null, 
//							   null, 
//							   true, 
//							   'A', 
//							   (byte) 1, 
//							   (byte) 0, 
//							   (short) 0, 
//							   "0-H");
//		
//		
//		try {
//			int filaSubidas = probar.upload(p3);
//			
//			System.out.println("Filas actualizadas: " + filaSubidas);
//		} catch (InvalidProperty e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		/*
//		try {
//			int filaBorradas = probar.delete( (short)5 );
//			
//			System.out.println("Filas borradas: " + filaBorradas);
//		} catch (InvalidProperty e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		*/
//		
//		
//		/*
//		try {
//			probar.update(p2);
//		} catch (NoConexion e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidProperty e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		*/
//		Iterator<Property> j = probar.getSortedByArea();
//		while (j.hasNext()) {
//			System.out.println( j.next() );
//		}
//		
//		System.out.println("\n\n\n\n------------------------------------------\nY mi propiedad clonda\n");
//		System.out.println(p2);
//		
//		/*
//		UserDAOTemp u1 = null;
//		try {
//			u1 = new UserDAOTemp("salomn@gmail.com", "55555555Y", "Salomon", "Salomon", "645645645", 500, LocalDate.now(), "PROPRIETOR", null, null);
//		} catch (NoConexion e) {
//			System.out.println("No hay conexión a la base de datos.");
//		} catch (UserExistException e) {
//				System.out.println("El usuario ya existe.");
//		} catch (SQLException e) {
//			System.out.println("Ha ocurrido un error a¡con la base de datos.");
//		} catch (InvalidaPassException e) {
//			System.out.println( e.getMessage() );
//		} catch (InvalidNameException e) {
//			System.out.println( e.getMessage() );
//		} 
//		*/
//		/*
//		UserDAOTemp u1 = null;
//		try {
//			u1 = new UserDAOTemp("principePio@gmail.com", "77777777S", "Principe Pio", "pio", "123456789", 25000, LocalDate.now(), "AGENT", null, null);
//			
//			System.out.println("Se ha creado correctamente");
//		} catch (NoConexion e) {
//			System.out.println("No hay conexión a la base de datos.");
//		} catch (UserExistException e) {
//				System.out.println("El usuario ya existe.");
//		} catch (SQLException e) {
//			System.out.println("Ha ocurrido un error a¡con la base de datos.");
//		} catch (InvalidaPassException e) {
//			System.out.println( e.getMessage() );
//		} catch (InvalidNameException e) {
//			System.out.println( e.getMessage() );
//		} 
//		*/
//		
//		UserDAOTemp u1 = null;
//		try {
//			u1 = new UserDAOTemp("77777777S", "pio");
//			System.out.println("Login correcto.");
//			u1.deleteUser("77777777S", "pio");
//			System.out.println("Eliminado correctamente.");
//		} catch (InvalidaPassException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UserNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		/*
//		UserDAOTemp u1 = null;
//		try {
//			u1 = new UserDAOTemp("11111111N", "Pepe");
//		} catch (SQLException e) {
//			System.out.println("Ha ocurrido un error con la base de datos.");
//			e.printStackTrace();
//		} catch (InvalidaPassException e) {
//			System.out.println( e.getMessage() );
//		} catch (UserNotFoundException e) {
//			System.out.println("Usuario no encontrado.");
//		} 
//		System.out.println(u1);
//		
//		try {
//			u1.setPhone("343434343");
//		} catch (NoConexion e) {
//			System.out.println("No hay conexión a la base de datos.");
//		} catch (SQLException e) {
//			System.out.println("Ha ocurrido un error con la base de datos.");
//			e.printStackTrace();
//		}
//		
//		try {
//			u1.setMoney(50);
//		} catch (NoConexion e) {
//			System.out.println("No hay conexión a la base de datos.");
//		} catch (SQLException e) {
//			System.out.println("Ha ocurrido un error con la base de datos.");
//			e.printStackTrace();
//		}
//		
//		*/
	}

}
