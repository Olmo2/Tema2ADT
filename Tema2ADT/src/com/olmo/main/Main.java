package com.olmo.main;

import java.sql.*;

public class Main {
	public static void main(String[] args) {
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/libros?useSSL=false", "root", "root");

			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM Autores";
			ResultSet resul = sentencia.executeQuery(sql);
			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros y se van visualizando
			while (resul.next()) {
				System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}
			System.out.println("N�MERO DE FILAS: " + resul.getRow());
			
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

			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexi�n

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// fin de main
}// fin de la clase
