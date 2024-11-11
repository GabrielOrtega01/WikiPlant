package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Tmaxmedia;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.TmaxmediaRepository;

@RestController
@RequestMapping("/tmaxmedias")
public class ControllerRestTmaxmedia {

    @Autowired
    private TmaxmediaRepository tmaxmediaRepository;

    @GetMapping
    public List<Tmaxmedia> listarTmaxmedias() {
        return tmaxmediaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tmaxmedia getTmaxmediaById(@PathVariable String id) {
        return tmaxmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tmaxmedia no encontrada"));
    }

    @PostMapping
    public Tmaxmedia crearTmaxmedia(@RequestBody Tmaxmedia tmaxmedia) {
        return tmaxmediaRepository.save(tmaxmedia);
    }

    @PostMapping("/map")
    public Tmaxmedia saveTmaxmediaFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Tmaxmedia tmaxmedia = mapper.convertValue(body, Tmaxmedia.class);
        return tmaxmediaRepository.save(tmaxmedia);
    }

    @PutMapping("/{id}")
    public Tmaxmedia actualizarTmaxmedia(@PathVariable String id, @RequestBody Tmaxmedia tmaxmediaActualizada) {
        tmaxmediaActualizada.setId(id);
        return tmaxmediaRepository.save(tmaxmediaActualizada);
    }

    @PutMapping("/map/{id}")
    public Tmaxmedia updateTmaxmediaFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Tmaxmedia tmaxmedia = mapper.convertValue(body, Tmaxmedia.class);
        tmaxmedia.setId(id);
        return tmaxmediaRepository.save(tmaxmedia);
    }

    
	@DeleteMapping("/{id}")
	public Tmaxmedia deleteTmaxmedia(@PathVariable String id) {
		Tmaxmedia tmaxmedia = tmaxmediaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Tmaxmedia no encontrada"));
		tmaxmediaRepository.deleteById(id);
		return tmaxmedia;
	}

}
