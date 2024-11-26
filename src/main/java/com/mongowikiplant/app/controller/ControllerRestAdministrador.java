package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Administrador;
import com.mongowikiplant.app.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador")
public class ControllerRestAdministrador {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping
    public List<Administrador> listarAdministradores() {
        return administradorRepository.findAll();
    }

    @PostMapping
    public Administrador crearAdministrador(@RequestBody Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @PutMapping("/{id}")
    public Administrador actualizarAdministrador(@PathVariable String id, @RequestBody Administrador administradorActualizado) {
        administradorActualizado.setId(id);
        return administradorRepository.save(administradorActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarAdministrador(@PathVariable String id) {
        administradorRepository.deleteById(id);
    }
}
