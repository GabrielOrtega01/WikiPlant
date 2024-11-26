package com.mongowikiplant.app.entity;

import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "cliente")
public class Cliente {
	@Id
	private String id;

	private String usuario;
	private String contrasena;

	private String tipoDocumento;
	private String numeroDocumento;
	private String primerApellido;
	private String segundoApellido;
	private String primerNombre;
	private String segundoNombre;
	private String correoElectronico;
	private String numeroTelefonico;

	
	private Map<String, String> notasPlantas = new HashMap<>();
	private Set<String> plantasFavoritas = new HashSet<>();
	
	
	
	public Cliente() {
		super();
	}

	public Cliente(String id, String usuario, String contrasena, String tipoDocumento, String numeroDocumento,
            String primerApellido, String segundoApellido, String primerNombre, String segundoNombre,
            String correoElectronico, String numeroTelefonico, String numeroRegistro) {
    super();
    this.id = id;
    this.usuario = usuario;
    this.contrasena = contrasena;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.primerApellido = primerApellido;
    this.segundoApellido = segundoApellido;
    this.primerNombre = primerNombre;
    this.segundoNombre = segundoNombre;
    this.correoElectronico = correoElectronico;
    this.numeroTelefonico = numeroTelefonico;
    this.notasPlantas = new HashMap<>(); // Inicializamos el mapa de notas
}




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public Map<String, String> getNotasPlantas() {
        return notasPlantas;
    }

    public void setNotasPlantas(Map<String, String> notasPlantas) {
        this.notasPlantas = notasPlantas;
    }
    public Set<String> getPlantasFavoritas() {
        return plantasFavoritas;
    }

    public void setPlantasFavoritas(Set<String> plantasFavoritas) {
        this.plantasFavoritas = plantasFavoritas;
    }
}
