package com.mongowikiplant.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Document(collection = "radiacion")
public class Radiacion {
	@Id
    private String id;
    private int fecha;  // Cambié de String a int para almacenar el año
    private List<MesCantidad> registros;

    // Map para almacenar mes -> cantidad
    private Map<String, Double> mesCantidadMap = new HashMap<>();

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public List<MesCantidad> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MesCantidad> registros) {
        this.registros = registros;
    }

    // Getter y Setter para mesCantidadMap
    public Map<String, Double> getMesCantidadMap() {
        return mesCantidadMap;
    }

    public void setMesCantidadMap(Map<String, Double> mesCantidadMap) {
        this.mesCantidadMap = mesCantidadMap;
    }

    // Clase estática embebida para representar Mes y Cantidad
    public static class MesCantidad {
        private String mes;
        private Double cantidad;

        // Constructor
        public MesCantidad() {}

        public MesCantidad(String mes, Double cantidad) {
            this.mes = mes;
            this.cantidad = cantidad;
        }

        // Getters y Setters
        public String getMes() {
            return mes;
        }

        public void setMes(String mes) {
            this.mes = mes;
        }

        public Double getCantidad() {
            return cantidad;
        }

        public void setCantidad(Double cantidad) {
            this.cantidad = cantidad;
        }
    }
}
