package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Conductor;
import com.example.transcaribe.entity.Horario;
import com.example.transcaribe.services.ConductorService;
import com.example.transcaribe.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/conductores")
public class AdminConductorController {

    private final ConductorService conductorService;
    private final HorarioService horarioService;

    @Autowired
    public AdminConductorController(ConductorService conductorService,
                                    HorarioService horarioService) {
        this.conductorService = conductorService;
        this.horarioService   = horarioService;
    }

    /**
     * Lista todos los conductores.
     */
    @GetMapping
    public String listarConductores(Model model) {
        model.addAttribute("conductores", conductorService.listar());
        return "admin/conductores/list";   // Thymeleaf: list.html
    }

    /**
     * Muestra el formulario para crear un nuevo conductor.
     */
    @GetMapping("/nuevo")
    public String mostrarFormCrearConductor(Model model) {
        model.addAttribute("conductor", new Conductor());
        return "admin/conductores/form";   // Thymeleaf: form.html
    }

    /**
     * Procesa la creación del conductor y redirige al listado.
     */
    @PostMapping("/nuevo")
    public String crearConductor(@ModelAttribute Conductor conductor) {
        conductorService.crear(conductor);
        return "redirect:/admin/conductores";
    }

    /**
     * Muestra el formulario para asignar un horario a un conductor.
     */
    @GetMapping("/{id}/horarios/nuevo")
    public String mostrarFormCrearHorario(@PathVariable String id, Model model) {
        Horario h = new Horario();
        h.setConductorId(id);
        model.addAttribute("horario", h);
        return "admin/conductores/horario_form";  // Thymeleaf: horario_form.html
    }

    /**
     * Procesa la asignación de horario y vuelve al listado de conductores.
     */
    @PostMapping("/{id}/horarios/nuevo")
    public String crearHorario(@ModelAttribute Horario horario) {
        horarioService.guardarHorario(horario);
        return "redirect:/admin/conductores";
    }
}
