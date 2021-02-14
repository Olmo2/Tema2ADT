package com.olmo.ejercicio6;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mysql.jdbc.Statement;

public class InsertarXML {
	
	
	
	
	public void insertarXML(String archivo){
	
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager
					.getConnection("jdbc:mysql://localhost/test?useSSL=false&allowMultiQueries=true", "root", "root");

			// Preparamos la consulta
			Statement sentencia = (Statement) conexion.createStatement();

			String consulta = "Insert into autores (id,nombre,pais) Values( ";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(archivo));
			document.getDocumentElement().normalize();

			System.out.printf("Elemento raiz: %s %n", document.getDocumentElement().getNodeName());
			// crea una lista con todos los nodos empleado
			NodeList libros = document.getElementsByTagName("autor");
			System.out.printf("Nodos libro a recorrer: %d %n", libros.getLength());

			// recorrer la lista
			for (int i = 0; i < libros.getLength(); i++) {
				Node lib = libros.item(i); // obtener un nodo libro
				if (lib.getNodeType() == Node.ELEMENT_NODE) {// tipo de nodo
					// obtener los elementos del nodo
					Element elemento = (Element) lib;

					consulta = consulta.concat(elemento.getElementsByTagName("ID").item(0).getTextContent() + ", ");
					System.out.println(elemento.getElementsByTagName("ID").item(0).getTextContent());

					consulta = consulta.concat("'" + elemento.getElementsByTagName("nombre").item(0).getTextContent() + "', ");
					System.out.println(elemento.getElementsByTagName("nombre").item(0).getTextContent());

					consulta = consulta.concat("'" + elemento.getElementsByTagName("pais").item(0).getTextContent() + "'); ");
					System.out.println(elemento.getElementsByTagName("pais").item(0).getTextContent());

					System.out.println(consulta);
					sentencia.executeUpdate(consulta);

					consulta = "Insert into autores (id,nombre,pais) Values( ";

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	} 
}// fin de la clase