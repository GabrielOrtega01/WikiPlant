package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Estimacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.EstimacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/estimacion")
public class ControllerWebEstimacion {

    @Autowired
    private EstimacionRepository estimacionRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String estimacionesNewTemplate(Model model) {
        model.addAttribute("estimacion", new Estimacion());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "estimacion-form";
    }

    @GetMapping("/lista")
    public String estimacionesListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("estimaciones", estimacionRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "estimacion-lista";
    }

    @GetMapping("/edit/{id}")
    public String estimacionEditTemplate(@PathVariable("id") String id, Model model) {
        Estimacion estimacion = estimacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estimacion no encontrado"));
        model.addAttribute("estimacion", estimacion);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "estimacion-form";
    }

    @PostMapping("/save")
    public String estimacionesSaveProcess(@Valid @ModelAttribute("estimacion") Estimacion estimacion, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "estimacion-form";
        }
        if (estimacion.getId() == null || estimacion.getId().isEmpty()) {
        	estimacion.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el estimacion
        estimacionRepository.save(estimacion);

        // Redirigir al usuario a la lista de estimaciones después de guardar los datos
        return "redirect:/estimacion/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String estimacionDeleteProcess(@PathVariable("id") String id) {
    	estimacionRepository.deleteById(id);
        return "redirect:/estimacion/lista";
    }

    
    @GetMapping("/info")
    public String estimacionInfoTemplate() {
        return "estimacion-info";
    }

}
