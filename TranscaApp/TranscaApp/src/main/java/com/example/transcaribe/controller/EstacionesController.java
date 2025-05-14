package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Estacion;
import com.example.transcaribe.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EstacionesController {

    @Autowired
    private EstacionRepository estacionRepository;


    // Endpoint para obtener la lista de estaciones
    @GetMapping("/estaciones")
    public ResponseEntity<List<Estacion>> obtenerEstaciones() {
        List<Estacion> estaciones = estacionRepository.findAll();
        return ResponseEntity.ok(estaciones);
    }

    // Endpoint para obtener la lista de trayectos
}