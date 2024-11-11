package com.mongowikiplant.app.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "estacion")


public class Estacion {

	@Id
	private String id;
	private String nombre;
	private String tipo;
	private Double latitud;
	private Double longitud;
	private Double altitud;
	private String periodo;
	
	
	public Estacion() {
		super();
	}
		
	public Estacion(String id, String nombre, String tipo, Double latitud, Double longitud, Double altitud,
			String periodo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
		this.periodo = periodo;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getAltitud() {
		return altitud;
	}
	public void setAltitud(Double altitud) {
		this.altitud = altitud;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}
