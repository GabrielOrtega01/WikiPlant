package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Precipitacion;
import com.mongowikiplant.app.repository.PrecipitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/precipitacion")
public class ControllerRestPrecipitacion {

    @Autowired
    private PrecipitacionRepository precipitacionRepository;

    @GetMapping
    public List<Precipitacion> listarPrecipitaciones() {
        return precipitacionRepository.findAll();
    }

    @PostMapping
    public Precipitacion crearPrecipitacion(@RequestBody Precipitacion precipitacion) {
        return precipitacionRepository.save(precipitacion);
    }

    @PutMapping("/{id}")
    public Precipitacion actualizarPrecipitacion(@PathVariable String id, @RequestBody Precipitacion precipitacionActualizada) {
        precipitacionActualizada.setId(id);
        return precipitacionRepository.save(precipitacionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarPrecipitacion(@PathVariable String id) {
        precipitacionRepository.deleteById(id);
    }
}
