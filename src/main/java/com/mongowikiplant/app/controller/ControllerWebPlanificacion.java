package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Planificacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.PlanificacionRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/planificacion")
public class ControllerWebPlanificacion {

    @Autowired
    private PlanificacionRepository planificacionRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String planificacionesNewTemplate(Model model) {
        model.addAttribute("planificacion", new Planificacion());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "planificacion-form";
    }

    @GetMapping("/lista")
    public String planificacionesListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("planificaciones", planificacionRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "planificacion-lista";
    }

    @GetMapping("/edit/{id}")
    public String planificacionEditTemplate(@PathVariable("id") String id, Model model) {
        Planificacion planificacion = planificacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planificacion no encontrado"));
        model.addAttribute("planificacion", planificacion);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "planificacion-form";
    }

    @PostMapping("/save")
    public String planificacionesSaveProcess(@Valid @ModelAttribute("planificacion") Planificacion planificacion, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "planificacion-form";
        }
        if (planificacion.getId() == null || planificacion.getId().isEmpty()) {
        	planificacion.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el planificacion
        planificacionRepository.save(planificacion);

        // Redirigir al usuario a la lista de planificaciones después de guardar los datos
        return "redirect:/planificacion/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String planificacionDeleteProcess(@PathVariable("id") String id) {
    	planificacionRepository.deleteById(id);
        return "redirect:/planificacion/lista";
    }

    
    @GetMapping("/info")
    public String planificacionInfoTemplate() {
        return "planificacion-info";
    }

}
