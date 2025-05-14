package com.example.transcaribe.services;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.transcaribe.entity.Estacion;
import com.example.transcaribe.entity.Trayecto;
import com.example.transcaribe.repository.EstacionRepository;
import com.example.transcaribe.repository.TrayectoRepository;


@Service
public class TrayectoService {

     @Autowired
    private TrayectoRepository trayectoRepository;

    @Autowired
    private EstacionRepository estacionRepository;

    public Trayecto encontrarMejorTrayecto(String estacionInicio, String estacionDestino) {
        // Obtén las estaciones desde la base de datos
        Estacion inicio = estacionRepository.findByNombre(estacionInicio);
        Estacion destino = estacionRepository.findByNombre(estacionDestino);

        if (inicio == null || destino == null) {
            throw new IllegalArgumentException("Estaciones no válidas");
        }

        // Busca los trayectos que incluyan ambas estaciones
        List<Trayecto> trayectos = trayectoRepository.findByEstacionesIn(List.of(inicio, destino));

        // Filtra los trayectos para que el inicio esté antes que el destino
        return trayectos.stream()
                .filter(t -> t.getEstaciones().indexOf(inicio) < t.getEstaciones().indexOf(destino))
                .min(Comparator.comparingInt(Trayecto::getCantidadEstaciones))
                .orElse(null); // Devuelve el trayecto con menos estaciones
    }


    public List<Trayecto> obtenerTodosLosTrayectos() {
        return trayectoRepository.findAll();
    }

    public void cambiarEstado(String id) {
        Trayecto trayecto = trayectoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trayecto no encontrado con ID: " + id));
        trayecto.setEstado(!trayecto.getEstado()); // Cambia el estado
        trayectoRepository.save(trayecto);
    }
    
}

