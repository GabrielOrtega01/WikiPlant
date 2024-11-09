package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Planificacion;
import com.mongowikiplant.app.repository.PlanificacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("planificacion")
public class ControllerWebPlanificacion {

    @Autowired
    private PlanificacionRepository planificacionRepository;

    @GetMapping("/listar")
    public String listarPlanificaciones(Model model) {
        model.addAttribute("planificaciones", planificacionRepository.findAll());
        return "planificacion-lista";
    }

    @GetMapping("/crear")
    public String crearPlanificacionForm(Model model) {
        model.addAttribute("planificacion", new Planificacion());
        return "planificacion-form";
    }

    @PostMapping("/guardar")
    public String guardarPlanificacion(@ModelAttribute Planificacion planificacion) {
        planificacionRepository.save(planificacion);
        return "redirect:/planificacion/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarPlanificacionForm(@PathVariable String id, Model model) {
        Planificacion planificacion = planificacionRepository.findById(id).orElse(null);
        model.addAttribute("planificacion", planificacion);
        return "planificacion-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPlanificacion(@PathVariable String id) {
        planificacionRepository.deleteById(id);
        return "redirect:/planificacion/listar";
    }
}
