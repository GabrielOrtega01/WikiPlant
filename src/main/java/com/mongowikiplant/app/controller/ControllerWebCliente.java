package com.mongowikiplant.app.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongowikiplant.app.entity.Cliente;
import com.mongowikiplant.app.entity.Planta;
import com.mongowikiplant.app.repository.ClienteRepository;
import com.mongowikiplant.app.repository.PlantaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/cliente")
public class ControllerWebCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PlantaRepository plantaRepository;

    @GetMapping("/index")
    public String clienteIndexTemplate(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }

        // Obtener la lista de plantas para mostrar en el desplegable
        model.addAttribute("plantas", plantaRepository.findAll());

        return "index-cliente";
    }

    @PostMapping("/buscarPlanta")
    public String buscarPlanta(@RequestParam("plantaId") String plantaId, Model model, HttpSession session) {
        // Obtener los datos del cliente para mantener el nombre y apellido en la vista
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }

        // Cargar la lista de plantas para el desplegable
        model.addAttribute("plantas", plantaRepository.findAll());

        // Buscar la planta seleccionada por su ID
        Planta planta = plantaRepository.findById(plantaId).orElse(null);
        if (planta != null) {
            // Pasar los detalles de la planta seleccionada al modelo
            model.addAttribute("planta", planta);
        }

        return "index-cliente"; // Volver a mostrar index-cliente con la lista de plantas y los detalles de la
                                // planta seleccionada
    }

    @PostMapping("/logear")
    public String clienteLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model,
            HttpSession session) {
        Cliente cliente = null;
        for (Cliente c : clienteRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
                cliente = c;
                break;
            }
        }

        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogeado", cliente);
            return "redirect:/cliente/index";
        } else {
            model.addAttribute("error", true);
            return "login-cliente";
        }
    }

    @GetMapping("/login")
    public String clienteLoginTemplate(Model model) {
        return "login-cliente";
    }

    @GetMapping("/masBuscadas")
    public String mostrarMasBuscadas(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }

        // Obtener las plantas ordenadas por búsquedas (descendente)
        List<Planta> plantasMasBuscadas = plantaRepository.findTop5ByOrderByBusquedasDesc();

        if (plantasMasBuscadas.isEmpty()) {
            model.addAttribute("mensaje", "No hay plantas buscadas todavía.");
        } else {
            model.addAttribute("plantasMasBuscadas", plantasMasBuscadas);
        }

        return "mas-buscadas"; // Vista para mostrar las plantas más buscadas
    }

    @PostMapping("/guardarNotaPlanta")
    public String guardarNotaPlanta(
            @RequestParam("plantaId") String plantaId,
            @RequestParam("nota") String nota,
            HttpSession session,
            Model model) {

        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (cliente != null) {
            // Verificar si el mapa de notas existe, si no, inicializarlo
            if (cliente.getNotasPlantas() == null) {
                cliente.setNotasPlantas(new HashMap<>());
            }

            // Guardar o actualizar la nota para la planta específica
            cliente.getNotasPlantas().put(plantaId, nota);
            clienteRepository.save(cliente); // Guardar cambios en la base de datos

            model.addAttribute("mensaje", "Nota guardada con éxito.");
        } else {
            model.addAttribute("error", "No se encontró el cliente logeado.");
        }

        return "redirect:/cliente/plantaDetalle?plantaId=" + plantaId; // Redirigir al detalle de la planta
    }

    @GetMapping("/plantaDetalle")
    public String mostrarDetallePlanta(@RequestParam("plantaId") String plantaId, Model model, HttpSession session) {
        Planta planta = plantaRepository.findById(plantaId).orElse(null);
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (planta != null) {
            planta.incrementarBusquedas(); // Incrementar el contador de búsquedas
            plantaRepository.save(planta); // Guardar cambios en la base de datos

            // Agregar los detalles de la planta al modelo
            model.addAttribute("planta", planta);

            // Verificar si hay una nota para esta planta guardada por el cliente
            if (cliente != null && cliente.getNotasPlantas() != null) {
                String notaCliente = cliente.getNotasPlantas().get(plantaId);
                if (notaCliente != null) {
                    System.out.println("Nota encontrada: " + notaCliente); // Log para verificar
                    model.addAttribute("notaCliente", notaCliente); // Agregar la nota al modelo
                } else {
                    System.out.println("No hay nota para esta planta."); // Log para verificar
                    model.addAttribute("notaCliente", "No has agregado una nota para esta planta.");
                }
            }
        } else {
            model.addAttribute("error", "Planta no encontrada.");
        }

        return "planta-detalle"; // Mostrar la vista con los detalles de la planta y la nota
    }

    @PostMapping("/guardarNota")
    public String guardarNota(@RequestParam("plantaId") String plantaId,
            @RequestParam("nota") String nota,
            HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (cliente != null) {
            // Guardar la nota en el mapa de notas de la planta
            cliente.getNotasPlantas().put(plantaId, nota);
            clienteRepository.save(cliente); // Guardar cambios en la base de datos

            // Log para verificar que la nota se guarda
            System.out.println("Nota guardada para la planta con ID: " + plantaId + " - Nota: " + nota);
        } else {
            System.out.println("Cliente no encontrado en sesión.");
        }

        return "redirect:/cliente/plantaDetalle?plantaId=" + plantaId;
    }

    @PostMapping("/agregarAFavoritos")
    public String agregarAFavoritos(@RequestParam("plantaId") String plantaId, HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (cliente != null) {
            // Verificar si el conjunto de plantas favoritas existe, si no, inicializarlo
            if (cliente.getPlantasFavoritas() == null) {
                cliente.setPlantasFavoritas(new HashSet<>());
            }

            // Añadir la planta a la lista de favoritos si no está ya presente
            cliente.getPlantasFavoritas().add(plantaId);
            clienteRepository.save(cliente); // Guardar cambios en la base de datos

            model.addAttribute("mensaje", "Planta añadida a favoritos.");
        } else {
            model.addAttribute("error", "No se encontró el cliente logeado.");
        }

        return "redirect:/cliente/plantaDetalle?plantaId=" + plantaId;
    }

    @GetMapping("/favoritos")
    public String mostrarFavoritos(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (cliente != null && cliente.getPlantasFavoritas() != null) {
            // Recuperar la lista de plantas favoritas del cliente
            List<Planta> plantasFavoritas = plantaRepository.findAllById(cliente.getPlantasFavoritas());
            model.addAttribute("plantasFavoritas", plantasFavoritas);
        } else {
            model.addAttribute("mensaje", "No tienes plantas favoritas.");
        }

        return "favoritos"; // Vista que muestra las plantas favoritas
    }

    @PostMapping("/eliminarDeFavoritos")
    public String eliminarDeFavoritos(@RequestParam("plantaId") String plantaId, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");

        if (cliente != null) {
            // Eliminar la planta de la lista de favoritos
            cliente.getPlantasFavoritas().remove(plantaId);
            clienteRepository.save(cliente); // Guardar cambios en la base de datos
        }

        return "redirect:/cliente/favoritos"; // Redirigir de nuevo a la página de favoritos
    }

}
