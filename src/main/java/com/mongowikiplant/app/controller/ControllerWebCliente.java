package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import com.mongowikiplant.app.entity.Cliente;
import com.mongowikiplant.app.repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "cliente")
	
public class ControllerWebCliente {
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@GetMapping("/resultadoTotal")
    public String clienteResultadoTotalTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "cliente-resultado-total";
    }
	
	@GetMapping("/resultadoUnico")
    public String clienteResultadoUnicoTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "cliente-resultado";
    }
	
	@GetMapping("/index")
    public String clienteIndexTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "index-cliente";
    }
	
	@PostMapping("/logear")
    public String clienteLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        // Buscar al administrador por nombre de usuario en la base de datos
        Cliente cliente = null;
        for (Cliente c : clienteRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
            	cliente = c;
                break;
            }
        }
        
        // Verificar si se encontró al administrador y si la contraseña es correcta
        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            // Guardar el usuario logeado en la sesión
            session.setAttribute("usuarioLogeado", cliente);
            // Si las credenciales son correctas, redirigir a la página de inicio
            return "redirect:/cliente/index";
        } else {
            // Si las credenciales son incorrectas, mostrar un mensaje de error y volver al formulario de login
            model.addAttribute("error", true);
            return "login-administrador";
        }
    }
	
	@GetMapping("/login")
	public String clienteLoginTemplate(Model model) {
		return "login-cliente";
	}
	
	
	@GetMapping("/informe/{id}")
	public String verInformeCliente(@PathVariable("id") String id, Model model) {
	   
	    Cliente cliente = clienteRepository.findById(id).orElse(null);
	    
	    if (cliente != null) {
	       
	    	model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());	        
	    }
	    
	    return "informe-cliente"; 
	}

	
	@GetMapping("/ranking")
	public String mostrarRanking(Model model) {
	    // Obtener los 3 clientes con las notas más altas
	    List<Cliente> ranking = clienteRepository.findTop3ByOrderByPuntajeDesc(); // Asumiendo que 'puntaje' es el nombre del campo en tu entidad Cliente
	    model.addAttribute("ranking", ranking);
	    return "ranking"; // Devuelve la vista de ranking
	}

	
	
}
