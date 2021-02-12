package com.olmo.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		CRUD crud  =new CRUD();
		Statement sentencia = null;
		String resp="", consulta="",tabla="";
		String id="", titulo="",autor="";
		String  nombre="",pais="";
		 ResultSet resul = null;
		 Boolean fin = true;
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager
					.getConnection("jdbc:mysql://localhost/libros?useSSL=false&allowMultiQueries=true", "root", "root");

			// Preparamos la consulta
			sentencia = conexion.createStatement();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Hola, bienvenido a JDBC en consola!!!!!!!");
		
		do {

		System.out.println("Elige una de las tablas: LIBROS(1),AUTORES(2)");
		System.out.println("Pulsa '0' para salir si no quieres hacer nada más ;)");
		resp =sc.next();
		
		switch(resp) {
		case "0":
			fin=false;
			break;
			
		case "1":
			System.out.println("**** TABLA LIBROS ****");
			consulta= "SELECT * FROM libros";
			tabla="libros";
			try {
			crud.read(tabla, sentencia);
				System.out.println("Bueno y ahora que hacemos?");
				System.out.println("'1' Para CREAR un registro nuevo");
				System.out.println("'2' Para ACTUALIZAR un registro que ya exista");
				System.out.println("'3' Para BORRAR un registro");
				System.out.println("Pulsa '0' para salir si no quieres hacer nada más ;)");
				resp=sc.next();
				
				switch (resp) {
				case "0":
					fin=false;
				break;
				
				case "1": 
					System.out.println("Vale, para crear un libro necesito cosinas:");
					System.out.println("El id en la base de datos, ¡No se puede repetir!");
					id=sc.next();
					sc.nextLine();
					System.out.println("El titulo el libro");
					titulo=sc.next();
					sc.nextLine();
					System.out.println("El id del autor en la base de datos");
					System.out.println("Hay estos autores ;)");
					crud.read("autores", sentencia);
					System.out.println("Elige uno e introudce su id: ");
					autor=sc.nextLine();
					
				//	sentencia.executeUpdate(crud.create(tabla, sentencia, id, titulo, autor));
					crud.create(tabla, sentencia, id, titulo, autor);
					
					break;
					
				case "2":
					System.out.println("Vale, para actualizar un libro necesito saber su id:");
					System.out.println("Ahora mismo hay estos libros: ");
					crud.read("libros", sentencia);
					System.out.println("Pásame el id y lo areglamos en un momento :)");
					resp=sc.next();
					sc.nextLine();
					
					
					break;
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + resp);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						break;
						
			
						
		
		}
		
		}while(fin);
		
		
		
		

	}
}
