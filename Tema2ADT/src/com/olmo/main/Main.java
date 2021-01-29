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
	public static void main(String[] args) {
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo?useSSL=false&allowMultiQueries=true", "root", "root");
			

			// Preparamos la consulta
			sentencia = conexion.createStatement();
			
			String linea = null;
			File file = new File("C:\\Users\\Dams2\\Desktop\\crearTablas.sql");
			String sql = "SELECT * FROM Autores";
			BufferedReader br= null;
			try {
				br =  new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			StringBuilder strBuilder= new StringBuilder();
			String salto= System.getProperty("line.separator");
			
			try {
				while((linea = br.readLine()) !=null) {
					strBuilder.append(linea);
					strBuilder.append(salto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String consulta = strBuilder.toString();
			
			
			/*Ejercico 1*/
			 sentencia.executeUpdate(consulta);
			 
//			 /*Ejercicio 2*/
//			 System.out.println(insertarDatos("departamentos", 1 ,"Ventas","Oviedo"));
//			 System.out.println(modificarDatos("departamentos", 1 ,"Compras","Gijon"));
//			 System.out.println(borrarDatos("departamentos"));
//			 
//			 /*Ejercicio 3*/
//			 PreparedStatement stmt= conexion.prepareStatement("insert into departamentos values(?,?,?)");  
//			 stmt.setInt(1,1);//1 specifies the first parameter in the query  
//			 stmt.setString(2,"Ratan");  
//			 stmt.setString(3,"carloso"); 
//			 stmt.executeUpdate();
//			 
//			 /*Ejercicio 4*/
//			 CallableStatement statement = conexion.prepareCall("{call peruano()}");
//			 ResultSet resul = statement.executeQuery();
			
			
			
			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros y se van visualizando
//			while (resul.next()) {
//				System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
//			}
//			System.out.println("NÚMERO DE FILAS: " + resul.getRow());
			
//			resul.beforeFirst();
//			while(resul.next()) {
//				System.out.printf("Fila %d: %d, %s, %s %n",
//						resul.getRow() ,
//						resul.getInt(1) , 
//						resul.getString(2) , 
//						resul.getString(3));
//				
//				
//				
//			}

			//resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// fin de main
	
	
	public static String insertarDatos() {
		String consulta;
		String tabla;
		Scanner sc = new Scanner(System.in);
		System.out.println("Elige la tabla");
		
		
		tabla=sc.next();
		
		consulta="Select * from " + tabla + ";";
		try {
			System.out.println(sentencia.executeUpdate(consulta));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return consulta;
		
		
	}
	public  static String borrarDatos(String tabla) {
		String str = "Drop table " + tabla + ";" ;
		return str;
	}
	
	public static String modificarDatos(String tabla , int dept_no, String dnombre, String loc) {
		String str = "Update " +tabla + " SET dnombre='"+ dnombre + "', loc='" + loc + "' WHERE dept_no=" + dept_no + ";"; 
		return str;
	}
	
}// fin de la clase
