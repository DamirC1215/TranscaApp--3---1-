package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Conductor;
import com.example.transcaribe.services.ConductorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/conductor")
public class ConductorViewController {

    @Autowired
    private ConductorService conductorService;

    // Login del conductor
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("conductor", new Conductor());
        return "conductor/loginConductor";
    }

    @PostMapping("/login")
    public String loginConductor(@RequestParam("correo") String correo,
                                 @RequestParam("password") String password,
                                 HttpSession session, Model model) {
        Conductor conductor = conductorService.buscarPorCorreo(correo);
        if (conductor != null && conductor.getPassword().equals(password)) {
            session.setAttribute("conductorLogueado", conductor);
            return "redirect:/conductor/horario";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "conductor/loginConductor";
        }
    }

    //  Logout del conductor
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/conductor/login";
    }

    //  Horario semanal del conductor
    @GetMapping("/horario")
    public String mostrarHorario(HttpSession session, Model model) {
        Conductor conductor = (Conductor) session.getAttribute("conductorLogueado");
        if (conductor == null) {
            return "redirect:/conductor/login";
        }
        model.addAttribute("conductor", conductor);
        return "conductor/horarioConductor"; // Debe estar en templates/conductor/horarioConductor.html
    }

    //  Detalle de un día específico
    @GetMapping("/detalle-dia/{dia}")
    public String mostrarDetalleDia(@PathVariable("dia") String dia, HttpSession session, Model model) {
        Conductor conductor = (Conductor) session.getAttribute("conductorLogueado");
        if (conductor == null) {
            return "redirect:/conductor/login";
        }
        model.addAttribute("conductor", conductor);
        model.addAttribute("dia", dia);
        return "conductor/detalleDia"; // Debe estar en templates/conductor/detalleDia.html
    }
}
