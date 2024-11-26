package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Evaporacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.EvaporacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/evaporacion")
public class ControllerWebEvaporacion {

    @Autowired
    private EvaporacionRepository evaporacionRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String evaporacionesNewTemplate(Model model) {
        model.addAttribute("evaporacion", new Evaporacion());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "evaporacion-form";
    }

    @GetMapping("/lista")
    public String evaporacionesListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("evaporaciones", evaporacionRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "evaporacion-lista";
    }

    @GetMapping("/edit/{id}")
    public String evaporacionEditTemplate(@PathVariable("id") String id, Model model) {
        Evaporacion evaporacion = evaporacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evaporacion no encontrado"));
        model.addAttribute("evaporacion", evaporacion);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "evaporacion-form";
    }

    @PostMapping("/save")
    public String evaporacionesSaveProcess(@Valid @ModelAttribute("evaporacion") Evaporacion evaporacion, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "evaporacion-form";
        }
        if (evaporacion.getId() == null || evaporacion.getId().isEmpty()) {
        	evaporacion.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el evaporacion
        evaporacionRepository.save(evaporacion);

        // Redirigir al usuario a la lista de evaporaciones después de guardar los datos
        return "redirect:/evaporacion/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String evaporacionDeleteProcess(@PathVariable("id") String id) {
    	evaporacionRepository.deleteById(id);
        return "redirect:/evaporacion/lista";
    }

    
    @GetMapping("/info")
    public String evaporacionInfoTemplate() {
        return "evaporacion-info";
    }

}
