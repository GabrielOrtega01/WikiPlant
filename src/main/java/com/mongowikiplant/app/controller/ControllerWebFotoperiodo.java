package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mongowikiplant.app.entity.Fotoperiodo;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;
import com.mongowikiplant.app.repository.FotoperiodoRepository;
import com.mongowikiplant.app.service.FotoperiodoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/fotoperiodo")
public class ControllerWebFotoperiodo {

  @Autowired
  private FotoperiodoRepository fotoperiodoRepository;

  @Autowired
  private EstacionRepository estacionRepository;

  @Autowired
  private FotoperiodoService fotoperiodoService;

  @GetMapping("/crear")
  public String fotoperiodosNewTemplate(Model model) {
    model.addAttribute("fotoperiodo", new Fotoperiodo());
    model.addAttribute("listaEstaciones", estacionRepository.findAll());
    return "fotoperiodo-form";
  }
  @GetMapping("/lista")
  public String fotoperiodosListTemplate(Model model) {
      // Obtener todos los fotoperiodos y ordenarlos por la fecha
      List<Fotoperiodo> fotoperiodos = fotoperiodoRepository.findAll(Sort.by(Sort.Order.asc("fecha")));

      // Calcular los conteos totales para todos los meses sin agrupar por estación
      Map<String, Integer> conteosTotales = fotoperiodoService.calcularConteosTotales();
      Map<String, Double> promedios = fotoperiodoService.calcularPromedioTotales();
      Map<String, Double> sumaDiferenciasCuadradoAnual = fotoperiodoService.calcularDesviacionEstandar();
      Map<String, Double> coeficienteDeVariacion = fotoperiodoService.calcularCoeficienteVariacion();
      Map<String, Double> desviaciones = fotoperiodoService.calcularDesviaciones(); // Completa este método en tu servicio

      model.addAttribute("fotoperiodos", fotoperiodos);
      model.addAttribute("conteosTotales", conteosTotales);
      model.addAttribute("promedios", promedios);
      model.addAttribute("sumaDiferenciasCuadradoAnual", sumaDiferenciasCuadradoAnual);
      model.addAttribute("coeficienteDeVariacion", coeficienteDeVariacion);
      model.addAttribute("desviaciones", desviaciones);

      return "fotoperiodo-lista";
  }


  @PostMapping("/save")
  public String fotoperiodosSaveProcess(@Valid @ModelAttribute("fotoperiodo") Fotoperiodo fotoperiodo,
      BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      // Si hay errores en los datos del formulario, se vuelve a mostrar el formulario
      model.addAttribute("listaEstaciones", estacionRepository.findAll());
      return "fotoperiodo-form";
    }
    if (fotoperiodo.getId() == null || fotoperiodo.getId().isEmpty()) {
      fotoperiodo.setId(null); // MongoDB generará un ID automáticamente
    }

    // Si no hay errores, se guarda el fotoperiodo
    fotoperiodoRepository.save(fotoperiodo);

    // Redirigir al usuario a la lista de fotoperiodos después de guardar los datos
    return "redirect:/fotoperiodo/lista";
  }

  @GetMapping("/delete/{id}")
  public String fotoperiodoDeleteProcess(@PathVariable("id") String id) {
    fotoperiodoRepository.deleteById(id);
    return "redirect:/fotoperiodo/lista";
  }

  @GetMapping("/info")
  public String fotoperiodoInfoTemplate() {
    return "fotoperiodo-info";
  }
  
  @GetMapping("/edit/{id}")
  public String fotoperiodoEditTemplate(@PathVariable("id") String id, Model model) {
  	Fotoperiodo fotoperiodo = fotoperiodoRepository.findById(id)
              .orElseThrow(() -> new NotFoundException("Fotoperiodo no encontrado"));
  	model.addAttribute("fotoperiodo", fotoperiodo);
    model.addAttribute("listaEstaciones", estacionRepository.findAll());
      return "fotoperiodo-form";
  }

}



















