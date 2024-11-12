package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.AmplitudDiariaTemperatura;
import com.mongowikiplant.app.entity.AmplitudDiariaTemperatura.MesCantidad;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.AmplitudDiariaTemperaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/amplitudDiariaTemperatura")
public class ControllerWebAmplitudDiariaTemperatura {

    @Autowired
    private AmplitudDiariaTemperaturaRepository amplitudDiariaTemperaturaRepository;

    @GetMapping("/crear")
    public String amplitudDiariaTemperaturaCrearTemplate(Model model) {
        model.addAttribute("amplitudDiariaTemperatura", new AmplitudDiariaTemperatura());
        return "amplitudDiariaTemperatura-form";
    }

    @GetMapping("/lista")
    public String amplitudDiariaTemperaturaListTemplate(Model model) {
        List<AmplitudDiariaTemperatura> amplitudDiariaTemperaturaesDiariasTemperaturas = amplitudDiariaTemperaturaRepository.findAll();

        for (AmplitudDiariaTemperatura amplitudDiariaTemperatura : amplitudDiariaTemperaturaesDiariasTemperaturas) {
            Map<String, Double> mesCantidadMap = new HashMap<>();
            for (AmplitudDiariaTemperatura.MesCantidad registro : amplitudDiariaTemperatura.getRegistros()) {
                mesCantidadMap.put(registro.getMes(), registro.getCantidad());
            }
            amplitudDiariaTemperatura.setMesCantidadMap(mesCantidadMap);
        }

        model.addAttribute("amplitudDiariaTemperaturaesDiariasTemperaturas", amplitudDiariaTemperaturaesDiariasTemperaturas);
        return "amplitudDiariaTemperatura-lista";
    }

    @GetMapping("/edit/{id}")
    public String amplitudDiariaTemperaturaEditTemplate(@PathVariable("id") String id, Model model) {
        AmplitudDiariaTemperatura amplitudDiariaTemperatura = amplitudDiariaTemperaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("AmplitudDiariaTemperatura no encontrada"));
        model.addAttribute("amplitudDiariaTemperatura", amplitudDiariaTemperatura);
        return "amplitudDiariaTemperatura-form";
    }

    @GetMapping("/delete/{id}")
    public String amplitudDiariaTemperaturaDeleteProcess(@PathVariable("id") String id) {
        amplitudDiariaTemperaturaRepository.deleteById(id);
        return "redirect:/amplitudDiariaTemperatura/lista";
    }

    @GetMapping("/info")
    public String amplitudDiariaTemperaturaInfoTemplate() {
        return "amplitudDiariaTemperatura-info";
    }

    @PostMapping("/save")
    public String amplitudDiariaTemperaturaSaveProcess(
            @ModelAttribute("amplitudDiariaTemperatura") AmplitudDiariaTemperatura amplitudDiariaTemperatura,
            @RequestParam(value = "fecha", required = false) int fecha,
            @RequestParam(value = "mes1", required = false) String mes1,
            @RequestParam(value = "cantidad1", required = false) Double cantidad1,
            @RequestParam(value = "mes2", required = false) String mes2,
            @RequestParam(value = "cantidad2", required = false) Double cantidad2,
            @RequestParam(value = "mes3", required = false) String mes3,
            @RequestParam(value = "cantidad3", required = false) Double cantidad3,
            @RequestParam(value = "mes4", required = false) String mes4,
            @RequestParam(value = "cantidad4", required = false) Double cantidad4,
            @RequestParam(value = "mes5", required = false) String mes5,
            @RequestParam(value = "cantidad5", required = false) Double cantidad5,
            @RequestParam(value = "mes6", required = false) String mes6,
            @RequestParam(value = "cantidad6", required = false) Double cantidad6,
            @RequestParam(value = "mes7", required = false) String mes7,
            @RequestParam(value = "cantidad7", required = false) Double cantidad7,
            @RequestParam(value = "mes8", required = false) String mes8,
            @RequestParam(value = "cantidad8", required = false) Double cantidad8,
            @RequestParam(value = "mes9", required = false) String mes9,
            @RequestParam(value = "cantidad9", required = false) Double cantidad9,
            @RequestParam(value = "mes10", required = false) String mes10,
            @RequestParam(value = "cantidad10", required = false) Double cantidad10,
            @RequestParam(value = "mes11", required = false) String mes11,
            @RequestParam(value = "cantidad11", required = false) Double cantidad11,
            @RequestParam(value = "mes12", required = false) String mes12,
            @RequestParam(value = "cantidad12", required = false) Double cantidad12) {

        if (amplitudDiariaTemperatura.getId() == null || amplitudDiariaTemperatura.getId().isEmpty()) {
            amplitudDiariaTemperatura.setId(null);
        }

        if (fecha != 0) {
            amplitudDiariaTemperatura.setFecha(fecha);
        }

        List<MesCantidad> registros = new ArrayList<>();

        if (mes1 != null && cantidad1 != null) registros.add(new MesCantidad(mes1, cantidad1));
        if (mes2 != null && cantidad2 != null) registros.add(new MesCantidad(mes2, cantidad2));
        if (mes3 != null && cantidad3 != null) registros.add(new MesCantidad(mes3, cantidad3));
        if (mes4 != null && cantidad4 != null) registros.add(new MesCantidad(mes4, cantidad4));
        if (mes5 != null && cantidad5 != null) registros.add(new MesCantidad(mes5, cantidad5));
        if (mes6 != null && cantidad6 != null) registros.add(new MesCantidad(mes6, cantidad6));
        if (mes7 != null && cantidad7 != null) registros.add(new MesCantidad(mes7, cantidad7));
        if (mes8 != null && cantidad8 != null) registros.add(new MesCantidad(mes8, cantidad8));
        if (mes9 != null && cantidad9 != null) registros.add(new MesCantidad(mes9, cantidad9));
        if (mes10 != null && cantidad10 != null) registros.add(new MesCantidad(mes10, cantidad10));
        if (mes11 != null && cantidad11 != null) registros.add(new MesCantidad(mes11, cantidad11));
        if (mes12 != null && cantidad12 != null) registros.add(new MesCantidad(mes12, cantidad12));

        amplitudDiariaTemperatura.setRegistros(registros);
        amplitudDiariaTemperaturaRepository.save(amplitudDiariaTemperatura);

        return "redirect:/amplitudDiariaTemperatura/lista";
    }
}
