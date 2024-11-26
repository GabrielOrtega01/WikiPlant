package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Amplitud;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.AmplitudRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/amplitud")
public class ControllerWebAmplitud {

    @Autowired
    private AmplitudRepository amplitudRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String amplitudesNewTemplate(Model model) {
        model.addAttribute("amplitud", new Amplitud());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "amplitud-form";
    }

    @GetMapping("/lista")
    public String amplitudesListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("amplitudes", amplitudRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "amplitud-lista";
    }

    @GetMapping("/edit/{id}")
    public String amplitudEditTemplate(@PathVariable("id") String id, Model model) {
        Amplitud amplitud = amplitudRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Amplitud no encontrado"));
        model.addAttribute("amplitud", amplitud);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "amplitud-form";
    }

    @PostMapping("/save")
    public String amplitudesSaveProcess(@Valid @ModelAttribute("amplitud") Amplitud amplitud, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "amplitud-form";
        }
        if (amplitud.getId() == null || amplitud.getId().isEmpty()) {
        	amplitud.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el amplitud
        amplitudRepository.save(amplitud);

        // Redirigir al usuario a la lista de amplitudes después de guardar los datos
        return "redirect:/amplitud/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String amplitudDeleteProcess(@PathVariable("id") String id) {
    	amplitudRepository.deleteById(id);
        return "redirect:/amplitud/lista";
    }

    
    @GetMapping("/info")
    public String amplitudInfoTemplate() {
        return "amplitud-info";
    }

}
