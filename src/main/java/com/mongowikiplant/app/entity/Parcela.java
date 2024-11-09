package com.mongowikiplant.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
@Document(collection = "parcela")
public class Parcela {

    @Id
    private String id;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @Positive(message = "Área debe ser un valor positivo")
    private double area;

    @NotBlank(message = "Ubicación es obligatoria")
    private String ubicacion;

    @NotBlank(message = "Tipo de suelo es obligatorio")
    private String tipoSuelo;
    
	public Parcela() {
		super();
	}
	
	public Parcela(String id, @NotBlank(message = "Nombre es obligatorio") String nombre,
			@Positive(message = "Área debe ser un valor positivo") double area,
			@NotBlank(message = "Ubicación es obligatoria") String ubicacion,
			@NotBlank(message = "Tipo de suelo es obligatorio") String tipoSuelo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.area = area;
		this.ubicacion = ubicacion;
		this.tipoSuelo = tipoSuelo;
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

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipoSuelo() {
		return tipoSuelo;
	}

	public void setTipoSuelo(String tipoSuelo) {
		this.tipoSuelo = tipoSuelo;
	}

}