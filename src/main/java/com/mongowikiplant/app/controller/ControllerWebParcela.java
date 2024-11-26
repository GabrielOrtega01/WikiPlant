package com.mongowikiplant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Parcela;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.ParcelaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/parcela")
public class ControllerWebParcela {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping("/crear")
    public String parcelasNewTemplate(Model model) {
        model.addAttribute("parcela", new Parcela());
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "parcela-form";
    }

    @GetMapping("/lista")
    public String parcelasListTemplate(Model model) {
        // Ordenar por el campo 'fecha' de manera ascendente
        model.addAttribute("parcelas", parcelaRepository.findAll(Sort.by(Sort.Order.asc("fecha"))));
        return "parcela-lista";
    }

    @GetMapping("/edit/{id}")
    public String parcelaEditTemplate(@PathVariable("id") String id, Model model) {
        Parcela parcela = parcelaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parcela no encontrado"));
        model.addAttribute("parcela", parcela);
        model.addAttribute("listaEstaciones", estacionRepository.findAll());
        return "parcela-form";
    }

    @PostMapping("/save")
    public String parcelasSaveProcess(@Valid @ModelAttribute("parcela") Parcela parcela, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
            model.addAttribute("listaEstaciones", estacionRepository.findAll());
            return "parcela-form";
        }
        if (parcela.getId() == null || parcela.getId().isEmpty()) {
        	parcela.setId(null); // MongoDB generará un ID automáticamente
        }

        // Si no hay errores, se guarda el parcela
        parcelaRepository.save(parcela);

        // Redirigir al usuario a la lista de parcelas después de guardar los datos
        return "redirect:/parcela/lista";
    }
    
    @GetMapping("/delete/{id}")
    public String parcelaDeleteProcess(@PathVariable("id") String id) {
    	parcelaRepository.deleteById(id);
        return "redirect:/parcela/lista";
    }

    
    @GetMapping("/info")
    public String parcelaInfoTemplate() {
        return "parcela-info";
    }

}
