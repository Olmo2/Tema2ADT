package com.olmo.ejercicio7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mysql.jdbc.Statement;

public class GestionContenido extends DefaultHandler {
	
	
	Autor autor;
	String consulta = "INSERT INTO autores (id, nombre, pais) VALUES(";
	ArrayList<Autor> autores = new ArrayList<Autor>();
	int lib;

	public GestionContenido() {
		super();
	}

	public void startDocument() {
		System.out.println("Comienzo del Documento XML");
	}

	public void endDocument() {
		System.out.println("Final del Documento XML");
		for (int i = 0; i < autores.size(); i++) {
			System.out.println(autores.get(i).toString());
			consulta = consulta.concat(autores.get(i).getId() + ", " + " '" + autores.get(i).getNombre() + "' ," + " '"
					+ autores.get(i).getPais() + "' );");

			System.out.println(consulta);
			consulta(consulta);
			
			consulta = "INSERT INTO autores (id, nombre, pais) VALUES(";
		}
	}

	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		System.out.printf("\t Principio Elemento: %s %n", nombre);

		if (nombre.equals("ID")) {
			autor = new Autor();
			lib = 1;
		} else if (nombre.equals("nombre")) {
			lib = 2;
			// p.libro.setAnio(Integer.parseInt(atts.getValue(0)));
		} else if (nombre.equals("pais")) {
			lib = 3;
		} else {
			lib = 4;
		}
	}

	public void endElement(String uri, String nombre, String nombreC) {
		lib = 4;
		if (nombre == "autor") {

			autores.add(autor);
		}
		System.out.printf("\tFin Elemento: %s %n", nombre);
	}

	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String(ch, inicio, longitud);
		// quitar saltos de línea
		car = car.replaceAll("[\t\n]", "");
		System.out.printf("\t Caracteres: %s %n", car);
		if (lib == 1) {

			autor.setId(car);
		} else if (lib == 2) {
			autor.setNombre(car);
		} else if (lib == 3) {
			autor.setPais(car);
		}

	}
	
	public void consulta(String consulta) {
		
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/test?useSSL=false&allowMultiQueries=true", "root", "root");
			// Preparamos la consulta
			Statement sentencia = (Statement) conexion.createStatement();
			
			sentencia.executeUpdate(consulta);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		 
		
	}

}// fin GestionContenido