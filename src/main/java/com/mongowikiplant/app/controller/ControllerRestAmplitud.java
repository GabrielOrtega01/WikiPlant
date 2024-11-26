package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Amplitud;
import com.mongowikiplant.app.repository.AmplitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amplitud")
public class ControllerRestAmplitud {

    @Autowired
    private AmplitudRepository amplitudRepository;

    @GetMapping
    public List<Amplitud> listarAmplitudes() {
        return amplitudRepository.findAll();
    }

    @PostMapping
    public Amplitud crearAmplitud(@RequestBody Amplitud amplitud) {
        return amplitudRepository.save(amplitud);
    }

    @PutMapping("/{id}")
    public Amplitud actualizarAmplitud(@PathVariable String id, @RequestBody Amplitud amplitudActualizado) {
        amplitudActualizado.setId(id);
        return amplitudRepository.save(amplitudActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarAmplitud(@PathVariable String id) {
        amplitudRepository.deleteById(id);
    }
}
