package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.mongowikiplant.app.entity.Fotoperiodo;
import com.mongowikiplant.app.entity.Fotoperiodo.MesCantidad;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.FotoperiodoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fotoperiodo")
public class ControllerWebFotoperiodo {

    @Autowired
    private FotoperiodoRepository fotoperiodoRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String fotoperiodoCrearTemplate(Model model) {
        model.addAttribute("fotoperiodo", new Fotoperiodo());
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "fotoperiodo-form";
    }

    @GetMapping("/lista")
    public String fotoperiodoListTemplate(Model model) {
        List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findAll();
        fotoperiodos.sort(Comparator.comparingInt(Fotoperiodo::getYear));
        fotoperiodos.forEach(Fotoperiodo::actualizarMesCantidadMap);
        model.addAttribute("fotoperiodos", fotoperiodos);
        return "fotoperiodo-lista";
    }

    @GetMapping("/edit/{id}")
    public String fotoperiodoEditTemplate(@PathVariable("id") String id, Model model) {
        Fotoperiodo fotoperiodo = fotoperiodoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fotoperiodo no encontrado"));
        model.addAttribute("fotoperiodo", fotoperiodo);
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "fotoperiodo-form";
    }

    @GetMapping("/delete/{id}")
    public String fotoperiodoDeleteProcess(@PathVariable("id") String id) {
        fotoperiodoRepository.deleteById(id);
        return "redirect:/fotoperiodo/lista";
    }

    @GetMapping("/info")
    public String fotoperiodoInfoTemplate() {
        return "fotoperiodo-info";
    }

    @PostMapping("/save")
    public String fotoperiodoSaveProcess(@ModelAttribute Fotoperiodo fotoperiodo,
                                         @RequestParam(value = "year", required = false) int year,
                                         @RequestParam Map<String, String> allParams) {

        if (year != 0) {
            fotoperiodo.setYear(year);
        }

        List<MesCantidad> registros = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String mes = allParams.get("mes" + i);
            Double cantidad = allParams.containsKey("cantidad" + i) ? Double.valueOf(allParams.get("cantidad" + i)) : null;
            if (mes != null && cantidad != null) {
                registros.add(new MesCantidad(mes, cantidad));
            }
        }

        fotoperiodo.setRegistros(registros);
        fotoperiodo.actualizarMesCantidadMap();
        fotoperiodoRepository.save(fotoperiodo);

        return "redirect:/fotoperiodo/lista";
    }

    @GetMapping("/detalle/{id}")
    public String fotoperiodoDetalleTemplate(@PathVariable("id") String id, Model model) {
        Fotoperiodo fotoperiodo = fotoperiodoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fotoperiodo no encontrado"));

        // Calcular las estadísticas y agregarlas al modelo
        double promedioAnual = fotoperiodo.calcularPromedioAnual();
        double desviacionEstandar = fotoperiodo.calcularDesviacionEstandar();
        double coeficienteVariacion = fotoperiodo.calcularCoeficienteVariacion();
        double maximo = fotoperiodo.calcularMaximo();
        double minimo = fotoperiodo.calcularMinimo();

        model.addAttribute("fotoperiodo", fotoperiodo);
        model.addAttribute("promedioAnual", promedioAnual);
        model.addAttribute("desviacionEstandar", desviacionEstandar);
        model.addAttribute("coeficienteVariacion", coeficienteVariacion);
        model.addAttribute("maximo", maximo);
        model.addAttribute("minimo", minimo);

        return "fotoperiodo-detalle";
    }

    @GetMapping("/estacion/{id}")
    @ResponseBody
    public List<Fotoperiodo> getFotoperiodosByEstacion(@PathVariable("id") String estacionId) {
        List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findByEstacionId(estacionId);
        fotoperiodos.forEach(Fotoperiodo::actualizarMesCantidadMap);
        return fotoperiodos;
    }
}
