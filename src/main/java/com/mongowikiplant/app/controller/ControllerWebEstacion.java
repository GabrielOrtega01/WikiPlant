package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongowikiplant.app.entity.Estacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/estacion")
public class ControllerWebEstacion {

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/lista")
    public String estacionesListTemplate(Model model) {
        model.addAttribute("estaciones", estacionRepository.findAll());
        return "estacion-lista";
    }

    @GetMapping("/crear")
    public String estacionesCrearTemplate(Model model) {
        model.addAttribute("estacion", new Estacion());
        return "estacion-form";
    }

    @GetMapping("/edit/{id}")
    public String estacionEditTemplate(@PathVariable("id") String id, Model model) {
    	Estacion estacion = estacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estación no encontrada"));
    	model.addAttribute("estacion", estacion);
        return "estacion-form";
    }

    @PostMapping("/save")
    public String estacionesSaveProcess(@Valid @ModelAttribute("estacion") Estacion estacion, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            return "fotoperiodo-form";
        }
        if (estacion.getId() == null || estacion.getId().isEmpty()) {
        	estacion.setId(null); // MongoDB generará un ID automáticamente
        }

        estacionRepository.save(estacion);
        return "redirect:/estacion/lista";
    }

    @GetMapping("/delete/{id}")
    public String estacionDeleteProcess(@PathVariable("id") String id) {
    	estacionRepository.deleteById(id);
        return "redirect:/estacion/lista";
    }
    
    @GetMapping("/info")
    public String estacionesInfoTemplate() {
        return "estacion-info"; // nombre del nuevo template Thymeleaf
    }

    
}
