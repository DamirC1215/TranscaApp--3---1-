package com.example.transcaribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.transcaribe.entity.Trayecto;
import com.example.transcaribe.repository.TrayectoRepository;
import com.example.transcaribe.services.TrayectoService;



@RestController
@RequestMapping("/api/trayectos")
public class TrayectoController {

    @Autowired
    private TrayectoService trayectoService;

    @Autowired
    private TrayectoRepository trayectoRepository;

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

}
