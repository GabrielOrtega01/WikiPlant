package com.mongowikiplant.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerWebCompartido {
	@GetMapping("/")
	public String compartidoIndexTemplate(Model model) {
		return "index-compartido";
	}
	@Controller
	public class ControllerWebNosotros {
		@GetMapping("/wikiplant")
		public String compartidoIndexTemplate(Model model) {
			return "wikiplant";
		}
	}

}
