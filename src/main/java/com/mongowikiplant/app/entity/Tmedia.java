package com.mongowikiplant.app.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Document(collection = "tmedia")

//Temperatura media


public class Tmedia {


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

    // Método para actualizar el mapa mesCantidadMap
    public void actualizarMesCantidadMap() {
        mesCantidadMap = new HashMap<>();
        for (MesCantidad registro : registros) {
            mesCantidadMap.put(registro.getMes(), registro.getCantidad());
        }
    }
    
    
        // Método para calcular Desviación Estándar
        public double calcularDesviacionEstandar() {
            double promedio = calcularPromedioAnual();
            double sumaCuadrados = 0;
            int count = 0;

            for (Double cantidad : mesCantidadMap.values()) {
                if (cantidad != null) {
                    sumaCuadrados += Math.pow(cantidad - promedio, 2);
                    count++;
                }
            }

            return count > 0 ? Math.sqrt(sumaCuadrados / count) : 0;
        }

        // Método para calcular Coeficiente de Variación (CV%)
        public double calcularCoeficienteVariacion() {
            double promedio = calcularPromedioAnual();
            double desviacionEstandar = calcularDesviacionEstandar();
            return promedio > 0 ? (desviacionEstandar / promedio) * 100 : 0;
        }

        // Método para calcular el valor máximo
        public double calcularMaximo() {
            return mesCantidadMap.values().stream()
                    .filter(cantidad -> cantidad != null)
                    .max(Double::compare)
                    .orElse(0.0);
        }

        // Método para calcular el valor mínimo
        public double calcularMinimo() {
            return mesCantidadMap.values().stream()
                    .filter(cantidad -> cantidad != null)
                    .min(Double::compare)
                    .orElse(0.0);
        }
    }



