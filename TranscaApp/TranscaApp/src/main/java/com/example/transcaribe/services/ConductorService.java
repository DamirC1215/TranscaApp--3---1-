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

    /**
     * Crea un nuevo conductor (lanza IllegalArgumentException si el correo ya existe).
     */
    public Conductor crear(Conductor conductor) {
        if (repo.existsByCorreoElectronico(conductor.getCorreoElectronico())) {
            throw new IllegalArgumentException(
                "Ya existe un conductor con correo " + conductor.getCorreoElectronico()
            );
        }
        return repo.save(conductor);
    }

    /**
     * Actualiza un conductor existente. Lanza IllegalArgumentException si no existe.
     */
    public Conductor actualizar(String id, Conductor datos) {
        Optional<Conductor> opt = repo.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("Conductor no encontrado con id: " + id);
        }
        Conductor existente = opt.get();
        existente.setNombre(datos.getNombre());
        existente.setCorreoElectronico(datos.getCorreoElectronico());
        existente.setPassword(datos.getPassword());
        existente.setLicencia(datos.getLicencia());
        // no cambiamos el campo 'tipo'
        return repo.save(existente);
    }

    /**
     * Obtiene la lista de todos los conductores.
     */
    public List<Conductor> obtenerTodos() {
        return repo.findAll();
    }

    /**
     * Obtiene un conductor por su ID, o lanza IllegalArgumentException si no existe.
     */
    public Conductor obtenerPorId(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado con id: " + id));
    }

    /**
     * Elimina un conductor por ID (lanza IllegalArgumentException si no existe).
     */
    public void eliminarPorId(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Conductor no encontrado con id: " + id);
        }
        repo.deleteById(id);
    }

    /**
     * Verifica si ya existe un conductor con ese correo.
     */
    public boolean existsByCorreoElectronico(String correoElectronico) {
        return repo.existsByCorreoElectronico(correoElectronico);
    }
}
