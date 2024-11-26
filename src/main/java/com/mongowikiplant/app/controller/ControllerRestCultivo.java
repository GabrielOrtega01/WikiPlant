package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Cultivo;
import com.mongowikiplant.app.repository.CultivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cultivo")
public class ControllerRestCultivo {

    @Autowired
    private CultivoRepository cultivoRepository;

    @GetMapping
    public List<Cultivo> listarCultivos() {
        return cultivoRepository.findAll();
    }

    @PostMapping
    public Cultivo crearCultivo(@RequestBody Cultivo cultivo) {
        return cultivoRepository.save(cultivo);
    }

    @PutMapping("/{id}")
    public Cultivo actualizarCultivo(@PathVariable String id, @RequestBody Cultivo cultivoActualizado) {
        cultivoActualizado.setId(id);
        return cultivoRepository.save(cultivoActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarCultivo(@PathVariable String id) {
        cultivoRepository.deleteById(id);
    }
}
