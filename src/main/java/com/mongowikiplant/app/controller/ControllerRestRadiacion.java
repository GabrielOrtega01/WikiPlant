package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Radiacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.RadiacionRepository;

@RestController
@RequestMapping("/radiaciones")
public class ControllerRestRadiacion {

    @Autowired
    private RadiacionRepository radiacionRepository;

    @GetMapping
    public List<Radiacion> listarRadiaciones() {
        return radiacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Radiacion getRadiacionById(@PathVariable String id) {
        return radiacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Radiación no encontrada"));
    }

    @PostMapping
    public Radiacion crearRadiacion(@RequestBody Radiacion radiacion) {
        return radiacionRepository.save(radiacion);
    }

    @PostMapping("/map")
    public Radiacion saveRadiacionFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Radiacion radiacion = mapper.convertValue(body, Radiacion.class);
        return radiacionRepository.save(radiacion);
    }

    @PutMapping("/{id}")
    public Radiacion actualizarRadiacion(@PathVariable String id, @RequestBody Radiacion radiacionActualizada) {
        radiacionActualizada.setId(id);
        return radiacionRepository.save(radiacionActualizada);
    }

    @PutMapping("/map/{id}")
    public Radiacion updateRadiacionFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Radiacion radiacion = mapper.convertValue(body, Radiacion.class);
        radiacion.setId(id);
        return radiacionRepository.save(radiacion);
    }

    
	@DeleteMapping("/{id}")
	public Radiacion deleteRadiacion(@PathVariable String id) {
		Radiacion radiacion = radiacionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Radiación no encontrada"));
		radiacionRepository.deleteById(id);
		return radiacion;
	}

}
