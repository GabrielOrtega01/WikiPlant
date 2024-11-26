package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Cultivo;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.CultivoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/cultivo")
public class ControllerWebCultivo {

    @Autowired
    private CultivoRepository cultivoRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String cultivosNewTemplate(Model model) {
        model.addAttribute("cultivo", new Cultivo());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "cultivo-form";
    }

    @GetMapping("/lista")
    public String cultivosListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("cultivos", cultivoRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "cultivo-lista";
    }

    @GetMapping("/edit/{id}")
    public String cultivoEditTemplate(@PathVariable("id") String id, Model model) {
        Cultivo cultivo = cultivoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cultivo no encontrado"));
        model.addAttribute("cultivo", cultivo);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "cultivo-form";
    }

    @PostMapping("/save")
    public String cultivosSaveProcess(@Valid @ModelAttribute("cultivo") Cultivo cultivo, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "cultivo-form";
        }
        if (cultivo.getId() == null || cultivo.getId().isEmpty()) {
        	cultivo.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el cultivo
        cultivoRepository.save(cultivo);

        // Redirigir al usuario a la lista de cultivos después de guardar los datos
        return "redirect:/cultivo/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String cultivoDeleteProcess(@PathVariable("id") String id) {
    	cultivoRepository.deleteById(id);
        return "redirect:/cultivo/lista";
    }

    
    @GetMapping("/info")
    public String cultivoInfoTemplate() {
        return "cultivo-info";
    }

}
