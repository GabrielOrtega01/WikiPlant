package com.mongowikiplant.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDate;

@Document(collection = "planificacion")
public class Planificacion {

    @Id
    private String id;

    @DBRef(lazy = true)
    @NotNull(message = "Cliente es obligatorio")
    private Cliente cliente;

    @DBRef(lazy = true)
    @NotNull(message = "Parcela es obligatorio")
    private Parcela parcela;

    @DBRef(lazy = true)
    @NotNull(message = "Cultivo es obligatorio")
    private Cultivo cultivo;

    @NotNull(message = "Fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "Fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotBlank(message = "Estado es obligatorio")
    private String estado;

    // Constructor vacío y completo, Getters y Setters...

    public Planificacion() {}

	public Planificacion(String id, @NotNull(message = "Cliente es obligatorio") Cliente cliente,
			@NotNull(message = "Parcela es obligatorio") Parcela parcela,
			@NotNull(message = "Cultivo es obligatorio") Cultivo cultivo,
			@NotNull(message = "Fecha de inicio es obligatoria") LocalDate fechaInicio,
			@NotNull(message = "Fecha de fin es obligatoria") LocalDate fechaFin,
			@NotBlank(message = "Estado es obligatorio") String estado) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.parcela = parcela;
		this.cultivo = cultivo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}