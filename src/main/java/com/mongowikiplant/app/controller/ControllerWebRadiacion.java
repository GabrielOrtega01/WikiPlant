package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Radiacion;
import com.mongowikiplant.app.entity.Radiacion.MesCantidad;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.RadiacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/radiacion")
public class ControllerWebRadiacion {

    @Autowired
    private RadiacionRepository radiacionRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String radiacionCrearTemplate(Model model) {
        model.addAttribute("radiacion", new Radiacion());
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "radiacion-form";
    }

    @GetMapping("/lista")
    public String radiacionListTemplate(Model model) {
        List<Radiacion> radiaciones = radiacionRepository.findAll();
        radiaciones.forEach(Radiacion::actualizarMesCantidadMap);
        model.addAttribute("radiaciones", radiaciones);
        radiaciones.sort(Comparator.comparingInt(Radiacion::getYear));
        return "radiacion-lista";
    }

    @GetMapping("/edit/{id}")
    public String radiacionEditTemplate(@PathVariable("id") String id, Model model) {
        Radiacion radiacion = radiacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Radiación no encontrada"));
        model.addAttribute("radiacion", radiacion);
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "radiacion-form";
    }

    @GetMapping("/delete/{id}")
    public String radiacionDeleteProcess(@PathVariable("id") String id) {
        radiacionRepository.deleteById(id);
        return "redirect:/radiacion/lista";
    }

    @GetMapping("/info")
    public String radiacionInfoTemplate() {
        return "radiacion-info";
    }

    @PostMapping("/save")
    public String radiacionSaveProcess(@ModelAttribute Radiacion radiacion,
            @RequestParam(value = "year", required = false) int year,
            @RequestParam Map<String, String> allParams) {

        if (year != 0) {
            radiacion.setYear(year);
        }

        List<MesCantidad> registros = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String mes = allParams.get("mes" + i);
            Double cantidad = allParams.containsKey("cantidad" + i) ? Double.valueOf(allParams.get("cantidad" + i))
                    : null;
            if (mes != null && cantidad != null) {
                registros.add(new MesCantidad(mes, cantidad));
            }
        }

        radiacion.setRegistros(registros);
        radiacion.actualizarMesCantidadMap();
        radiacionRepository.save(radiacion);

        return "redirect:/radiacion/lista";
    }

    @GetMapping("/detalle/{id}")
    public String radiacionDetalleTemplate(@PathVariable("id") String id, Model model) {
        Radiacion radiacion = radiacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Radiación no encontrada"));

        // Calcular las estadísticas y agregarlas al modelo
        double promedioAnual = radiacion.calcularPromedioAnual();
        double desviacionEstandar = radiacion.calcularDesviacionEstandar();
        double coeficienteVariacion = radiacion.calcularCoeficienteVariacion();
        double maximo = radiacion.calcularMaximo();
        double minimo = radiacion.calcularMinimo();

        model.addAttribute("radiacion", radiacion);
        model.addAttribute("promedioAnual", promedioAnual);
        model.addAttribute("desviacionEstandar", desviacionEstandar);
        model.addAttribute("coeficienteVariacion", coeficienteVariacion);
        model.addAttribute("maximo", maximo);
        model.addAttribute("minimo", minimo);

        return "radiacion-detalle";
    }

    @GetMapping("/estacion/{id}")
    @ResponseBody
    public List<Radiacion> getRadiacionesByEstacion(@PathVariable("id") String estacionId) {
        List<Radiacion> radiaciones = radiacionRepository.findByEstacionId(estacionId);
        radiaciones.forEach(Radiacion::actualizarMesCantidadMap);
        return radiaciones;
    }
}
