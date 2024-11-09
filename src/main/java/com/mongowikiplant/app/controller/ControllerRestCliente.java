package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Cliente;
import com.mongowikiplant.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ControllerRestCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable String id, @RequestBody Cliente clienteActualizado) {
        clienteActualizado.setId(id);
        return clienteRepository.save(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable String id) {
        clienteRepository.deleteById(id);
    }
}
