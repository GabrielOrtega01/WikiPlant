package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Evaporacion;
import com.mongowikiplant.app.repository.EvaporacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaporacion")
public class ControllerRestEvaporacion {

    @Autowired
    private EvaporacionRepository evaporacionRepository;

    @GetMapping
    public List<Evaporacion> listarEvaporaciones() {
        return evaporacionRepository.findAll();
    }

    @PostMapping
    public Evaporacion crearEvaporacion(@RequestBody Evaporacion evaporacion) {
        return evaporacionRepository.save(evaporacion);
    }

    @PutMapping("/{id}")
    public Evaporacion actualizarEvaporacion(@PathVariable String id, @RequestBody Evaporacion evaporacionActualizada) {
        evaporacionActualizada.setId(id);
        return evaporacionRepository.save(evaporacionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarEvaporacion(@PathVariable String id) {
        evaporacionRepository.deleteById(id);
    }
}
