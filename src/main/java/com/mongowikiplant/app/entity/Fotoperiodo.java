package com.mongowikiplant.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Objects;
import java.util.stream.Stream;

@Document(collection = "fotoperiodo")
public class Fotoperiodo {

  @Id
  private String id;

  @Min(1900)
  @Max(2100)
  @Indexed
  private int fecha; // Año al que corresponde el fotoperiodo

  private Double enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre;

  @DBRef
  private Estacion estacion; // Referencia a la estación

  public Fotoperiodo() {
    // Constructor por defecto
  }

  public Fotoperiodo(String id, int fecha, Estacion estacion, Double enero, Double febrero,
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

  

  // Método para calcular el promedio anual
  public double calcularPromedioAnual() {
    return Stream
        .of(enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre)
        .filter(Objects::nonNull)
        .mapToDouble(Double::doubleValue)
        .average()
        .orElse(0.0); // Si no hay valores, retorna 0
  }

  // Método para inicializar todas las constantes mensuales con un valor fijo
  public void inicializarConstantesMensuales(double valorConstante) {
    this.enero = valorConstante;
    this.febrero = valorConstante;
    this.marzo = valorConstante;
    this.abril = valorConstante;
    this.mayo = valorConstante;
    this.junio = valorConstante;
    this.julio = valorConstante;
    this.agosto = valorConstante;
    this.septiembre = valorConstante;
    this.octubre = valorConstante;
    this.noviembre = valorConstante;
    this.diciembre = valorConstante;
  }

  // Método para verificar si todos los valores mensuales son positivos
  public boolean tieneValoresPositivos() {
    return Stream
        .of(enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre)
        .allMatch(val -> val != null && val > 0);
  }

  // Método para verificar si un mes tiene un valor no nulo y mayor que cero
  public boolean tieneValorParaMes(String mes) {
    switch (mes.toLowerCase()) {
      case "enero":
        return enero != null && enero > 0;
      case "febrero":
        return febrero != null && febrero > 0;
      case "marzo":
        return marzo != null && marzo > 0;
      case "abril":
        return abril != null && abril > 0;
      case "mayo":
        return mayo != null && mayo > 0;
      case "junio":
        return junio != null && junio > 0;
      case "julio":
        return julio != null && julio > 0;
      case "agosto":
        return agosto != null && agosto > 0;
      case "septiembre":
        return septiembre != null && septiembre > 0;
      case "octubre":
        return octubre != null && octubre > 0;
      case "noviembre":
        return noviembre != null && noviembre > 0;
      case "diciembre":
        return diciembre != null && diciembre > 0;
      default:
        return false;
    }
  }
//Getters y setters omitidos para no hacer esto tan largo

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
}
