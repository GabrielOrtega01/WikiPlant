package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Parcela;
import com.mongowikiplant.app.repository.ParcelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("parcela")
public class ControllerWebParcela {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @GetMapping("/listar")
    public String listarParcelas(Model model) {
        model.addAttribute("parcelas", parcelaRepository.findAll());
        return "parcela-lista";
    }

    @GetMapping("/crear")
    public String crearParcelaForm(Model model) {
        model.addAttribute("parcela", new Parcela());
        return "parcela-form";
    }

    @PostMapping("/guardar")
    public String guardarParcela(@ModelAttribute Parcela parcela) {
        parcelaRepository.save(parcela);
        return "redirect:/parcela/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarParcelaForm(@PathVariable String id, Model model) {
        Parcela parcela = parcelaRepository.findById(id).orElse(null);
        model.addAttribute("parcela", parcela);
        return "parcela-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarParcela(@PathVariable String id) {
        parcelaRepository.deleteById(id);
        return "redirect:/parcela/listar";
    }
}
