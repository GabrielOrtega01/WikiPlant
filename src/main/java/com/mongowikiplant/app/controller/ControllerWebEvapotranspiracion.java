package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Evapotranspiracion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.EvapotranspiracionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/evapotranspiracion")
public class ControllerWebEvapotranspiracion {

    @Autowired
    private EvapotranspiracionRepository evapotranspiracionRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String evapotranspiracionesNewTemplate(Model model) {
        model.addAttribute("evapotranspiracion", new Evapotranspiracion());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "evapotranspiracion-form";
    }

    @GetMapping("/lista")
    public String evapotranspiracionesListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("evapotranspiraciones", evapotranspiracionRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "evapotranspiracion-lista";
    }

    @GetMapping("/edit/{id}")
    public String evapotranspiracionEditTemplate(@PathVariable("id") String id, Model model) {
        Evapotranspiracion evapotranspiracion = evapotranspiracionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evapotranspiracion no encontrado"));
        model.addAttribute("evapotranspiracion", evapotranspiracion);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "evapotranspiracion-form";
    }

    @PostMapping("/save")
    public String evapotranspiracionesSaveProcess(@Valid @ModelAttribute("evapotranspiracion") Evapotranspiracion evapotranspiracion, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "evapotranspiracion-form";
        }
        if (evapotranspiracion.getId() == null || evapotranspiracion.getId().isEmpty()) {
        	evapotranspiracion.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el evapotranspiracion
        evapotranspiracionRepository.save(evapotranspiracion);

        // Redirigir al usuario a la lista de evapotranspiraciones después de guardar los datos
        return "redirect:/evapotranspiracion/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String evapotranspiracionDeleteProcess(@PathVariable("id") String id) {
    	evapotranspiracionRepository.deleteById(id);
        return "redirect:/evapotranspiracion/lista";
    }

    
    @GetMapping("/info")
    public String evapotranspiracionInfoTemplate() {
        return "evapotranspiracion-info";
    }

}
