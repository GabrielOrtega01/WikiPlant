package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Cultivo;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.CultivoRepository;

@RestController
@RequestMapping("/cultivos")
public class ControllerRestCultivo {

    @Autowired
    private CultivoRepository cultivoRepository;

    @GetMapping
    public List<Cultivo> listarCultivos() {
        return cultivoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cultivo getCultivoById(@PathVariable String id) {
        return cultivoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cultivo no encontrado"));
    }

    @PostMapping
    public Cultivo crearCultivo(@RequestBody Cultivo cultivo) {
        return cultivoRepository.save(cultivo);
    }

    @PostMapping("/map")
    public Cultivo saveCultivoFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Cultivo cultivo = mapper.convertValue(body, Cultivo.class);
        return cultivoRepository.save(cultivo);
    }

    @PutMapping("/{id}")
    public Cultivo actualizarCultivo(@PathVariable String id, @RequestBody Cultivo cultivoActualizada) {
        cultivoActualizada.setId(id);
        return cultivoRepository.save(cultivoActualizada);
    }

    @PutMapping("/map/{id}")
    public Cultivo updateCultivoFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Cultivo cultivo = mapper.convertValue(body, Cultivo.class);
        cultivo.setId(id);
        return cultivoRepository.save(cultivo);
    }

    
	@DeleteMapping("/{id}")
	public Cultivo deleteCultivo(@PathVariable String id) {
		Cultivo cultivo = cultivoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cultivo no encontrada"));
		cultivoRepository.deleteById(id);
		return cultivo;
	}

}
