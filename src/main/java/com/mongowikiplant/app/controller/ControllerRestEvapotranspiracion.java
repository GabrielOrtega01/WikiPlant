package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Evapotranspiracion;
import com.mongowikiplant.app.repository.EvapotranspiracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evapotranspiracion")
public class ControllerRestEvapotranspiracion {

    @Autowired
    private EvapotranspiracionRepository evapotranspiracionRepository;

    @GetMapping
    public List<Evapotranspiracion> listarEvapotranspiraciones() {
        return evapotranspiracionRepository.findAll();
    }

    @PostMapping
    public Evapotranspiracion crearEvapotranspiracion(@RequestBody Evapotranspiracion evapotranspiracion) {
        return evapotranspiracionRepository.save(evapotranspiracion);
    }

    @PutMapping("/{id}")
    public Evapotranspiracion actualizarEvapotranspiracion(@PathVariable String id, @RequestBody Evapotranspiracion evapotranspiracionActualizada) {
        evapotranspiracionActualizada.setId(id);
        return evapotranspiracionRepository.save(evapotranspiracionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarEvapotranspiracion(@PathVariable String id) {
        evapotranspiracionRepository.deleteById(id);
    }
}
