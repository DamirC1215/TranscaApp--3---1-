// src/main/java/com/example/transcaribe/services/ConductorService.java
package com.example.transcaribe.services;

import com.example.transcaribe.entity.Conductor;
import com.example.transcaribe.repository.ConductorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {

    private final ConductorRepository repo;

    public ConductorService(ConductorRepository repo) {
        this.repo = repo;
    }

    public Conductor crear(Conductor conductor) {
        if (repo.existsByCorreoElectronico(conductor.getCorreoElectronico())) {
            throw new IllegalArgumentException(
                    "Ya existe un conductor con correo " + conductor.getCorreoElectronico()
            );
        }
        return repo.save(conductor);
    }

    public Conductor actualizar(String id, Conductor datos) {
        Conductor existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setCorreoElectronico(datos.getCorreoElectronico());
        existente.setPassword(datos.getPassword());
        existente.setLicencia(datos.getLicencia());
        return repo.save(existente);
    }

    public List<Conductor> obtenerTodos() {
        return repo.findAll();
    }

    // Este es el método que usas en el controlador:
    public Conductor buscarPorId(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado con id: " + id));
    }

    // Este es el método que usas en el controlador:
    public void eliminar(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Conductor no encontrado con id: " + id);
        }
        repo.deleteById(id);
    }

    public boolean existsByCorreoElectronico(String correoElectronico) {
        return repo.existsByCorreoElectronico(correoElectronico);
    }

    public Conductor buscarPorCorreo(String correoElectronico) {
        return repo.findByCorreoElectronico(correoElectronico).orElse(null);
    }
}
