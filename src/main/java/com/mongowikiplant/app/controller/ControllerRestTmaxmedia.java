package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Tmaxmedia;
import com.mongowikiplant.app.repository.TmaxmediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tmaxmedia")

public class ControllerRestTmaxmedia {

    @Autowired
    private TmaxmediaRepository tmaxmediaRepository;

    @GetMapping
    public List<Tmaxmedia> listarTmaxmedias() {
        return tmaxmediaRepository.findAll();
    }

    @PostMapping
    public Tmaxmedia crearTmaxmedia(@RequestBody Tmaxmedia tmaxmedia) {
        return tmaxmediaRepository.save(tmaxmedia);
    }

    @PutMapping("/{id}")
    public Tmaxmedia actualizarTmaxmedia(@PathVariable String id, @RequestBody Tmaxmedia tmaxmediaActualizado) {
    	tmaxmediaActualizado.setId(id);
        return tmaxmediaRepository.save(tmaxmediaActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarTmaxmedia(@PathVariable String id) {
    	tmaxmediaRepository.deleteById(id);
    }
}
