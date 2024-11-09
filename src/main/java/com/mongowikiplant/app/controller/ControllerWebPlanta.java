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

    // Página para crear una nueva planta
    @GetMapping("/crear")
    public String plantaCrearTemplate(Model model) {
        model.addAttribute("planta", new Planta());
        return "planta-form"; // Vista para el formulario de creación de planta
    }

    // Página que muestra la lista de plantas
    @GetMapping("/lista")
    public String plantaListTemplate(Model model) {
        model.addAttribute("plantas", plantaRepository.findAll());
        return "planta-lista"; // Vista que muestra la lista de plantas
    }

    // Página para editar una planta específica
    @GetMapping("/edit/{id}")
    public String plantaEditTemplate(@PathVariable("id") String id, Model model) {
        Planta planta = plantaRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException("Planta no encontrada"));
        model.addAttribute("planta", planta);
        return "planta-form"; // Reutilizamos la vista del formulario para edición
    }

    // Procesa la solicitud de guardar una nueva planta o actualizar una existente
    @PostMapping("/save")
    public String plantaSaveProcess(@ModelAttribute("planta") Planta planta) {
        if (planta.getId() == null || planta.getId().isEmpty()) {
            planta.setId(null); // Asigna null para que se genere un nuevo ID si es un nuevo registro
        }
        plantaRepository.save(planta);
        return "redirect:/planta/lista"; // Redirige a la lista de plantas tras guardar
    }

    // Procesa la solicitud de eliminar una planta específica
    @GetMapping("/delete/{id}")
    public String plantaDeleteProcess(@PathVariable("id") String id) {
        plantaRepository.deleteById(id);
        return "redirect:/planta/lista"; // Redirige a la lista de plantas tras eliminar
    }
}
