package com.olmo.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {

	static Statement sentencia;
	ResultSet resul;
	String consulta, tabla;
	Boolean fin = false;
	int size;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			Main main = new Main();
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager
					.getConnection("jdbc:mysql://localhost/libros?useSSL=false&allowMultiQueries=true", "root", "root");

			// Preparamos la consulta
			sentencia = conexion.createStatement();

			String linea = null;
			File file = new File("C:\\Users\\olmo\\Desktop\\libros.sql");
			String sql = "SELECT * FROM Autores";
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			StringBuilder strBuilder = new StringBuilder();
			String salto = System.getProperty("line.separator");

			try {
				while ((linea = br.readLine()) != null) {
					strBuilder.append(linea);
					strBuilder.append(salto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String consulta = strBuilder.toString();

			/* Ejercico 1 */
			 sentencia.executeUpdate(consulta);
			 System.out.println(consulta);

			 /*Ejercicio 2*/
			main.insertarDatos();
			main.borrarDatos();
			main.modificarDatos();
			 
			 /*Ejercicio 3*/
			 PreparedStatement stmt= conexion.prepareStatement("insert into autores values(?,?,?)");  
			 stmt.setInt(1,9);//1 specifies the first parameter in the query  
			 stmt.setString(2,"Miguel de cervantes");  
			 stmt.setString(3,"ESP"); 
			 stmt.executeUpdate();
			 
			 /*Ejercicio 4*/
			 CallableStatement statement = conexion.prepareCall("{call AutoresESP()}");
			 ResultSet resul = statement.executeQuery();

			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros y se van visualizando
			while (resul.next()) {
				System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}
			System.out.println("NÚMERO DE FILAS: " + resul.getRow());

			resul.beforeFirst();
			while(resul.next()) {
				System.out.printf("Fila %d: %d, %s, %s %n",
						resul.getRow() ,
						resul.getInt(1) , 
						resul.getString(2) , 
						resul.getString(3));
				
				
				
			}

			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// fin de main

	public void insertarDatos() {

		while (!fin) {

			System.out.println("Elige la tabla");

			tabla = sc.next();
			tabla = tabla.toLowerCase();

			try {

				switch (tabla) {

				case "autores":
					consulta = "Select * from " + tabla + ";";
					resul = sentencia.executeQuery(consulta);
					resul.beforeFirst();
					while (resul.next()) {
						System.out.printf("Fila %d: %d, %s, %s %n", resul.getRow(), resul.getInt(1), resul.getString(2),
								resul.getString(3));

					}
					consulta = "insert into autores (id, Nombre, Pais) values( ";

					System.out.println("Estos son los valores que contiene la BD, ahora te toca a ti :)");
					System.out.println("Id a insertar: ");
					consulta = consulta.concat(sc.next() + ",");
					sc.nextLine();

					System.out.println("Nombre: ");
					consulta = consulta.concat("'" + sc.nextLine() + "' , ");

					System.out.println("Pais: ");
					consulta = consulta.concat("'" + sc.next() + "');");
					sc.nextLine();

					System.out.println(consulta);

					sentencia.executeUpdate(consulta);

					break;
				case "libros":
					consulta = "Select * from " + tabla + ";";
					resul = sentencia.executeQuery(consulta);
					resul.beforeFirst();
					while (resul.next()) {
						System.out.printf("Fila %d: %d, %s, %d %n ", resul.getRow(), resul.getInt(1),
								resul.getString(2), resul.getInt(3));
					}
					consulta = "insert into libros (id, titulo,autor_id) values( ";
					System.out.println("Estos son los valores que contiene la BD, ahora te toca a ti :)");

					System.out.println("Id a insertar: ");
					consulta = consulta.concat(sc.next() + ",");
					sc.nextLine();

					System.out.println("Título: ");
					consulta = consulta.concat("'" + sc.nextLine() + "' ,");

					System.out.println("Id del autor: ");
					consulta = consulta.concat(sc.next() + ");");
					sc.nextLine();

					System.out.println(consulta);

					sentencia.executeUpdate(consulta);

					break;

				case "encaboxar":
					fin = true;
					break;
				default:
					System.out.println("Esa tabla no existe, por faor elige entre 'Libros' & 'Autores'");
					System.out.println("Para salir teclea 'encaboxar'");
					break;

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void borrarDatos() {

		fin = false;

		while (!fin) {

			System.out.println("Elige la tabla");
			tabla = sc.next();
			tabla = tabla.toLowerCase();

			try {

				switch (tabla) {

				case "autores":
					consulta = "Select * from " + tabla + ";";
					resul = sentencia.executeQuery(consulta);
					resul.beforeFirst();

					while (resul.next()) {
						System.out.printf("Fila %d: %d, %s, %s %n", resul.getRow(), resul.getInt(1), resul.getString(2),
								resul.getString(3));

					}
					consulta = "delete from autores where ( id = ";

					System.out.println("Estos son los valores que contiene la BD, ahora te toca a ti :)");
					System.out.println("Id del autor que euires borrar: ");
					consulta = consulta.concat(sc.next() + ");");
					sc.nextLine();

					System.out.println(consulta);

					sentencia.executeUpdate(consulta);

					break;
				case "libros":
					consulta = "Select * from " + tabla + ";";
					resul = sentencia.executeQuery(consulta);
					resul.beforeFirst();

					while (resul.next()) {
						System.out.printf("Fila %d: %d, %s, %d %n ", resul.getRow(), resul.getInt(1),
								resul.getString(2), resul.getInt(3));
					}
					consulta = "delete from libros where ( id = ";
					System.out.println("Estos son los valores que contiene la BD, ahora te toca a ti :)");

					System.out.println("Id del libro que quieras borrar: ");
					consulta = consulta.concat(sc.next() + ");");
					sc.nextLine();

					System.out.println(consulta);

					sentencia.executeUpdate(consulta);

					break;

				case "encaboxar":
					fin = true;
					break;
				default:
					System.out.println("Esa tabla no existe, por faor elige entre 'Libros' & 'Autores'");
					System.out.println("Para salir teclea 'encaboxar'");
					break;

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void modificarDatos() {
		fin = false;
		String id;

		while (!fin) {

			System.out.println("Elige la tabla");
			tabla = sc.next();
			sc.hasNextLine();
			tabla = tabla.toLowerCase();

			try {

				switch (tabla) {

				case "autores":
					
					consulta = "Select * from " + tabla + ";";
					resul = sentencia.executeQuery(consulta);
					resul.beforeFirst();

					while (resul.next()) {
						System.out.printf("Fila %d: %d, %s, %s %n", resul.getRow(), resul.getInt(1), resul.getString(2),
								resul.getString(3));

					}

					System.out.println("Estos son los valores que contiene la BD, ahora te toca a ti :)");
					System.out.println("Id del autor que quieres editar: ");
					id = sc.next();
					sc.nextLine();
					
					
					consulta = "Select * from " + tabla + " where id = " + id + ";";
					resul = sentencia.executeQuery(consulta);
					
					System.out.println("Datos actuales:");
					while (resul.next()) {
						System.out.printf(" ID: %d,%n Nombre: %s, %n País: %s %n", resul.getRow(), resul.getInt(1), resul.getString(2),
								resul.getString(3));

					}
					consulta = "update autores set nombre = ";
					
					System.out.println("Nuevo Nombre: ");
					consulta= consulta.concat("'"  + sc.nextLine() + "' ," );
					
					System.out.println("Nuevo Pais: ");
					consulta= consulta.concat("'"  + sc.nextLine() + "' " );
				
					consulta= consulta.concat(" where id = " + id + ";" );
					
					

					System.out.println(consulta);

					sentencia.executeUpdate(consulta);

					break;
				case "libros":
					
					consulta = "Select * from " + tabla + ";";
					resul = sentencia.executeQuery(consulta);
					resul.beforeFirst();

					while (resul.next()) {
						System.out.printf("Fila %d: %d, %s, %s %n", resul.getRow(), resul.getInt(1), resul.getString(2),
								resul.getInt(3));

					}

					System.out.println("Estos son los valores que contiene la BD, ahora te toca a ti :)");
					System.out.println("Id del Libro que quieres editar: ");
					id = sc.next();
					sc.nextLine();
					
					
					consulta = "Select * from " + tabla + " where id = " + id + ";";
					resul = sentencia.executeQuery(consulta);
					
					System.out.println("Datos actuales:");
					while (resul.next()) {
						System.out.printf(" ID: %d %n Título: %s %n Autor: %d %n", resul.getInt(1), resul.getString(2),
								resul.getInt(3));

					}
					consulta = "update libros set titulo = ";
					
					System.out.println("Nuevo Título: ");
					consulta= consulta.concat("'"  + sc.nextLine() + "' ," );
					
					System.out.println("Nuevo Autor Id: ");
					consulta= consulta.concat("autor_id =  "+sc.nextLine());
				
					consulta= consulta.concat(" where id = " + id + ";" );
					
					

					System.out.println(consulta);

					sentencia.executeUpdate(consulta);
					break;

				case "encaboxar":
					fin = true;
					break;
				default:
					System.out.println("Esa tabla no existe, por faor elige entre 'Libros' & 'Autores'");
					System.out.println("Para salir teclea 'encaboxar'");
					break;

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}// fin de la clase
