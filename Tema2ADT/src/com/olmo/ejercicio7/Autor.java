package com.olmo.ejercicio7;

import java.io.Serializable;

public class Autor implements Serializable{

	
	private String id;
	private String nombre;
	private String pais;
	
	
	
	
	public Autor() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", pais=" + pais + "]";
	}
	
	
	
}
