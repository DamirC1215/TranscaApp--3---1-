package com.example.transcaribe.controller;

import com.example.transcaribe.services.TrayectoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RutaController {

    private final TrayectoService trayectoService;

    public RutaController(TrayectoService trayectoService) {
        this.trayectoService = trayectoService;
    }

    /**
     * Recibe la confirmación de ruta desde el formulario,
     * incrementa el contador de uso y redirige al índice.
     */
    @PostMapping("/ruta/confirmar")
    public String confirmarRuta(
            @RequestParam("rutaId") String rutaId,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 1) Incrementar uso en la base de datos
        trayectoService.incrementarUso(rutaId);

        // 2) Redirigir al index ("/index") sin cerrar sesión
        return "redirect:/index";
    }
}
