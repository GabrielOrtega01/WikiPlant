package com.mongowikiplant.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Objects;
import java.util.stream.Stream;

@Document(collection = "evapotranspiracion")
public class Evapotranspiracion {

    @Id
    private String id;

    @Min(1900)
    @Max(2100)
    private int fecha; 

    private Double enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre;

    @DBRef
    private Estacion estacion; // Referencia a la estación

    public Evapotranspiracion() {
        // Constructor por defecto
    }

    public Evapotranspiracion(String id, int fecha, Estacion estacion, Double enero, Double febrero,
            Double marzo, Double abril, Double mayo, Double junio, Double julio, Double agosto, Double septiembre,
            Double octubre, Double noviembre, Double diciembre) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.estacion = estacion;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.abril = abril;
        this.mayo = mayo;
        this.junio = junio;
        this.julio = julio;
        this.agosto = agosto;
        this.septiembre = septiembre;
        this.octubre = octubre;
        this.noviembre = noviembre;
        this.diciembre = diciembre;
    }

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

    public Double getEnero() {
        return enero;
    }

    public void setEnero(Double enero) {
        this.enero = enero;
    }

    public Double getFebrero() {
        return febrero;
    }

    public void setFebrero(Double febrero) {
        this.febrero = febrero;
    }

    public Double getMarzo() {
        return marzo;
    }

    public void setMarzo(Double marzo) {
        this.marzo = marzo;
    }

    public Double getAbril() {
        return abril;
    }

    public void setAbril(Double abril) {
        this.abril = abril;
    }

    public Double getMayo() {
        return mayo;
    }

    public void setMayo(Double mayo) {
        this.mayo = mayo;
    }

    public Double getJunio() {
        return junio;
    }

    public void setJunio(Double junio) {
        this.junio = junio;
    }

    public Double getJulio() {
        return julio;
    }

    public void setJulio(Double julio) {
        this.julio = julio;
    }

    public Double getAgosto() {
        return agosto;
    }

    public void setAgosto(Double agosto) {
        this.agosto = agosto;
    }

    public Double getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(Double septiembre) {
        this.septiembre = septiembre;
    }

    public Double getOctubre() {
        return octubre;
    }

    public void setOctubre(Double octubre) {
        this.octubre = octubre;
    }

    public Double getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(Double noviembre) {
        this.noviembre = noviembre;
    }

    public Double getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(Double diciembre) {
        this.diciembre = diciembre;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    // Tu clase permanece igual excepto por el método calcularPromedioAnual
    public Double calcularPromedioAnual() {
        return Stream
                .of(enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre)
                .filter(Objects::nonNull) // Filtra los valores nulos
                .mapToDouble(Double::doubleValue) // Convierte los valores a double
                .average() // Calcula el promedio
                .orElse(0.0); // Devuelve 0.0 si no hay valores
    }

    public long calcularN() {
        return Stream
                .of(enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre)
                .filter(Objects::nonNull)
                .count();
    }

    public Double calcularMedia() {
        return Stream
                .of(enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0); // Devuelve 0.0 si no hay valores
    }

    public Double calcularDesviacionEstandar() {
        double media = calcularMedia();
        return Math.sqrt(Stream
                .of(enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .map(valor -> Math.pow(valor - media, 2))
                .average()
                .orElse(0.0)); // Devuelve 0.0 si no hay valores
    }

}
