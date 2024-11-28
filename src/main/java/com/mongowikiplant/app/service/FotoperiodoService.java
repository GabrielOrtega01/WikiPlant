package com.mongowikiplant.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongowikiplant.app.entity.Fotoperiodo;
import com.mongowikiplant.app.repository.FotoperiodoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FotoperiodoService {

  @Autowired
  private FotoperiodoRepository fotoperiodoRepository;

  public Map<String, Integer> calcularConteosTotales() {
    // Obtener todos los fotoperiodos de la base de datos
    List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findAll();

    // Inicializar el mapa de conteos totales por mes
    Map<String, Integer> conteosTotales = inicializarMapaConteos();

    for (Fotoperiodo fotoperiodo : fotoperiodos) {
      actualizarConteosMensuales(fotoperiodo, conteosTotales);
    }

    int totalAnual = conteosTotales.values().stream().mapToInt(Integer::intValue).sum();
    conteosTotales.put("anual", totalAnual);

    double promedioAnual = totalAnual / 12.0;
    conteosTotales.put("promedioAnual", (int) promedioAnual);

    return conteosTotales;
  }

  private Map<String, Integer> inicializarMapaConteos() {
    Map<String, Integer> conteosTotales = new HashMap<>();
    for (String mes : obtenerMeses()) {
      conteosTotales.put(mes, 0);
    }
    conteosTotales.put("anual", 0);
    return conteosTotales;
  }

  private void actualizarConteosMensuales(Fotoperiodo fotoperiodo, Map<String, Integer> conteos) {
    if (fotoperiodo.getEnero() > 0)
      conteos.put("enero", conteos.get("enero") + 1);
    if (fotoperiodo.getFebrero() > 0)
      conteos.put("febrero", conteos.get("febrero") + 1);
    if (fotoperiodo.getMarzo() > 0)
      conteos.put("marzo", conteos.get("marzo") + 1);
    if (fotoperiodo.getAbril() > 0)
      conteos.put("abril", conteos.get("abril") + 1);
    if (fotoperiodo.getMayo() > 0)
      conteos.put("mayo", conteos.get("mayo") + 1);
    if (fotoperiodo.getJunio() > 0)
      conteos.put("junio", conteos.get("junio") + 1);
    if (fotoperiodo.getJulio() > 0)
      conteos.put("julio", conteos.get("julio") + 1);
    if (fotoperiodo.getAgosto() > 0)
      conteos.put("agosto", conteos.get("agosto") + 1);
    if (fotoperiodo.getSeptiembre() > 0)
      conteos.put("septiembre", conteos.get("septiembre") + 1);
    if (fotoperiodo.getOctubre() > 0)
      conteos.put("octubre", conteos.get("octubre") + 1);
    if (fotoperiodo.getNoviembre() > 0)
      conteos.put("noviembre", conteos.get("noviembre") + 1);
    if (fotoperiodo.getDiciembre() > 0)
      conteos.put("diciembre", conteos.get("diciembre") + 1);

  }

  // =====================================
  // Métodos para cálculos de promedios
  // =====================================
  public Map<String, Double> calcularPromedioTotales() {
	    List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findAll();
	    Map<String, Double> promedios = inicializarMapaPromedios();

	    for (Fotoperiodo fotoperiodo : fotoperiodos) {
	        actualizarPromediosMensuales(fotoperiodo, promedios);
	    }

	    int totalFotoperiodos = fotoperiodos.size();
	    if (totalFotoperiodos > 0) {
	        ajustarPromediosMensuales(promedios, totalFotoperiodos);
	        ajustarPromedioAnual(promedios); // Agregamos el cálculo del promedio anual
	    }

	    return promedios;
	}

	private Map<String, Double> inicializarMapaPromedios() {
	    Map<String, Double> promedios = new HashMap<>();
	    for (String mes : obtenerMeses()) {
	        promedios.put(mes, 0.0);
	    }
	    promedios.put("anual", 0.0); // Inicializamos el promedio anual en 0.0
	    return promedios;
	}

	private void actualizarPromediosMensuales(Fotoperiodo fotoperiodo, Map<String, Double> promedios) {
	    promedios.put("enero", promedios.get("enero") + fotoperiodo.getEnero());
	    promedios.put("febrero", promedios.get("febrero") + fotoperiodo.getFebrero());
	    promedios.put("marzo", promedios.get("marzo") + fotoperiodo.getMarzo());
	    promedios.put("abril", promedios.get("abril") + fotoperiodo.getAbril());
	    promedios.put("mayo", promedios.get("mayo") + fotoperiodo.getMayo());
	    promedios.put("junio", promedios.get("junio") + fotoperiodo.getJunio());
	    promedios.put("julio", promedios.get("julio") + fotoperiodo.getJulio());
	    promedios.put("agosto", promedios.get("agosto") + fotoperiodo.getAgosto());
	    promedios.put("septiembre", promedios.get("septiembre") + fotoperiodo.getSeptiembre());
	    promedios.put("octubre", promedios.get("octubre") + fotoperiodo.getOctubre());
	    promedios.put("noviembre", promedios.get("noviembre") + fotoperiodo.getNoviembre());
	    promedios.put("diciembre", promedios.get("diciembre") + fotoperiodo.getDiciembre());
	}

	private void ajustarPromediosMensuales(Map<String, Double> promedios, int totalFotoperiodos) {
	    for (String mes : obtenerMeses()) {
	        promedios.put(mes, redondearAUnDecimal(promedios.get(mes) / totalFotoperiodos));
	    }
	}

	private void ajustarPromedioAnual(Map<String, Double> promedios) {
	    double sumaMensual = 0.0;
	    for (String mes : obtenerMeses()) {
	        sumaMensual += promedios.get(mes);
	    }
	    promedios.put("anual", redondearAUnDecimal(sumaMensual / 12)); // Calculamos y redondeamos el promedio anual
	}


  // =====================================
  // Métodos para cálculos de desviación estándar
  // =====================================
  public Map<String, Double> calcularDesviacionEstandar() {
    List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findAll();
    Map<String, Double> desviaciones = inicializarMapaPromedios();
    Map<String, Double> promedios = calcularPromedioTotales();

    for (String mes : obtenerMeses()) {
      double promedioMes = promedios.get(mes);
      desviaciones.put(mes, calcularDesviacionPorMes(fotoperiodos, mes, promedioMes));
    }

    desviaciones.put("anual", calcularDesviacionAnual(fotoperiodos, promedios.get("anual")));
    return desviaciones;
  }

  private double calcularDesviacionPorMes(List<Fotoperiodo> fotoperiodos, String mes, double promedio) {
    double sumaCuadrados = 0.0;

    for (Fotoperiodo fotoperiodo : fotoperiodos) {
      double valorMes = getValorMes(fotoperiodo, mes);
      sumaCuadrados += Math.pow(valorMes - promedio, 2);
    }

    return redondearAUnDecimal(Math.sqrt(sumaCuadrados / fotoperiodos.size()));
  }

  private double calcularDesviacionAnual(List<Fotoperiodo> fotoperiodos, double promedioAnual) {
    double sumaCuadrados = 0.0;

    for (Fotoperiodo fotoperiodo : fotoperiodos) {
      for (String mes : obtenerMeses()) {
        double valorMes = getValorMes(fotoperiodo, mes);
        sumaCuadrados += Math.pow(valorMes - promedioAnual, 2);
      }
    }

    return redondearAUnDecimal(Math.sqrt(sumaCuadrados / (fotoperiodos.size() * 12)));
  }

  // =====================================
  // Métodos utilitarios
  // =====================================
  private List<String> obtenerMeses() {
    return List.of("enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
        "octubre", "noviembre", "diciembre");
  }

  private double getValorMes(Fotoperiodo fotoperiodo, String mes) {
    return switch (mes) {
      case "enero" -> fotoperiodo.getEnero();
      case "febrero" -> fotoperiodo.getFebrero();
      case "marzo" -> fotoperiodo.getMarzo();
      case "abril" -> fotoperiodo.getAbril();
      case "mayo" -> fotoperiodo.getMayo();
      case "junio" -> fotoperiodo.getJunio();
      case "julio" -> fotoperiodo.getJulio();
      case "agosto" -> fotoperiodo.getAgosto();
      case "septiembre" -> fotoperiodo.getSeptiembre();
      case "octubre" -> fotoperiodo.getOctubre();
      case "noviembre" -> fotoperiodo.getNoviembre();
      case "diciembre" -> fotoperiodo.getDiciembre();
      default -> 0.0;
    };

  }

  // Método auxiliar para redondear a un decimal
  private double redondearAUnDecimal(double valor) {
    return Math.round(valor * 10.0) / 10.0;
  }

  public Map<String, Double> calcularDesviaciones() {
    List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findAll();
    Map<String, Double> desviaciones = inicializarMapaPromedios(); // Reutiliza este método para inicializar

    for (String mes : obtenerMeses()) {  
      double promedio = calcularPromedioPorMes(mes, fotoperiodos);
      double sumaDiferenciasCuadradas = 0.0;
      for (Fotoperiodo fotoperiodo : fotoperiodos) {
        double valorMes = obtenerValorMes(fotoperiodo, mes);
        sumaDiferenciasCuadradas += Math.pow(valorMes - promedio, 2);
      }
      desviaciones.put(mes, redondearAUnDecimal(Math.sqrt(sumaDiferenciasCuadradas / fotoperiodos.size())));
    }

    return desviaciones;
  }

  private double calcularPromedioPorMes(String mes, List<Fotoperiodo> fotoperiodos) {
    double suma = 0.0;
    for (Fotoperiodo fotoperiodo : fotoperiodos) {
      suma += obtenerValorMes(fotoperiodo, mes);
    }
    return suma / fotoperiodos.size();
  }

  private double obtenerValorMes(Fotoperiodo fotoperiodo, String mes) {
    switch (mes) {
      case "enero":
        return fotoperiodo.getEnero();
      case "febrero":
        return fotoperiodo.getFebrero();
      // Repite para los demás meses
      default:
        return 0.0;
    }
  }


  public Map<String, Double> calcularCoeficienteVariacion() {
    List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findAll();
    Map<String, Double> coeficienteDeVariacion = new HashMap<>();
    Map<String, Double> promedios = calcularPromedioTotales();
    Map<String, Double> desviaciones = calcularDesviacionEstandar();

    // Calcular el coeficiente de variación para cada mes
    for (String mes : obtenerMeses()) {
      double desviacion = desviaciones.get(mes);
      double promedio = promedios.get(mes);
      double cv = (promedio != 0) ? (desviacion / promedio) * 100 : 0; // Evitar división por cero
      coeficienteDeVariacion.put(mes, redondearAUnDecimal(cv));
    }

    // Calcular el coeficiente de variación anual
    double promedioAnual = promedios.get("anual");
    double desviacionAnual = desviaciones.get("anual");
    double cvAnual = (promedioAnual != 0) ? (desviacionAnual / promedioAnual) * 100 : 0;
    coeficienteDeVariacion.put("anual", redondearAUnDecimal(cvAnual));

    return coeficienteDeVariacion;
  }
}