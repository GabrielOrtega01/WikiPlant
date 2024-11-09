package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongowikiplant.app.entity.Cliente;
import com.mongowikiplant.app.entity.Planta;
import com.mongowikiplant.app.repository.ClienteRepository;
import com.mongowikiplant.app.repository.PlantaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "cliente")
public class ControllerWebCliente {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PlantaRepository plantaRepository;

    @GetMapping("/index")
    public String clienteIndexTemplate(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        // Obtener la lista de plantas para mostrar en el desplegable
        model.addAttribute("plantas", plantaRepository.findAll());

        return "index-cliente";
    }

    @PostMapping("/buscarPlanta")
    public String buscarPlanta(@RequestParam("plantaId") String plantaId, Model model, HttpSession session) {
        // Obtener los datos del cliente para mantener el nombre y apellido en la vista
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }

        // Cargar la lista de plantas para el desplegable
        model.addAttribute("plantas", plantaRepository.findAll());

        // Buscar la planta seleccionada por su ID
        Planta planta = plantaRepository.findById(plantaId).orElse(null);
        if (planta != null) {
            // Pasar los detalles de la planta seleccionada al modelo
            model.addAttribute("planta", planta);
        }

        return "index-cliente"; // Volver a mostrar index-cliente con la lista de plantas y los detalles de la planta seleccionada
    }

    
    @GetMapping("/plantaDetalle")
    public String mostrarDetallePlanta(@RequestParam("plantaId") String plantaId, Model model) {
        Planta planta = plantaRepository.findById(plantaId).orElse(null);
        model.addAttribute("planta", planta);
        return "planta-detalle"; // Renderiza la nueva página con los detalles de la planta en una tabla
    }

    

    @PostMapping("/logear")
    public String clienteLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        Cliente cliente = null;
        for (Cliente c : clienteRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
                cliente = c;
                break;
            }
        }

        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogeado", cliente);
            return "redirect:/cliente/index";
        } else {
            model.addAttribute("error", true);
            return "login-cliente";
        }
    }

    @GetMapping("/login")
    public String clienteLoginTemplate(Model model) {
        return "login-cliente";
    }
}
