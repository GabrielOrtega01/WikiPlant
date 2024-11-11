package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Precipitacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.PrecipitacionRepository;

@RestController
@RequestMapping("/precipitaciones")
public class ControllerRestPrecipitacion {

    @Autowired
    private PrecipitacionRepository precipitacionRepository;

    @GetMapping
    public List<Precipitacion> listarPrecipitaciones() {
        return precipitacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Precipitacion getPrecipitacionById(@PathVariable String id) {
        return precipitacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Precipitación no encontrada"));
    }

    @PostMapping
    public Precipitacion crearPrecipitacion(@RequestBody Precipitacion precipitacion) {
        return precipitacionRepository.save(precipitacion);
    }

    @PostMapping("/map")
    public Precipitacion savePrecipitacionFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Precipitacion precipitacion = mapper.convertValue(body, Precipitacion.class);
        return precipitacionRepository.save(precipitacion);
    }

    @PutMapping("/{id}")
    public Precipitacion actualizarPrecipitacion(@PathVariable String id, @RequestBody Precipitacion precipitacionActualizada) {
        precipitacionActualizada.setId(id);
        return precipitacionRepository.save(precipitacionActualizada);
    }

    @PutMapping("/map/{id}")
    public Precipitacion updatePrecipitacionFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Precipitacion precipitacion = mapper.convertValue(body, Precipitacion.class);
        precipitacion.setId(id);
        return precipitacionRepository.save(precipitacion);
    }

    
	@DeleteMapping("/{id}")
	public Precipitacion deletePrecipitacion(@PathVariable String id) {
		Precipitacion precipitacion = precipitacionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Precipitación no encontrada"));
		precipitacionRepository.deleteById(id);
		return precipitacion;
	}

}
