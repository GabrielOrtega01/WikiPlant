package com.mongowikiplant.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Document(collection = "fotoperiodos")
public class Fotoperiodo {

    @Id
    private String id;
    private String estacionId;  // ID de la estación a la que pertenece este fotoperiodo
    private int year;  // Año al que corresponde el fotoperiodo
    private List<MesCantidad> registros;  // Lista de registros mensuales
    private Map<String, Double> mesCantidadMap = new HashMap<>();  // Mapa de mes -> cantidad

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getEstacionId() {
        return estacionId;
    }

    public void setEstacionId(String estacionId) {
        this.estacionId = estacionId;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<MesCantidad> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MesCantidad> registros) {
        this.registros = registros;
    }

    public Map<String, Double> getMesCantidadMap() {
        return mesCantidadMap;
    }

    public void setMesCantidadMap(Map<String, Double> mesCantidadMap) {
        this.mesCantidadMap = mesCantidadMap;
    }

    // Método para calcular el promedio anual
    public double calcularPromedioAnual() {
        double suma = 0;
        int count = 0;
        for (String mes : List.of("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")) {
            Double valor = mesCantidadMap.get(mes);
            if (valor != null) {
                suma += valor;
                count++;
            }
        }
        return count > 0 ? suma / count : 0;
    }

    // Clase estática embebida para representar un registro de mes y cantidad
    public static class MesCantidad {
        private String mes;
        private Double cantidad;

        // Constructores
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
