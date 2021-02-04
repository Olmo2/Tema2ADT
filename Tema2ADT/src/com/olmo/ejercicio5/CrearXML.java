package com.olmo.ejercicio5;

import org.w3c.dom.*;

import com.mysql.jdbc.Statement;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.sql.*;

public class CrearXML {

	static Statement sentencia;

	public static void main(String args[]) throws IOException {

		// Cargar el driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		

		// Establecemos la conexion con la BD
		Connection conexion = DriverManager
				.getConnection("jdbc:mysql://localhost/libros?useSSL=false&allowMultiQueries=true", "root", "root");

		// Preparamos la consulta
		sentencia = (Statement) conexion.createStatement();

		// File file = new File("C:\\Users\\olmo\\Desktop\\libros.sql");
		String consulta = "SELECT * FROM Autores";

		System.out.println(consulta);
		ResultSet resul = sentencia.executeQuery(consulta);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Autores", null);
			document.setXmlVersion("1.0");
			String nombre,pais;
			Integer id;
			resul.beforeFirst();
			while (resul.next()) { // lectura del fichero

				// libro = (Libro) dataIS.readObject();
				id = resul.getInt(1);
				nombre = resul.getString(2);
				pais=resul.getString(3);
				Element raiz = document.createElement("autor"); // nodo libro
				document.getDocumentElement().appendChild(raiz);
				//Añadir ID
				CrearElemento("ID", Integer.toString(id), raiz, document);
				//Añadir titulo
				CrearElemento("nombre", nombre, raiz, document);
				//Añadir Autor_id
				CrearElemento("pais", pais, raiz, document);
			

			} // fin del for que recorre el fichero

			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Autores.xml"));

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}// fin de main

	// Inserción de los datos del empleado
	static void CrearElemento(String datoLibro, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoLibro);
		Text text = document.createTextNode(valor); // damos valor
		raiz.appendChild(elem); // pegamos el elemento hijo a la raiz
		elem.appendChild(text); // pegamos el valor
	}
}// fin de la clase
