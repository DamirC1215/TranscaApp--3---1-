package com.example.transcaribe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.transcaribe.entity.Conductor;
import com.example.transcaribe.repository.ConductorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    // 👉 Guarda un nuevo conductor o actualiza uno existente
    public void guardar(Conductor conductor) {
        conductorRepository.save(conductor);
    }

    // 👉 Método adicional para guardar conductor (para compatibilidad con el controlador)
    public void guardarConductor(Conductor conductor) {
        guardar(conductor);
    }

    // 👉 Verifica si existe un conductor por correo
    public boolean existePorCorreo(String correoElectronico) {
        return conductorRepository.findByCorreoElectronico(correoElectronico) != null;
    }

    // 👉 Busca un conductor por ID
    public Optional<Conductor> buscarPorId(String id) {
        return conductorRepository.findById(id);
    }

    // 👉 Busca un conductor por correo
    public Conductor buscarPorCorreo(String correoElectronico) {
        return conductorRepository.findByCorreoElectronico(correoElectronico).orElse(null);
    }
    // 👉 Obtiene todos los conductores
    public List<Conductor> obtenerTodos() {
        return conductorRepository.findAll();
    }

    // 👉 Elimina un conductor por ID
    public void eliminarPorId(String id) {
        conductorRepository.deleteById(id);
    }

    // 👉 Método adicional para eliminar conductor (para compatibilidad con el controlador)
    public void eliminarConductor(String id) {
        eliminarPorId(id);
    }
}
