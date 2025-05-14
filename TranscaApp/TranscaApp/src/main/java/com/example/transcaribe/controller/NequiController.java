package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Tarjeta;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.services.TarjetaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NequiController {
    @Autowired
    private TarjetaService tarjetaService;

  /*   @GetMapping("/nequi")
public String nequi(HttpSession session, Model model) {
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null) {
        return "redirect:/login";
    }

    Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorUsuario(usuario.getId());
    
    // Agregar la tarjeta al modelo
    model.addAttribute("usuario", usuario);
    model.addAttribute("tarjeta", tarjeta); 
    
    return "nequi";  
}  */


    @GetMapping("/comprar-tarjeta")
    public String mostrarVistaCompraTarjeta(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorUsuario(usuario.getId());
        if (tarjeta != null) {
            return "redirect:/perfil";
        }
        return "comprar-tarjeta";
    }

/* @PostMapping("/comprar-tarjeta")
public String comprarTarjeta(HttpSession session, Model model) {
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null) {
        return "redirect:/login";
    }
    try {
        
        Tarjeta tarjetaAsignada = tarjetaService.asignarTarjetaExistente(usuario);

        
        usuario.getTarjetas().add(tarjetaAsignada);
        session.setAttribute("usuarioLogueado", usuario);

        model.addAttribute("tarjeta", tarjetaAsignada);
        model.addAttribute("successMessage", "¡Tarjeta asignada exitosamente!");

    } catch (IllegalStateException e) {
        model.addAttribute("errorMessage", "Ocurrió un error al confirmar la compra de la tarjeta.");
    }


    return "perfil";
}

} */

    @PostMapping("/confirmar-compra-tarjeta")
    public String procesarCompraTarjeta(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/login";
        }

        Tarjeta tarjetaAsignada = tarjetaService.asignarTarjetaExistente(usuario);
        usuario.getTarjetas().add(tarjetaAsignada);
        session.setAttribute("usuarioLogueado", usuario);

        Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorUsuario(usuario.getId());

        model.addAttribute("usuario", usuario);
        model.addAttribute("tarjeta", tarjeta);
        model.addAttribute("successMessage", "¡Compra de tarjeta confirmada y exitosa!");

        return "comprar-tarjeta"; // Mantener al usuario en la vista de compra
    }
}



 