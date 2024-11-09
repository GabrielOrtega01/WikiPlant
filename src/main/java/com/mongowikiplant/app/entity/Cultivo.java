package com.mongowikiplant.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Document(collection = "cultivo")
public class Cultivo {

    @Id
    private String id;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Tipo de planta es obligatorio")
    private String tipoPlanta;

    @Positive(message = "Tiempo de crecimiento debe ser un número positivo")
    private int tiempoCrecimientoDias;

    @NotBlank(message = "Temporada es obligatoria")
    private String temporada;
    
	public Cultivo() {
		super();
	}

	public Cultivo(String id, @NotBlank(message = "Nombre es obligatorio") String nombre,
			@NotBlank(message = "Tipo de planta es obligatorio") String tipoPlanta,
			@Positive(message = "Tiempo de crecimiento debe ser un número positivo") int tiempoCrecimientoDias,
			@NotBlank(message = "Temporada es obligatoria") String temporada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipoPlanta = tipoPlanta;
		this.tiempoCrecimientoDias = tiempoCrecimientoDias;
		this.temporada = temporada;
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

	public String getTipoPlanta() {
		return tipoPlanta;
	}

	public void setTipoPlanta(String tipoPlanta) {
		this.tipoPlanta = tipoPlanta;
	}

	public int getTiempoCrecimientoDias() {
		return tiempoCrecimientoDias;
	}

	public void setTiempoCrecimientoDias(int tiempoCrecimientoDias) {
		this.tiempoCrecimientoDias = tiempoCrecimientoDias;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

}