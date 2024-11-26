package com.mongowikiplant.app.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planta")

public class Planta {
	@Id
	private String id;
	private String nomCientifico;
	private String nomComun;
	private String imagen;
	private String familia;
	private String origen;
	private String distribucion;
	private String adaptacion;
	private String madurez;
	private String altitud;
	private String fotoperiodo;
	private String radiacion;
	private String temperatura;
	private String precipitacion;
	private String humedad;
	private String textura;
	private String drenaje;
	private String ph;
	
	private int busquedas = 0;
	
	public Planta() {
		super();
	}

	public Planta(String id, String nomCientifico, String nomComun, String imagen, String familia, String origen,
            String distribucion, String adaptacion, String madurez, String altitud, String fotoperiodo,
            String radiacion, String temperatura, String precipitacion, String humedad, String textura,
            String drenaje, String ph, int busquedas) {
  super();
  this.id = id;
  this.nomCientifico = nomCientifico;
  this.nomComun = nomComun;
  this.imagen = imagen;
  this.familia = familia;
  this.origen = origen;
  this.distribucion = distribucion;
  this.adaptacion = adaptacion;
  this.madurez = madurez;
  this.altitud = altitud;
  this.fotoperiodo = fotoperiodo;
  this.radiacion = radiacion;
  this.temperatura = temperatura;
  this.precipitacion = precipitacion;
  this.humedad = humedad;
  this.textura = textura;
  this.drenaje = drenaje;
  this.ph = ph;
  this.busquedas = busquedas; // Valor inicial predeterminado
}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomCientifico() {
		return nomCientifico;
	}
	public void setNomCientifico(String nomCientifico) {
		this.nomCientifico = nomCientifico;
	}
	public String getNomComun() {
		return nomComun;
	}
	public void setNomComun(String nomComun) {
		this.nomComun = nomComun;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDistribucion() {
		return distribucion;
	}
	public void setDistribucion(String distribucion) {
		this.distribucion = distribucion;
	}
	public String getAdaptacion() {
		return adaptacion;
	}
	public void setAdaptacion(String adaptacion) {
		this.adaptacion = adaptacion;
	}
	public String getMadurez() {
		return madurez;
	}
	public void setMadurez(String madurez) {
		this.madurez = madurez;
	}
	public String getAltitud() {
		return altitud;
	}
	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}
	public String getFotoperiodo() {
		return fotoperiodo;
	}
	public void setFotoperiodo(String fotoperiodo) {
		this.fotoperiodo = fotoperiodo;
	}
	public String getRadiacion() {
		return radiacion;
	}
	public void setRadiacion(String radiacion) {
		this.radiacion = radiacion;
	}
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public String getPrecipitacion() {
		return precipitacion;
	}
	public void setPrecipitacion(String precipitacion) {
		this.precipitacion = precipitacion;
	}
	public String getHumedad() {
		return humedad;
	}
	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}
	public String getTextura() {
		return textura;
	}
	public void setTextura(String textura) {
		this.textura = textura;
	}
	public String getDrenaje() {
		return drenaje;
	}
	public void setDrenaje(String drenaje) {
		this.drenaje = drenaje;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	
	 public int getBusquedas() {
	        return busquedas;
	    }

	    public void setBusquedas(int busquedas) {
	        this.busquedas = busquedas;
	    }

	    public void incrementarBusquedas() {
	        this.busquedas++;
	    }
	
}

