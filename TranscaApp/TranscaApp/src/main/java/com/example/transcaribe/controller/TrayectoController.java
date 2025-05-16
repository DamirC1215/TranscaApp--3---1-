package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Trayecto;
import com.example.transcaribe.services.TrayectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para exponer rutas y estadísticas de uso.
 */
@RestController
@RequestMapping("/api/trayectos")
public class TrayectoController {

    private final TrayectoService trayectoService;

    @Autowired
    public TrayectoController(TrayectoService trayectoService) {
        this.trayectoService = trayectoService;
    }

    /**
     * Obtiene el mejor trayecto entre dos estaciones.
     */
    @GetMapping("/mejor-ruta")
    public ResponseEntity<Trayecto> obtenerMejorRuta(
            @RequestParam String estacionInicio,
            @RequestParam String estacionDestino) {

        Trayecto mejorTrayecto = trayectoService.encontrarMejorTrayecto(estacionInicio, estacionDestino);
        if (mejorTrayecto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mejorTrayecto);
    }

    /**
     * Incrementa el contador de uso de un trayecto.
     * Llamar desde el front-end (o tu Thymeleaf form) tras
     * que el usuario confirme la ruta.
     */
    @PostMapping("/confirmar")
    public ResponseEntity<Void> confirmarRuta(@RequestParam("rutaId") String rutaId) {
        trayectoService.incrementarUso(rutaId);
        return ResponseEntity.ok().build();
    }
}
