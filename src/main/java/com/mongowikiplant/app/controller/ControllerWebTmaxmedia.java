package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Tmaxmedia;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.TmaxmediaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/tmaxmedia")
public class ControllerWebTmaxmedia {

    @Autowired
    private TmaxmediaRepository tmaxmediaRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String tmaxmediasNewTemplate(Model model) {
        model.addAttribute("tmaxmedia", new Tmaxmedia());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "tmaxmedia-form";
    }

    @GetMapping("/lista")
    public String tmaxmediasListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("tmaxmedias", tmaxmediaRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "tmaxmedia-lista";
    }

    @GetMapping("/edit/{id}")
    public String tmaxmediaEditTemplate(@PathVariable("id") String id, Model model) {
        Tmaxmedia tmaxmedia = tmaxmediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tmaxmedia no encontrado"));
        model.addAttribute("tmaxmedia", tmaxmedia);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "tmaxmedia-form";
    }

    @PostMapping("/save")
    public String tmaxmediasSaveProcess(@Valid @ModelAttribute("tmaxmedia") Tmaxmedia tmaxmedia, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "tmaxmedia-form";
        }
        if (tmaxmedia.getId() == null || tmaxmedia.getId().isEmpty()) {
        	tmaxmedia.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el tmaxmedia
        tmaxmediaRepository.save(tmaxmedia);

        // Redirigir al usuario a la lista de tmaxmedias después de guardar los datos
        return "redirect:/tmaxmedia/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String tmaxmediaDeleteProcess(@PathVariable("id") String id) {
    	tmaxmediaRepository.deleteById(id);
        return "redirect:/tmaxmedia/lista";
    }

    
    @GetMapping("/info")
    public String tmaxmediaInfoTemplate() {
        return "tmaxmedia-info";
    }

}
