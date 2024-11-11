package com.mongowikiplant.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerWebContactenos {
	@GetMapping("/contactenos")
	public String compartidoIndexTemplate(Model model) {
		return "contactenos";
	}
}
