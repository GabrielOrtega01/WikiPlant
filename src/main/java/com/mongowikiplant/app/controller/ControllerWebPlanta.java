package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongowikiplant.app.entity.Planta;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.PlantaRepository;

@Controller
@RequestMapping("/planta")
public class ControllerWebPlanta {

    @Autowired
    private PlantaRepository plantaRepository;

    @GetMapping("/crear")
    public String plantaCrearTemplate(Model model) {
        model.addAttribute("planta", new Planta());
        return "planta-form"; 
    }

    @GetMapping("/lista")
    public String plantaListTemplate(Model model) {
        model.addAttribute("plantas", plantaRepository.findAll());
        return "planta-lista";
    }

    @GetMapping("/edit/{id}")
    public String plantaEditTemplate(@PathVariable("id") String id, Model model) {
        Planta planta = plantaRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException("Planta no encontrada"));
        model.addAttribute("planta", planta);
        return "planta-form";
    }

    @PostMapping("/save")
    public String plantaSaveProcess(@ModelAttribute("planta") Planta planta) {
        if (planta.getId() == null || planta.getId().isEmpty()) {
            planta.setId(null); 
        }
        plantaRepository.save(planta);
        return "redirect:/planta/lista";
    }

    
    @GetMapping("/delete/{id}")
    public String plantaDeleteProcess(@PathVariable("id") String id) {
        plantaRepository.deleteById(id);
        return "redirect:/planta/lista"; 
    }
}
