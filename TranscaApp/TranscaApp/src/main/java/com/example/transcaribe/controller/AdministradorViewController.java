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
    public AdministradorViewController(AdministradorService administradorService, TarjetaService tarjetaService, TrayectoService trayectoService) {
        this.administradorService = administradorService;
        this.tarjetaService = tarjetaService;
        this.trayectoService = trayectoService;
    }

    @GetMapping("/indexAdm")
    public String mostrarIndex(HttpSession session, Model model) {
        Administrador administrador = (Administrador) session.getAttribute("administradorLogueado");
        if (administrador == null) {
            return "redirect:/admin/loginAdm";
        }
        model.addAttribute("administrador", administrador);
        return "indexAdm";
    }

    @GetMapping("/usuario")
    public String mostrarUsuario(HttpSession session, Model model) {
        Administrador administrador = (Administrador) session.getAttribute("administradorLogueado");
        if (administrador == null) {
            return "redirect:/admin/loginAdm";
        }
        List<Usuario> usuarios = administradorService.obtenerUsuarios();
        model.addAttribute("administrador", administrador);
        model.addAttribute("usuarios", usuarios);
        return "usuario";
    }

    @GetMapping("/creacion")
    public String mostrarCreacion(HttpSession session, Model model) {
        Administrador administrador = (Administrador) session.getAttribute("administradorLogueado");
        if (administrador == null) {
            return "redirect:/admin/loginAdm";
        }
        List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetasSinUsuario();
        model.addAttribute("administrador", administrador);
        model.addAttribute("tarjetas", tarjetas); // Añadir lista de tarjetas al modelo
        model.addAttribute("nuevaTarjeta", new Tarjeta()); // Añadir un objeto tarjeta vacío para el formulario
        return "creacion";
    }

    @PostMapping("/crear-tarjeta")
    public String crearTarjeta(@ModelAttribute("nuevaTarjeta") Tarjeta tarjeta) {
        // Crear la tarjeta en la base de datos
        tarjetaService.crearTarjeta(tarjeta);
        return "redirect:/admin/creacion"; // Redirige de nuevo a la vista de creación
    }

    @GetMapping("/visualizar")
    public String mostrarVisualizar(HttpSession session, Model model) {
        Administrador administrador = (Administrador) session.getAttribute("administradorLogueado");
        if (administrador == null) {
            return "redirect:/admin/loginAdm";
        }
        List<Tarjeta> tarjetas = tarjetaService.obtenerTodasLasTarjetasConUsuarios();
        model.addAttribute("administrador", administrador);
        model.addAttribute("tarjetas", tarjetas);
        return "visualizar";
    }


    @GetMapping("/troncales")
    public String mostrarTrayectos(HttpSession session, Model model) {
        Administrador administrador = (Administrador) session.getAttribute("administradorLogueado");
        if (administrador == null) {
            return "redirect:/admin/loginAdm";
        }
        List<Trayecto> trayectos = trayectoService.obtenerTodosLosTrayectos();
        model.addAttribute("administrador", administrador);
        model.addAttribute("trayectos", trayectos);
        return "troncales"; // Plantilla Thymeleaf para mostrar los trayectos
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstadoTrayecto(@PathVariable String id) {
        trayectoService.cambiarEstado(id);
        return "redirect:/admin/troncales"; // Redirige para actualizar la tabla
    }


    @GetMapping("/loginAdm")
    public String irLogin(Model model) {
        Administrador administrador = new Administrador();
        model.addAttribute("administrador", administrador);
        return "loginAdm";
    }

    @PostMapping("/loginAdm")
    public String login(@RequestParam("correo") String correo, @RequestParam("password") String password,
                        HttpSession session, Model model) {
        Administrador administrador = administradorService.buscarPorCorreo(correo);
        if (administrador != null && administrador.getPassword().equals(password)) {
            session.setAttribute("administradorLogueado", administrador);
            return "redirect:/admin/indexAdm";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            model.addAttribute("administrador", new Administrador());
            return "loginAdm";
        }
    }


}


