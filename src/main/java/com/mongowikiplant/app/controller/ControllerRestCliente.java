package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Cliente;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ControllerRestCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable String id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PostMapping("/map")
    public Cliente saveClienteFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = mapper.convertValue(body, Cliente.class);
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable String id, @RequestBody Cliente clienteActualizada) {
        clienteActualizada.setId(id);
        return clienteRepository.save(clienteActualizada);
    }

    @PutMapping("/map/{id}")
    public Cliente updateClienteFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = mapper.convertValue(body, Cliente.class);
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    
	@DeleteMapping("/{id}")
	public Cliente deleteCliente(@PathVariable String id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
		clienteRepository.deleteById(id);
		return cliente;
	}

}
