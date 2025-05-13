package com.example.transcaribe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.transcaribe.entity.Recarga;
import com.example.transcaribe.entity.Tarjeta;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.services.TarjetaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TarjetaController {

    private final TarjetaService tarjetaService;

    @Autowired
    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @GetMapping("/recarga")
    public String mostrarFormularioRecarga(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorUsuario(usuario.getId());
        if (tarjeta == null) {
            model.addAttribute("errorMessage", "No se encontró una tarjeta asociada al usuario.");
            return "perfil"; // Cambia a otra vista si es necesario
        }

        model.addAttribute("tarjeta", tarjeta);
        return "nequi";
    }

    @PostMapping("/recargar")
    public String procesarRecarga(HttpSession session, @RequestParam Double monto, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Usuario no logueado.");
            return "redirect:/login";
        }

        try {
            tarjetaService.recargarTarjeta(usuario.getId(), monto);
            redirectAttributes.addFlashAttribute("successMessage", "Recarga exitosa.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al procesar la recarga: " + e.getMessage());
        }

        return "redirect:/perfil";
    }


    // Endpoint para obtener el historial de recargas de una tarjeta
    @GetMapping("/{tarjetaId}/historial")
    public ResponseEntity<List<Recarga>> obtenerHistorialRecargas(@PathVariable Long tarjetaId) {
        List<Recarga> historialRecargas = tarjetaService.obtenerHistorialRecargas(tarjetaId);
        return ResponseEntity.ok(historialRecargas);
    }



    
}
