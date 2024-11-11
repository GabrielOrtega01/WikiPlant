package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongowikiplant.app.entity.Administrador;
import com.mongowikiplant.app.entity.Cliente;
import com.mongowikiplant.app.entity.Planta;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.AdministradorRepository;
import com.mongowikiplant.app.repository.ClienteRepository;
import com.mongowikiplant.app.repository.PlantaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "administrador")
public class ControllerWebAdministrador {
    
    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private PlantaRepository plantaRepository;
    
    @GetMapping("/index")
    public String administradorIndexTemplate(Model model, HttpSession session) {
        
    	Administrador administrador = (Administrador) session.getAttribute("usuarioLogeado");
        
        if (administrador != null) {
            model.addAttribute("usuario", administrador.getUsuario());
            model.addAttribute("nombre", administrador.getNombre());
        }
        
        return "index-administrador";
    }
    
    @GetMapping("/login")
    public String administradorLoginTemplate(Model model) {
        return "login-administrador";
    }
    
    @PostMapping("/logear")
    public String administradorLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
       
        Administrador administrador = null;
        for (Administrador c : administradorRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
                administrador = c;
                break;
            }
        }
        
        if (administrador != null && administrador.getContrasena().equals(contrasena)) {
            
            session.setAttribute("usuarioLogeado", administrador);
            return "redirect:/administrador/index";
        } else {
        	
            model.addAttribute("error", true);
            return "login-administrador";
        }
    }
    
    @GetMapping("/cliente/crear")
    public String administradorCrearTemplate(Model model) {
		model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }
	
	@GetMapping("/lista")
	public String asociacionListTemplate(Model model) {
		model.addAttribute("clientes", clienteRepository.findAll());
		return "cliente-lista";
	}

	@GetMapping("/cliente/edit/{id}")
	public String administradorEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("cliente",
				clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("cliente no encontrada")));
		return "cliente-form";
	}

	@PostMapping("/cliente/save")
	public String administradorSaveProcess(@ModelAttribute("cliente") Cliente cliente) {
		if (cliente.getId().isEmpty()) {
			cliente.setId(null);
		}
		clienteRepository.save(cliente);
		return "redirect:/administrador/index";
	}

	@GetMapping("/cliente/delete/{id}")
	public String administradorDeleteProcess(@PathVariable("id") String id) {
		clienteRepository.deleteById(id);
		return "redirect:/administrador/lista";
	}
	

	
	@GetMapping("/planta/crear")
	public String plantaCrearTemplate(Model model) {
	    model.addAttribute("planta", new Planta());
	    return "planta-form";
	}

	@GetMapping("/planta/lista")
	public String plantaListTemplate(Model model) {
	    model.addAttribute("plantas", plantaRepository.findAll());
	    return "planta-lista";
	}

	@GetMapping("/planta/edit/{id}")
	public String plantaEditTemplate(@PathVariable("id") String id, Model model) {
	    model.addAttribute("planta",
	            plantaRepository.findById(id).orElseThrow(() -> new NotFoundException("planta no encontrada")));
	    return "planta-form";
	}

	@PostMapping("/planta/save")
	public String plantaSaveProcess(@ModelAttribute("planta") Planta planta) {
	    if (planta.getId().isEmpty()) {
	        planta.setId(null);
	    }
	    plantaRepository.save(planta);
	    return "redirect:/administrador/index";
	}

	@GetMapping("/planta/delete/{id}")
	public String plantaDeleteProcess(@PathVariable("id") String id) {
	    plantaRepository.deleteById(id);
	    return "redirect:/administrador/lista";
	}
	
}
