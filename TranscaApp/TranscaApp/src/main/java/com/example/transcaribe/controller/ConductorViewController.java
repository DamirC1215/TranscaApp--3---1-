// src/main/java/com/example/transcaribe/controller/ConductorViewController.java
package com.example.transcaribe.controller;

import com.example.transcaribe.dto.DetalleTurnoDto;
import com.example.transcaribe.dto.TurnoDto;
import com.example.transcaribe.entity.Conductor;
import com.example.transcaribe.services.ConductorService;
import com.example.transcaribe.services.TurnoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/conductor")
public class ConductorViewController {

    private final ConductorService conductorService;
    private final TurnoService turnoService;

    @Autowired
    public ConductorViewController(ConductorService conductorService,
                                   TurnoService turnoService) {
        this.conductorService = conductorService;
        this.turnoService     = turnoService;
    }

    // Página de login del conductor
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("conductor", new Conductor());
        return "conductor/loginConductor";
    }

    // Procesar login
    @PostMapping("/login")
    public String loginConductor(@RequestParam("correo") String correo,
                                 @RequestParam("password") String password,
                                 HttpSession session,
                                 Model model) {
        Conductor conductor = conductorService.buscarPorCorreo(correo);
        if (conductor != null && conductor.getPassword().equals(password)) {
            session.setAttribute("conductorLogueado", conductor);
            return "redirect:/conductor/index";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "conductor/loginConductor";
        }
    }

    // Página principal del conductor (dashboard)
    @GetMapping({"/", "/index"})
    public String indexConductor(HttpSession session, Model model) {
        Conductor conductor = (Conductor) session.getAttribute("conductorLogueado");
        if (conductor == null) {
            return "redirect:/conductor/login";
        }
        model.addAttribute("conductor", conductor);
        return "conductor/indexConductor";
    }

    // Logout del conductor (solo para su área)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/conductor/login";
    }

    // Horario semanal del conductor
    @GetMapping("/horario")
    public String mostrarHorario(HttpSession session, Model model) {
        Conductor conductor = (Conductor) session.getAttribute("conductorLogueado");
        if (conductor == null) {
            return "redirect:/conductor/login";
        }
        List<TurnoDto> turnos = turnoService.obtenerTurnosPorConductor(conductor.getId());
        model.addAttribute("conductor", conductor);
        model.addAttribute("turnos", turnos);
        return "conductor/horarioConductor";
    }

    // Detalle de un día específico
    @GetMapping("/detalle-dia/{dia}")
    public String mostrarDetalleDia(@PathVariable String dia,
                                    HttpSession session,
                                    Model model) {
        Conductor conductor = (Conductor) session.getAttribute("conductorLogueado");
        if (conductor == null) {
            return "redirect:/conductor/login";
        }
        DetalleTurnoDto detalle = turnoService.obtenerDetallePorDia(conductor.getId(), dia);
        if (detalle == null) {
            return "redirect:/conductor/horario";
        }
        model.addAttribute("conductor", conductor);
        model.addAttribute("dia", dia);
        model.addAttribute("detalle", detalle);
        return "conductor/detalleDia";
    }
}
