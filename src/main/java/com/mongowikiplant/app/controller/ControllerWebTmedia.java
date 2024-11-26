package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Tmedia;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.TmediaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/tmedia")
public class ControllerWebTmedia {

    @Autowired
    private TmediaRepository tmediaRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String tmediasNewTemplate(Model model) {
        model.addAttribute("tmedia", new Tmedia());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "tmedia-form";
    }

    @GetMapping("/lista")
    public String tmediasListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("tmedias", tmediaRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "tmedia-lista";
    }

    @GetMapping("/edit/{id}")
    public String tmediaEditTemplate(@PathVariable("id") String id, Model model) {
        Tmedia tmedia = tmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tmedia no encontrado"));
        model.addAttribute("tmedia", tmedia);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "tmedia-form";
    }

    @PostMapping("/save")
    public String tmediasSaveProcess(@Valid @ModelAttribute("tmedia") Tmedia tmedia, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "tmedia-form";
        }
        if (tmedia.getId() == null || tmedia.getId().isEmpty()) {
        	tmedia.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el tmedia
        tmediaRepository.save(tmedia);

        // Redirigir al usuario a la lista de tmedias después de guardar los datos
        return "redirect:/tmedia/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String tmediaDeleteProcess(@PathVariable("id") String id) {
    	tmediaRepository.deleteById(id);
        return "redirect:/tmedia/lista";
    }

    
    @GetMapping("/info")
    public String tmediaInfoTemplate() {
        return "tmedia-info";
    }

}
