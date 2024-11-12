package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongowikiplant.app.entity.Tmedia;
import com.mongowikiplant.app.entity.Tmedia.MesCantidad;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.TmediaRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tmedia")
public class ControllerWebTmedia {

    @Autowired
    private TmediaRepository tmediaRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String tmediaCrearTemplate(Model model) {
        model.addAttribute("tmedia", new Tmedia());
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "tmedia-form";
    }

    @GetMapping("/lista")
    public String tmediaListTemplate(Model model) {
        List<Tmedia> tmedias = tmediaRepository.findAll();
        tmedias.forEach(Tmedia::actualizarMesCantidadMap);
        model.addAttribute("tmedias", tmedias);
        tmedias.sort(Comparator.comparingInt(Tmedia::getYear));
        return "tmedia-lista";
    }

    @GetMapping("/edit/{id}")
    public String tmediaEditTemplate(@PathVariable("id") String id, Model model) {
        Tmedia tmedia = tmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Temperatura media no encontrada"));
        model.addAttribute("tmedia", tmedia);
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "tmedia-form";
    }

    @GetMapping("/delete/{id}")
    public String tmediaDeleteProcess(@PathVariable("id") String id) {
        tmediaRepository.deleteById(id);
        return "redirect:/tmedia/lista";
    }

    @GetMapping("/info")
    public String tmediaInfoTemplate() {
        return "tmedia-info";
    }

    @PostMapping("/save")
    public String tmediaSaveProcess(@ModelAttribute Tmedia tmedia,
                                         @RequestParam(value = "year", required = false) int year,
                                         @RequestParam Map<String, String> allParams) {

        if (year != 0) {
            tmedia.setYear(year);
        }

        List<MesCantidad> registros = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String mes = allParams.get("mes" + i);
            Double cantidad = allParams.containsKey("cantidad" + i) ? Double.valueOf(allParams.get("cantidad" + i)) : null;
            if (mes != null && cantidad != null) {
                registros.add(new MesCantidad(mes, cantidad));
            }
        }

        tmedia.setRegistros(registros);
        tmedia.actualizarMesCantidadMap();
        tmediaRepository.save(tmedia);

        return "redirect:/tmedia/lista";
    }

    @GetMapping("/detalle/{id}")
    public String tmediaDetalleTemplate(@PathVariable("id") String id, Model model) {
        Tmedia tmedia = tmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Temperatura media no encontrada"));

        // Calcular las estadísticas y agregarlas al modelo
        double promedioAnual = tmedia.calcularPromedioAnual();
        double desviacionEstandar = tmedia.calcularDesviacionEstandar();
        double coeficienteVariacion = tmedia.calcularCoeficienteVariacion();
        double maximo = tmedia.calcularMaximo();
        double minimo = tmedia.calcularMinimo();

        model.addAttribute("tmedia", tmedia);
        model.addAttribute("promedioAnual", promedioAnual);
        model.addAttribute("desviacionEstandar", desviacionEstandar);
        model.addAttribute("coeficienteVariacion", coeficienteVariacion);
        model.addAttribute("maximo", maximo);
        model.addAttribute("minimo", minimo);

        return "tmedia-detalle";
    }

    @GetMapping("/estacion/{id}")
    @ResponseBody
    public List<Tmedia> getTmediasByEstacion(@PathVariable("id") String estacionId) {
        List<Tmedia> tmedias = tmediaRepository.findByEstacionId(estacionId);
        tmedias.forEach(Tmedia::actualizarMesCantidadMap);
        return tmedias;
    }
}

