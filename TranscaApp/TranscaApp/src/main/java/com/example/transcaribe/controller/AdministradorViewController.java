package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Administrador;
import com.example.transcaribe.entity.Tarjeta;
import com.example.transcaribe.entity.Trayecto;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.services.AdministradorService;
import com.example.transcaribe.services.TarjetaService;
import com.example.transcaribe.services.TrayectoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdministradorViewController {

    private final AdministradorService administradorService;
    private final TarjetaService tarjetaService;
    private final TrayectoService trayectoService;

    @Autowired
    public AdministradorViewController(AdministradorService administradorService,
                                       TarjetaService tarjetaService,
                                       TrayectoService trayectoService) {
        this.administradorService = administradorService;
        this.tarjetaService       = tarjetaService;
        this.trayectoService      = trayectoService;
    }

    @GetMapping("/indexAdm")
    public String mostrarIndex(HttpSession session, Model model) {
        Administrador admin = (Administrador) session.getAttribute("administradorLogueado");
        if (admin == null) {
            return "redirect:/admin/loginAdm";
        }
        model.addAttribute("administrador", admin);
        return "admin/indexAdm";
    }

    @GetMapping("/usuario")
    public String mostrarUsuario(HttpSession session, Model model) {
        Administrador admin = (Administrador) session.getAttribute("administradorLogueado");
        if (admin == null) {
            return "redirect:/admin/loginAdm";
        }
        List<Usuario> usuarios = administradorService.obtenerUsuarios();
        model.addAttribute("administrador", admin);
        model.addAttribute("usuarios", usuarios);
        return "admin/usuario";
    }

    @GetMapping("/creacion")
    public String mostrarCreacion(HttpSession session, Model model) {
        Administrador admin = (Administrador) session.getAttribute("administradorLogueado");
        if (admin == null) {
            return "redirect:/admin/loginAdm";
        }
        List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetasSinUsuario();
        model.addAttribute("administrador", admin);
        model.addAttribute("tarjetas", tarjetas);
        model.addAttribute("nuevaTarjeta", new Tarjeta());
        return "admin/creacion";
    }

    @PostMapping("/crear-tarjeta")
    public String crearTarjeta(@ModelAttribute("nuevaTarjeta") Tarjeta tarjeta) {
        tarjetaService.crearTarjeta(tarjeta);
        return "redirect:/admin/creacion";
    }

    @GetMapping("/visualizar")
    public String mostrarVisualizar(HttpSession session, Model model) {
        Administrador admin = (Administrador) session.getAttribute("administradorLogueado");
        if (admin == null) {
            return "redirect:/admin/loginAdm";
        }
        model.addAttribute("administrador", admin);
        model.addAttribute("tarjetas", tarjetaService.obtenerTodasLasTarjetasConUsuarios());
        return "admin/visualizar";
    }

    @GetMapping("/troncales")
    public String mostrarTrayectos(HttpSession session, Model model) {
        Administrador admin = (Administrador) session.getAttribute("administradorLogueado");
        if (admin == null) {
            return "redirect:/admin/loginAdm";
        }
        List<Trayecto> trayectos = trayectoService.obtenerTodosLosTrayectos();
        model.addAttribute("administrador", admin);
        model.addAttribute("trayectos", trayectos);
        return "admin/troncales";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstadoTrayecto(@PathVariable String id) {
        // Invocamos el método que definiste en TrayectoService:
        trayectoService.cambiarEstado(id);
        return "redirect:/admin/troncales";
    }

    @GetMapping("/loginAdm")
    public String irLogin(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "admin/loginAdm";
    }

    @PostMapping("/loginAdm")
    public String login(@RequestParam String correo,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Administrador admin = administradorService.buscarPorCorreo(correo);
        if (admin != null && admin.getPassword().equals(password)) {
            session.setAttribute("administradorLogueado", admin);
            return "redirect:/admin/indexAdm";
        }
        model.addAttribute("error", "Correo o contraseña incorrectos");
        model.addAttribute("administrador", new Administrador());
        return "admin/loginAdm";
    }
}
