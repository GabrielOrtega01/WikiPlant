package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Tminmedia;
import com.mongowikiplant.app.repository.TminmediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tminmedia")
public class ControllerRestTminmedia {

    @Autowired
    private TminmediaRepository tminmediaRepository;

    @GetMapping
    public List<Tminmedia> listarTminmedias() {
        return tminmediaRepository.findAll();
    }

    @PostMapping
    public Tminmedia crearTminmedia(@RequestBody Tminmedia tminmedia) {
        return tminmediaRepository.save(tminmedia);
    }

    @PutMapping("/{id}")
    public Tminmedia actualizarTminmedia(@PathVariable String id, @RequestBody Tminmedia tminmediaActualizado) {
        tminmediaActualizado.setId(id);
        return tminmediaRepository.save(tminmediaActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarTminmedia(@PathVariable String id) {
        tminmediaRepository.deleteById(id);
    }
}
