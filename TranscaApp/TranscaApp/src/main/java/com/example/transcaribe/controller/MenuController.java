package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Tarjeta;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.services.TarjetaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @Autowired
    private TarjetaService tarjetaService; // Implementa la lógica de negocio para las tarjetas

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorUsuario(usuario.getId());

        // Agregar la tarjeta al modelo
        model.addAttribute("usuario", usuario);
        model.addAttribute("tarjeta", tarjeta);

        return "perfil";
    }


    @GetMapping("/mapa")
    public String mapa(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        return "mapa";

    }

}