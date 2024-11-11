package com.mongowikiplant.app.service;

import com.mongowikiplant.app.entity.Estacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceHidricoService {

    @Autowired
    private EstacionRepository estacionesRepository;

  //  public double calcularBalanceHidricoMensual(String estacionId, int año, int mes) {
        // Obtenemos los datos de la estación correspondiente
   //   Estaciones estacion = estacionesRepository.findById(estacionId).orElseThrow(() -> new NotFoundException("Estación no encontrada"));

        // Calcular el balance hídrico con la información específica de precipitación y evapotranspiración
       // double precipitacion = estacion.getPrecipitacion(); // Método para obtener datos del mes y año
       // double evapotranspiracion = estacion.getEvapotranspiracion(); // Método para obtener datos del mes y año

     //   return precipitacion - evapotranspiracion;
    }

