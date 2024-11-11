package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Evaporacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EvaporacionRepository;

@RestController
@RequestMapping("/evaporaciones")
public class ControllerRestEvaporacion {

    @Autowired
    private EvaporacionRepository evaporacionRepository;

    @GetMapping
    public List<Evaporacion> listarEvaporaciones() {
        return evaporacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Evaporacion getEvaporacionById(@PathVariable String id) {
        return evaporacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evaporación no encontrada"));
    }

    @PostMapping
    public Evaporacion crearEvaporacion(@RequestBody Evaporacion evaporacion) {
        return evaporacionRepository.save(evaporacion);
    }

    @PostMapping("/map")
    public Evaporacion saveEvaporacionFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Evaporacion evaporacion = mapper.convertValue(body, Evaporacion.class);
        return evaporacionRepository.save(evaporacion);
    }

    @PutMapping("/{id}")
    public Evaporacion actualizarEvaporacion(@PathVariable String id, @RequestBody Evaporacion evaporacionActualizada) {
        evaporacionActualizada.setId(id);
        return evaporacionRepository.save(evaporacionActualizada);
    }

    @PutMapping("/map/{id}")
    public Evaporacion updateEvaporacionFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Evaporacion evaporacion = mapper.convertValue(body, Evaporacion.class);
        evaporacion.setId(id);
        return evaporacionRepository.save(evaporacion);
    }

    
	@DeleteMapping("/{id}")
	public Evaporacion deleteEvaporacion(@PathVariable String id) {
		Evaporacion evaporacion = evaporacionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Evaporación no encontrada"));
		evaporacionRepository.deleteById(id);
		return evaporacion;
	}

}
