package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Estimacion;
import com.mongowikiplant.app.repository.EstimacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estimacion")
public class ControllerRestEstimacion {

    @Autowired
    private EstimacionRepository estimacionRepository;

    @GetMapping
    public List<Estimacion> listarEstimaciones() {
        return estimacionRepository.findAll();
    }

    @PostMapping
    public Estimacion crearEstimacion(@RequestBody Estimacion estimacion) {
        return estimacionRepository.save(estimacion);
    }

    @PutMapping("/{id}")
    public Estimacion actualizarEstimacion(@PathVariable String id, @RequestBody Estimacion estimacionActualizada) {
        estimacionActualizada.setId(id);
        return estimacionRepository.save(estimacionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstimacion(@PathVariable String id) {
        estimacionRepository.deleteById(id);
    }
}
