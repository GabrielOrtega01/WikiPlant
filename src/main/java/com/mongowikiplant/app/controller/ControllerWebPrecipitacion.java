package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Precipitacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.PrecipitacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/precipitacion")
public class ControllerWebPrecipitacion {

    @Autowired
    private PrecipitacionRepository precipitacionRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String precipitacionesNewTemplate(Model model) {
        model.addAttribute("precipitacion", new Precipitacion());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "precipitacion-form";
    }

    @GetMapping("/lista")
    public String precipitacionesListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("precipitaciones", precipitacionRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "precipitacion-lista";
    }

    @GetMapping("/edit/{id}")
    public String precipitacionEditTemplate(@PathVariable("id") String id, Model model) {
        Precipitacion precipitacion = precipitacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Precipitacion no encontrado"));
        model.addAttribute("precipitacion", precipitacion);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "precipitacion-form";
    }

    @PostMapping("/save")
    public String precipitacionesSaveProcess(@Valid @ModelAttribute("precipitacion") Precipitacion precipitacion, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "precipitacion-form";
        }
        if (precipitacion.getId() == null || precipitacion.getId().isEmpty()) {
        	precipitacion.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el precipitacion
        precipitacionRepository.save(precipitacion);

        // Redirigir al usuario a la lista de precipitaciones después de guardar los datos
        return "redirect:/precipitacion/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String precipitacionDeleteProcess(@PathVariable("id") String id) {
    	precipitacionRepository.deleteById(id);
        return "redirect:/precipitacion/lista";
    }

    
    @GetMapping("/info")
    public String precipitacionInfoTemplate() {
        return "precipitacion-info";
    }

}
