package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Tminmedia;
import com.mongowikiplant.app.entity.Tminmedia.MesCantidad;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.TminmediaRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tminmedia")
public class ControllerWebTminmedia {

    @Autowired
    private TminmediaRepository tminmediaRepository;
    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String tminmediaCrearTemplate(Model model) {
        model.addAttribute("tminmedia", new Tminmedia());
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "tminmedia-form";
    }

    @GetMapping("/lista")
    public String tminmediaListTemplate(Model model) {
        List<Tminmedia> tminmedias = tminmediaRepository.findAll();
        tminmedias.forEach(Tminmedia::actualizarMesCantidadMap);
        model.addAttribute("tminmedias", tminmedias);
        tminmedias.sort(Comparator.comparingInt(Tminmedia::getYear));
        return "tminmedia-lista";
    }

    @GetMapping("/edit/{id}")
    public String tminmediaEditTemplate(@PathVariable("id") String id, Model model) {
        Tminmedia tminmedia = tminmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Temperatura mínima media no encontrada"));
        model.addAttribute("tminmedia", tminmedia);
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "tminmedia-form";
    }

    @GetMapping("/delete/{id}")
    public String tminmediaDeleteProcess(@PathVariable("id") String id) {
        tminmediaRepository.deleteById(id);
        return "redirect:/tminmedia/lista";
    }

    @GetMapping("/info")
    public String tminmediaInfoTemplate() {
        return "tminmedia-info";
    }

    @PostMapping("/save")
    public String tminmediaSaveProcess(@ModelAttribute Tminmedia tminmedia,
                                         @RequestParam(value = "year", required = false) int year,
                                         @RequestParam Map<String, String> allParams) {

        if (year != 0) {
            tminmedia.setYear(year);
        }

        List<MesCantidad> registros = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String mes = allParams.get("mes" + i);
            Double cantidad = allParams.containsKey("cantidad" + i) ? Double.valueOf(allParams.get("cantidad" + i)) : null;
            if (mes != null && cantidad != null) {
                registros.add(new MesCantidad(mes, cantidad));
            }
        }

        tminmedia.setRegistros(registros);
        tminmedia.actualizarMesCantidadMap();
        tminmediaRepository.save(tminmedia);

        return "redirect:/tminmedia/lista";
    }

    @GetMapping("/detalle/{id}")
    public String tminmediaDetalleTemplate(@PathVariable("id") String id, Model model) {
        Tminmedia tminmedia = tminmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Temperatura mínima media no encontrada"));

        // Calcular las estadísticas y agregarlas al modelo
        double promedioAnual = tminmedia.calcularPromedioAnual();
        double desviacionEstandar = tminmedia.calcularDesviacionEstandar();
        double coeficienteVariacion = tminmedia.calcularCoeficienteVariacion();
        double maximo = tminmedia.calcularMaximo();
        double minimo = tminmedia.calcularMinimo();

        model.addAttribute("tminmedia", tminmedia);
        model.addAttribute("promedioAnual", promedioAnual);
        model.addAttribute("desviacionEstandar", desviacionEstandar);
        model.addAttribute("coeficienteVariacion", coeficienteVariacion);
        model.addAttribute("maximo", maximo);
        model.addAttribute("minimo", minimo);

        return "tminmedia-detalle";
    }

    @GetMapping("/estacion/{id}")
    @ResponseBody
    public List<Tminmedia> getTminmediasByEstacion(@PathVariable("id") String estacionId) {
        List<Tminmedia> tminmedias = tminmediaRepository.findByEstacionId(estacionId);
        tminmedias.forEach(Tminmedia::actualizarMesCantidadMap);
        return tminmedias;
    }
}

