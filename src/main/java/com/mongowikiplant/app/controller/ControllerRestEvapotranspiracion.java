package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Evapotranspiracion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EvapotranspiracionRepository;

@RestController
@RequestMapping("/evapotranspiraciones")
public class ControllerRestEvapotranspiracion {

    @Autowired
    private EvapotranspiracionRepository evapotranspiracionRepository;

    @GetMapping
    public List<Evapotranspiracion> listarEvapotranspiraciones() {
        return evapotranspiracionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Evapotranspiracion getEvapotranspiracionById(@PathVariable String id) {
        return evapotranspiracionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evapotraspiración no encontrada"));
    }

    @PostMapping
    public Evapotranspiracion crearEvapotranspiracion(@RequestBody Evapotranspiracion evapotranspiracion) {
        return evapotranspiracionRepository.save(evapotranspiracion);
    }

    @PostMapping("/map")
    public Evapotranspiracion saveEvapotranspiracionFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Evapotranspiracion evapotranspiracion = mapper.convertValue(body, Evapotranspiracion.class);
        return evapotranspiracionRepository.save(evapotranspiracion);
    }

    @PutMapping("/{id}")
    public Evapotranspiracion actualizarEvapotranspiracion(@PathVariable String id, @RequestBody Evapotranspiracion evapotranspiracionActualizada) {
        evapotranspiracionActualizada.setId(id);
        return evapotranspiracionRepository.save(evapotranspiracionActualizada);
    }

    @PutMapping("/map/{id}")
    public Evapotranspiracion updateEvapotranspiracionFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Evapotranspiracion evapotranspiracion = mapper.convertValue(body, Evapotranspiracion.class);
        evapotranspiracion.setId(id);
        return evapotranspiracionRepository.save(evapotranspiracion);
    }

    
	@DeleteMapping("/{id}")
	public Evapotranspiracion deleteEvapotranspiracion(@PathVariable String id) {
		Evapotranspiracion evapotranspiracion = evapotranspiracionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Evapotraspiración no encontrada"));
		evapotranspiracionRepository.deleteById(id);
		return evapotranspiracion;
	}

}
