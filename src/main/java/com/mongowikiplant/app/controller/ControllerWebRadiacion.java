package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Radiacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.RadiacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/radiacion")
public class ControllerWebRadiacion {

    @Autowired
    private RadiacionRepository radiacionRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String radiacionesNewTemplate(Model model) {
        model.addAttribute("radiacion", new Radiacion());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "radiacion-form";
    }

    @GetMapping("/lista")
    public String radiacionesListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("radiaciones", radiacionRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "radiacion-lista";
    }

    @GetMapping("/edit/{id}")
    public String radiacionEditTemplate(@PathVariable("id") String id, Model model) {
        Radiacion radiacion = radiacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Radiacion no encontrado"));
        model.addAttribute("radiacion", radiacion);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "radiacion-form";
    }

    @PostMapping("/save")
    public String radiacionesSaveProcess(@Valid @ModelAttribute("radiacion") Radiacion radiacion, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "radiacion-form";
        }
        if (radiacion.getId() == null || radiacion.getId().isEmpty()) {
        	radiacion.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el radiacion
        radiacionRepository.save(radiacion);

        // Redirigir al usuario a la lista de radiaciones después de guardar los datos
        return "redirect:/radiacion/lista";
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

}
