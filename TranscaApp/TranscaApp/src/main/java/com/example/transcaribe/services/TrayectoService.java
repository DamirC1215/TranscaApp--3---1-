package com.example.transcaribe.services;

import com.example.transcaribe.entity.Trayecto;
import com.example.transcaribe.repository.EstacionRepository;
import com.example.transcaribe.repository.TrayectoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TrayectoService {

    private final TrayectoRepository trayectoRepository;
    private final EstacionRepository estacionRepository;

    public TrayectoService(TrayectoRepository trayectoRepository,
                           EstacionRepository estacionRepository) {
        this.trayectoRepository   = trayectoRepository;
        this.estacionRepository   = estacionRepository;
    }

    /**
     * Devuelve todos los trayectos.
     */
    public List<Trayecto> obtenerTodosLosTrayectos() {
        return trayectoRepository.findAll();
    }

    /**
     * Alterna el estado (0/1) de un trayecto dado su id.
     */
    public void cambiarEstado(String id) {
        Trayecto trayecto = trayectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trayecto no encontrado con ID: " + id));

        Integer estadoActual = trayecto.getEstado();         // 0 ó 1
        Integer nuevoEstado  = (estadoActual != null && estadoActual == 1) ? 0 : 1;
        trayecto.setEstado(nuevoEstado);

        trayectoRepository.save(trayecto);
    }

    /**
     * Incrementa el contador de uso de un trayecto si existe.
     * Si no existe, no hace nada.
     */
    public void incrementarUso(String trayectoId) {
        trayectoRepository.findById(trayectoId)
                .ifPresent(trayecto -> {
                    Integer usoActual = trayecto.getUso() == null ? 0 : trayecto.getUso();
                    trayecto.setUso(usoActual + 1);
                    trayectoRepository.save(trayecto);
                });
    }

    /**
     * Encuentra el mejor trayecto entre dos estaciones, basado en duración estimada.
     */
    public Trayecto encontrarMejorTrayecto(String estacionInicioId, String estacionDestinoId) {
        return trayectoRepository
                .findAll()
                .stream()
                .filter(t -> t.getEstaciones().stream()
                        .anyMatch(e -> e.getId().equals(estacionInicioId)))
                .filter(t -> t.getEstaciones().stream()
                        .anyMatch(e -> e.getId().equals(estacionDestinoId)))
                .min(Comparator.comparing(Trayecto::getDuracionEstimada))
                .orElse(null);
    }
}
