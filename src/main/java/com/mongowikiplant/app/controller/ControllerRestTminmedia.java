package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Tminmedia;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.TminmediaRepository;

@RestController
@RequestMapping("/tminmedias")
public class ControllerRestTminmedia {

    @Autowired
    private TminmediaRepository tminmediaRepository;

    @GetMapping
    public List<Tminmedia> listarTminmedias() {
        return tminmediaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tminmedia getTminmediaById(@PathVariable String id) {
        return tminmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tminmedia no encontrada"));
    }

    @PostMapping
    public Tminmedia crearTminmedia(@RequestBody Tminmedia tminmedia) {
        return tminmediaRepository.save(tminmedia);
    }

    @PostMapping("/map")
    public Tminmedia saveTminmediaFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Tminmedia tminmedia = mapper.convertValue(body, Tminmedia.class);
        return tminmediaRepository.save(tminmedia);
    }

    @PutMapping("/{id}")
    public Tminmedia actualizarTminmedia(@PathVariable String id, @RequestBody Tminmedia tminmediaActualizada) {
        tminmediaActualizada.setId(id);
        return tminmediaRepository.save(tminmediaActualizada);
    }

    @PutMapping("/map/{id}")
    public Tminmedia updateTminmediaFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Tminmedia tminmedia = mapper.convertValue(body, Tminmedia.class);
        tminmedia.setId(id);
        return tminmediaRepository.save(tminmedia);
    }

    
	@DeleteMapping("/{id}")
	public Tminmedia deleteTminmedia(@PathVariable String id) {
		Tminmedia tminmedia = tminmediaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Tminmedia no encontrada"));
		tminmediaRepository.deleteById(id);
		return tminmedia;
	}

}
