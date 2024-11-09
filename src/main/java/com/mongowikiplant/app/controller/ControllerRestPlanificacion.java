package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Planificacion;
import com.mongowikiplant.app.repository.PlanificacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planificaciones")
public class ControllerRestPlanificacion {

    @Autowired
    private PlanificacionRepository planificacionRepository;

    @GetMapping
    public List<Planificacion> listarPlanificaciones() {
        return planificacionRepository.findAll();
    }

    @PostMapping
    public Planificacion crearPlanificacion(@RequestBody Planificacion planificacion) {
        return planificacionRepository.save(planificacion);
    }

    @PutMapping("/{id}")
    public Planificacion actualizarPlanificacion(@PathVariable String id, @RequestBody Planificacion planificacionActualizada) {
        planificacionActualizada.setId(id);
        return planificacionRepository.save(planificacionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarPlanificacion(@PathVariable String id) {
        planificacionRepository.deleteById(id);
    }
}
