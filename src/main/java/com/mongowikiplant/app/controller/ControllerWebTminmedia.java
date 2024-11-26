package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Tminmedia;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.TminmediaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/tminmedia")
public class ControllerWebTminmedia {

    @Autowired
    private TminmediaRepository tminmediaRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String tminmediasNewTemplate(Model model) {
        model.addAttribute("tminmedia", new Tminmedia());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "tminmedia-form";
    }

    @GetMapping("/lista")
    public String tminmediasListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("tminmedias", tminmediaRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "tminmedia-lista";
    }

    @GetMapping("/edit/{id}")
    public String tminmediaEditTemplate(@PathVariable("id") String id, Model model) {
        Tminmedia tminmedia = tminmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tminmedia no encontrado"));
        model.addAttribute("tminmedia", tminmedia);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "tminmedia-form";
    }

    @PostMapping("/save")
    public String tminmediasSaveProcess(@Valid @ModelAttribute("tminmedia") Tminmedia tminmedia, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "tminmedia-form";
        }
        if (tminmedia.getId() == null || tminmedia.getId().isEmpty()) {
        	tminmedia.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el tminmedia
        tminmediaRepository.save(tminmedia);

        // Redirigir al usuario a la lista de tminmedias después de guardar los datos
        return "redirect:/tminmedia/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String tminmediaDeleteProcess(@PathVariable("id") String id) {
    	tminmediaRepository.deleteById(id);
        return "redirect:/tminmedia/lista";
    }

    
    @GetMapping("/info")
    public String tminmediaInfoTemplate() {
        return "tminmedia-info";
    }

}
