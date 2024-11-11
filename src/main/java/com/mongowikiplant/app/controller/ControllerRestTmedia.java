package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Tmedia;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.TmediaRepository;

@RestController
@RequestMapping("/tmedias")
public class ControllerRestTmedia {

    @Autowired
    private TmediaRepository tmediaRepository;

    @GetMapping
    public List<Tmedia> listarTmedias() {
        return tmediaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tmedia getTmediaById(@PathVariable String id) {
        return tmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tmedia no encontrada"));
    }

    @PostMapping
    public Tmedia crearTmedia(@RequestBody Tmedia tmedia) {
        return tmediaRepository.save(tmedia);
    }

    @PostMapping("/map")
    public Tmedia saveTmediaFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Tmedia tmedia = mapper.convertValue(body, Tmedia.class);
        return tmediaRepository.save(tmedia);
    }

    @PutMapping("/{id}")
    public Tmedia actualizarTmedia(@PathVariable String id, @RequestBody Tmedia tmediaActualizada) {
        tmediaActualizada.setId(id);
        return tmediaRepository.save(tmediaActualizada);
    }

    @PutMapping("/map/{id}")
    public Tmedia updateTmediaFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Tmedia tmedia = mapper.convertValue(body, Tmedia.class);
        tmedia.setId(id);
        return tmediaRepository.save(tmedia);
    }

    
	@DeleteMapping("/{id}")
	public Tmedia deleteTmedia(@PathVariable String id) {
		Tmedia tmedia = tmediaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Tmedia no encontrada"));
		tmediaRepository.deleteById(id);
		return tmedia;
	}

}
