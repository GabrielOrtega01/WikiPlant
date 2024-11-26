package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Planta;
import com.mongowikiplant.app.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planta")
public class ControllerRestPlanta {

    @Autowired
    private PlantaRepository plantaRepository;

    @GetMapping
    public List<Planta> listarPlantas() {
        return plantaRepository.findAll();
    }

    @PostMapping
    public Planta crearPlanta(@RequestBody Planta planta) {
        return plantaRepository.save(planta);
    }

    @PutMapping("/{id}")
    public Planta actualizarPlanta(@PathVariable String id, @RequestBody Planta plantaActualizado) {
        plantaActualizado.setId(id);
        return plantaRepository.save(plantaActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarPlanta(@PathVariable String id) {
        plantaRepository.deleteById(id);
    }
}
