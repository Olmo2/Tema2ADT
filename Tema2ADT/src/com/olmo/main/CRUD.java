package com.olmo.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.Scanner;

public class CRUD {
	
	String consulta;
	ResultSet resul = null;
	public String create(String tabla, Statement sentencia, String uno, String dos,String tres ) {
		if(tabla.equals("libros")) {
			consulta= "INSERT INTO " + tabla + " VALUES ( " + uno + ", '" + dos + "', " + tres + ");" ;
		}else
			consulta= "INSERT INTO " + tabla + " VALUES ( " + uno + ", '" + dos + "', '" + tres+"');" ;
		System.out.println(consulta);
		
		
		return consulta;
	}
	
	
	public void read(String tabla, Statement sentencia) throws SQLException {
	
		consulta= "SELECT * FROM " + tabla;
		System.out.println(consulta);
		resul= sentencia.executeQuery(consulta);
		
		resul.beforeFirst();
	
		while(resul.next()) {
			System.out.printf("Fila %d: %d, %s, %s %n",
					resul.getRow() ,
					resul.getInt(1) , 
					resul.getString(2) , 
					resul.getString(3));
			
		}
	
		}
		
	
	public String update(String tabla, Statement sentencia, String id , String uno, String dos) {
		
		
		if(tabla.equals("libros")) {
			consulta= "UPDATE " + tabla + "SET titulo= '" + uno +  "', autor_id='"+ dos +"'"+ "WHERE id= " + id+  ";";
		}else
			consulta= "UPDATE " + tabla + "SET nombre= '" + uno +  "', pais='"+ dos +"'"+ "WHERE id= " + id+  ";";
		
		
		return consulta;
	}
	public String delete(String consulta) {
		return null;
	}

}
