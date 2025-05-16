// src/main/java/com/example/transcaribe/controller/AdminConductorController.java
package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Conductor;
import com.example.transcaribe.entity.Horario;
import com.example.transcaribe.services.ConductorService;
import com.example.transcaribe.services.HorarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/conductores")
public class AdminConductorController {

    private final ConductorService conductorService;
    private final HorarioService horarioService;

    public AdminConductorController(ConductorService conductorService,
                                    HorarioService horarioService) {
        this.conductorService = conductorService;
        this.horarioService   = horarioService;
    }

    /** 1. Listado de conductores **/
    @GetMapping
    public String listar(HttpSession session, Model model) {
        // Comprueba sesión de administrador
        if (session.getAttribute("administradorLogueado") == null) {
            return "redirect:/admin/loginAdm";
        }
        model.addAttribute("conductores", conductorService.obtenerTodos());
        return "admin/conductores/list";
    }

    /** 2. Formulario para crear un nuevo conductor **/
    @GetMapping("/nuevo")
    public String nuevo(HttpSession session, Model model) {
        if (session.getAttribute("administradorLogueado") == null) {
            return "redirect:/admin/loginAdm";
        }
        model.addAttribute("conductor", new Conductor());
        return "admin/conductores/form";
    }

    /** 3. Procesar la creación del conductor **/
    @PostMapping("/nuevo")
    public String crear(@ModelAttribute Conductor conductor) {
        conductorService.crear(conductor);
        return "redirect:/admin/conductores";
    }

    /** 4. Formulario para asignar un horario a un conductor **/
    @GetMapping("/{id}/horarios/nuevo")
    public String nuevoHorario(@PathVariable("id") String conductorId,
                               HttpSession session,
                               Model model) {
        if (session.getAttribute("administradorLogueado") == null) {
            return "redirect:/admin/loginAdm";
        }
        // Prepara un objeto Horario con el conductorId ya seteado
        Horario horario = new Horario();
        horario.setConductorId(conductorId);
        model.addAttribute("conductorId", conductorId);
        model.addAttribute("horario", horario);
        return "admin/conductores/horario_form";
    }

    /** 5. Procesar el envío del formulario de horario **/
    @PostMapping("/{id}/horarios/nuevo")
    public String crearHorario(@PathVariable("id") String conductorId,
                               @ModelAttribute Horario horario) {
        // El objeto horario ya lleva conductorId (viene del campo hidden en el form)
        horarioService.guardarHorario(horario);
        return "redirect:/admin/conductores";
    }

    // (Opcional) 6. Ver o editar un conductor existente
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable("id") String id,
                             HttpSession session,
                             Model model) {
        if (session.getAttribute("administradorLogueado") == null) {
            return "redirect:/admin/loginAdm";
        }
        Conductor c = conductorService.buscarPorId(id);
        model.addAttribute("conductor", c);
        return "admin/conductores/form";
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable("id") String id,
                             @ModelAttribute Conductor conductor) {
        conductorService.actualizar(id, conductor);
        return "redirect:/admin/conductores";
    }

    // (Opcional) 7. Eliminar conductor
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable("id") String id) {
        conductorService.eliminar(id);
        return "redirect:/admin/conductores";
    }
}
