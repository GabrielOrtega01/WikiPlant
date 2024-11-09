package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Cultivo;
import com.mongowikiplant.app.repository.CultivoRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar operaciones CRUD de la entidad Cultivo.
 */
@Controller
@RequestMapping("cultivo")
public class ControllerWebCultivo {

    @Autowired
    private CultivoRepository cultivoRepository;

    /**
     * Muestra la lista de todos los cultivos.
     *
     * @param model Modelo para la vista.
     * @return Vista "cultivo-lista" con la lista de cultivos.
     */
    @GetMapping("/listar")
    public String listarCultivos(Model model) {
        model.addAttribute("cultivos", cultivoRepository.findAll());
        return "cultivo-lista";
    }

    /**
     * Muestra el formulario para crear un nuevo cultivo.
     *
     * @param model Modelo para la vista.
     * @return Vista "cultivo-form" para el formulario de creación.
     */
    @GetMapping("/crear")
    public String crearCultivoForm(Model model) {
        model.addAttribute("cultivo", new Cultivo());
        return "cultivo-form";
    }
    
    /**
     * Guarda un cultivo en la base de datos. Si hay errores en la validación, regresa al formulario.
     *
     * @param cultivo El objeto Cultivo a guardar.
     * @param result  Resultado de la validación.
     * @return Redirecciona a la vista de lista o regresa al formulario si hay errores.
     */
    @PostMapping("/guardar")
    public String guardarCultivo(@Valid @ModelAttribute Cultivo cultivo, BindingResult result) {
        if (result.hasErrors()) {
            return "cultivo-form";
        }
        cultivoRepository.save(cultivo);
        return "redirect:/cultivo/listar";
    }

    /**
     * Muestra el formulario para editar un cultivo existente.
     *
     * @param id    Identificador del cultivo a editar.
     * @param model Modelo para la vista.
     * @return Vista "cultivo-form" con los datos del cultivo cargados.
     */
    @GetMapping("/editar/{id}")
    public String editarCultivoForm(@PathVariable String id, Model model) {
        Cultivo cultivo = cultivoRepository.findById(id).orElse(null);
        model.addAttribute("cultivo", cultivo);
        return "cultivo-form";
    }

    /**
     * Elimina un cultivo por su ID y redirecciona a la lista de cultivos.
     *
     * @param id Identificador del cultivo a eliminar.
     * @return Redirecciona a la vista de lista después de eliminar.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarCultivo(@PathVariable String id) {
        cultivoRepository.deleteById(id);
        return "redirect:/cultivo/listar";
    }
}
