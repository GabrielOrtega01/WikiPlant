package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Planificacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.PlanificacionRepository;

@RestController
@RequestMapping("/planificaciones")
public class ControllerRestPlanificacion {

    @Autowired
    private PlanificacionRepository planificacionRepository;

    @GetMapping
    public List<Planificacion> listarPlanificaciones() {
        return planificacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Planificacion getPlanificacionById(@PathVariable String id) {
        return planificacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planificación no encontrada"));
    }

    @PostMapping
    public Planificacion crearPlanificacion(@RequestBody Planificacion planificacion) {
        return planificacionRepository.save(planificacion);
    }

    @PostMapping("/map")
    public Planificacion savePlanificacionFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Planificacion planificacion = mapper.convertValue(body, Planificacion.class);
        return planificacionRepository.save(planificacion);
    }

    @PutMapping("/{id}")
    public Planificacion actualizarPlanificacion(@PathVariable String id, @RequestBody Planificacion planificacionActualizada) {
        planificacionActualizada.setId(id);
        return planificacionRepository.save(planificacionActualizada);
    }

    @PutMapping("/map/{id}")
    public Planificacion updatePlanificacionFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Planificacion planificacion = mapper.convertValue(body, Planificacion.class);
        planificacion.setId(id);
        return planificacionRepository.save(planificacion);
    }

    
	@DeleteMapping("/{id}")
	public Planificacion deletePlanificacion(@PathVariable String id) {
		Planificacion planificacion = planificacionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Planificación no encontrada"));
		planificacionRepository.deleteById(id);
		return planificacion;
	}

}
