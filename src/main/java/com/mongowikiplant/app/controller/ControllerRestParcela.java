package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Parcela;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.ParcelaRepository;

@RestController
@RequestMapping("/parcelas")
public class ControllerRestParcela {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @GetMapping
    public List<Parcela> listarParcelas() {
        return parcelaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Parcela getParcelaById(@PathVariable String id) {
        return parcelaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parcela no encontrada"));
    }

    @PostMapping
    public Parcela crearParcela(@RequestBody Parcela parcela) {
        return parcelaRepository.save(parcela);
    }

    @PostMapping("/map")
    public Parcela saveParcelaFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Parcela parcela = mapper.convertValue(body, Parcela.class);
        return parcelaRepository.save(parcela);
    }

    @PutMapping("/{id}")
    public Parcela actualizarParcela(@PathVariable String id, @RequestBody Parcela parcelaActualizada) {
        parcelaActualizada.setId(id);
        return parcelaRepository.save(parcelaActualizada);
    }

    @PutMapping("/map/{id}")
    public Parcela updateParcelaFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Parcela parcela = mapper.convertValue(body, Parcela.class);
        parcela.setId(id);
        return parcelaRepository.save(parcela);
    }

    
	@DeleteMapping("/{id}")
	public Parcela deleteParcela(@PathVariable String id) {
		Parcela parcela = parcelaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Parcela no encontrada"));
		parcelaRepository.deleteById(id);
		return parcela;
	}

}
