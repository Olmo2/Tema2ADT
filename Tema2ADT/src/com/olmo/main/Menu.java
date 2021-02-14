package com.olmo.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.xml.sax.SAXException;

import com.olmo.ejercicio5.CrearXML;
import com.olmo.ejercicio6.InsertarXML;
import com.olmo.ejercicio7.SaxJDBC;

public class Menu {

	public static void main(String[] args) {
		CRUD crud  =new CRUD();
		Statement sentencia = null;
		String resp="", consulta="",tabla="", archivo="";
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
		System.out.println("Menú de herramientas auxiliares(3)");
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
				System.out.println("Pulsa '0' para volver al anterior menú si no quieres hacer nada más ;)");
				resp=sc.next();
				
				switch (resp) {
				case "0":
					resp="";
				break;
				
				case "1": 
					System.out.println("Vale, para crear un libro necesito cosinas:");
					System.out.println("El id en la base de datos, ¡No se puede repetir!");
					id=sc.next();
					sc.nextLine();
					System.out.println("El titulo el libro");
					titulo=sc.nextLine();
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
					crud.read(tabla, sentencia);
					System.out.println("Pásame el id y lo areglamos en un momento :)");
					id=sc.next();
					sc.nextLine();
					
					crud.read(tabla, sentencia, id);
					System.out.println("Nuevo título: ");
					titulo=sc.nextLine();
					
					System.out.println("Nuevo Autor: ");
					System.out.println("Hay estos autores ;)");
					crud.read("autores", sentencia);
					System.out.println("Elige uno e introudce su id: ");
					autor=sc.nextLine();
					
					crud.update(tabla, sentencia, id, titulo, autor);
					
					break;
					
					
				case"3":
					System.out.println("**** TABLA LIBROS ****");
					crud.read(tabla, sentencia);
					
					System.out.println("¿Que registro quieres borrar?");
					id=sc.next();
					sc.nextLine();
					
					crud.delete(tabla, sentencia, id);
					
					break;
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + resp);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						break;
						
						
		case "2":
			System.out.println("**** TABLA AUTORES ****");
			consulta= "SELECT * FROM autores";
			tabla="autores";
			try {
			crud.read(tabla, sentencia);
				System.out.println("Bueno y ahora que hacemos?");
				System.out.println("'1' Para CREAR un registro nuevo");
				System.out.println("'2' Para ACTUALIZAR un registro que ya exista");
				System.out.println("'3' Para BORRAR un registro");
				System.out.println("Pulsa '0' para volver al anterior menú si no quieres hacer nada más ;)");
				resp=sc.next();
				
				switch (resp) {
				case "0":
					resp="";
				break;
				
				case "1": 
					System.out.println("Vale, para crear un autor necesito cosinas:");
					System.out.println("El id en la base de datos, ¡No se puede repetir!");
					id=sc.next();
					sc.nextLine();
					System.out.println("El nombre del autor");
					nombre=sc.nextLine();
					System.out.println("El Pais del autor");
					pais=sc.next();
					sc.nextLine();
					
					
				//	sentencia.executeUpdate(crud.create(tabla, sentencia, id, titulo, autor));
					crud.create(tabla, sentencia, id, nombre, pais);
					
					break;
					
				case "2":
					System.out.println("Vale, para actualizar un autor necesito saber su id:");
					System.out.println("Ahora mismo hay estos autores: ");
					crud.read(tabla, sentencia);
					System.out.println("Pásame el id y lo areglamos en un momento :)");
					id=sc.next();
					sc.nextLine();
					
					crud.read(tabla, sentencia, id);
					System.out.println("Nuevo nombre: ");
					nombre=sc.nextLine();
					
					System.out.println("Nuevo País: ");
				
					pais=sc.next();
					sc.nextLine();
					
					crud.update(tabla, sentencia, id, nombre, pais);
					
					break;
					
					
				case"3":
					System.out.println("**** TABLA AUTORES ****");
					
					crud.read(tabla, sentencia);
					
					System.out.println("¿Que registro quieres borrar?");
					id=sc.next();
					sc.nextLine();
					
					crud.delete(tabla, sentencia, id);
					
					break;
					
					
				
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + resp);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						break;
						
		case "3":
			
			System.out.println("Elige una función extra: ");
			System.out.println("Crear un Xml a partir del esquema actual(4)");
			System.out.println("Insertar datos desde un xml a la tabla autores usando DOM(5)");
			System.out.println("Insertar datos desde un xml a la tabla autores usando SAX(6)");
			System.out.println("Pulsa '0' para volver al menú principal");
			resp=sc.next();
			sc.nextLine();
		
			switch (resp) {
			case "0":
				resp="";
				break;
				
			case "4":
			CrearXML cX = new CrearXML();
				try {
					cX.crearXML();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			break;
			case "5":
				InsertarXML iX = new InsertarXML();
				System.out.println("Introduce la ruta ABSOLUTA del archivo que quieras leer usando DOM");
				System.out.println("'Autores.xml' para usar el xml disponible junto al proyecto");
				archivo=sc.nextLine();
				iX.insertarXML(archivo);
				
				break;
			case "6":
				SaxJDBC sJ = new SaxJDBC();
				System.out.println("Introduce la ruta ABSOLUTA del archivo que quieras leer usando SAX");
				System.out.println("'Autores.xml' para usar el xml disponible junto al proyecto");
				archivo=sc.nextLine();
				try {
					sJ.saxJDBC(archivo);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				break;
				
			

			default:
				throw new IllegalArgumentException("Unexpected value: " + resp);
			}
			
			break;
			
						
		
		}
		
		}while(fin);
		
		
		
		

	}
}
