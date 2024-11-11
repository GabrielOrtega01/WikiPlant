package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Administrador;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.AdministradorRepository;

@RestController
@RequestMapping("/administradores")
public class ControllerRestAdministrador {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping
    public List<Administrador> listarAdministradores() {
        return administradorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Administrador getAdministradorById(@PathVariable String id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Administrador no encontrado"));
    }

    @PostMapping
    public Administrador crearAdministrador(@RequestBody Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @PostMapping("/map")
    public Administrador saveAdministradorFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Administrador administrador = mapper.convertValue(body, Administrador.class);
        return administradorRepository.save(administrador);
    }

    @PutMapping("/{id}")
    public Administrador actualizarAdministrador(@PathVariable String id, @RequestBody Administrador administradorActualizada) {
        administradorActualizada.setId(id);
        return administradorRepository.save(administradorActualizada);
    }

    @PutMapping("/map/{id}")
    public Administrador updateAdministradorFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Administrador administrador = mapper.convertValue(body, Administrador.class);
        administrador.setId(id);
        return administradorRepository.save(administrador);
    }

    
	@DeleteMapping("/{id}")
	public Administrador deleteAdministrador(@PathVariable String id) {
		Administrador administrador = administradorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Administrador no encontrado"));
		administradorRepository.deleteById(id);
		return administrador;
	}

}
