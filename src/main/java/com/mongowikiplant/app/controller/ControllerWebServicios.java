package com.mongowikiplant.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerWebServicios {
	@GetMapping("/servicios")
	public String compartidoIndexTemplate(Model model) {
		return "servicios";
	}
	
	@GetMapping("/vermas")
	public String adminLogearTemplate(Model model) {
		return "/login-administrador";
	}
	
}
