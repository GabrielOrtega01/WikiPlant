package com.mongowikiplant.app.controller;


import com.mongowikiplant.app.entity.Tmedia;
import com.mongowikiplant.app.repository.TmediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tmedia")

public class ControllerRestTmedia {

    @Autowired
    private TmediaRepository tmediaRepository;

    @GetMapping
    public List<Tmedia> listarFotoperiodos() {
        return tmediaRepository.findAll();
    }

    @PostMapping
    public Tmedia crearTmedia(@RequestBody Tmedia tmedia) {
        return tmediaRepository.save(tmedia);
    }

    @PutMapping("/{id}")
    public Tmedia actualizarTmedia(@PathVariable String id, @RequestBody Tmedia tmediaActualizado) {
    	tmediaActualizado.setId(id);
        return tmediaRepository.save(tmediaActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarTmedia(@PathVariable String id) {
    	tmediaRepository.deleteById(id);
    }
}
