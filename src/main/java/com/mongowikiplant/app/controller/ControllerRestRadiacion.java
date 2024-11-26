package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Radiacion;
import com.mongowikiplant.app.repository.RadiacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/radiacion")
public class ControllerRestRadiacion {

    @Autowired
    private RadiacionRepository radiacionRepository;

    @GetMapping
    public List<Radiacion> listarRadiaciones() {
        return radiacionRepository.findAll();
    }

    @PostMapping
    public Radiacion crearRadiacion(@RequestBody Radiacion radiacion) {
        return radiacionRepository.save(radiacion);
    }

    @PutMapping("/{id}")
    public Radiacion actualizarRadiacion(@PathVariable String id, @RequestBody Radiacion radiacionActualizado) {
        radiacionActualizado.setId(id);
        return radiacionRepository.save(radiacionActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarRadiacion(@PathVariable String id) {
        radiacionRepository.deleteById(id);
    }
}
