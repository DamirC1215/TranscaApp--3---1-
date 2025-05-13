package com.example.transcaribe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.transcaribe.entity.Administrador;
import com.example.transcaribe.entity.Conductor;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.repository.AdministradorRepository;
import com.example.transcaribe.repository.ConductorRepository;
import com.example.transcaribe.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class AutenticacionService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    public Object autenticarUsuario(String correo, String password) {
        // Buscar en la tabla de administradores
        Optional<Administrador> adminOpt = administradorRepository.findByCorreo(correo);
        if (adminOpt.isPresent() && adminOpt.get().getPassword().equals(password)) {
            return adminOpt.get(); // Retorna el objeto Administrador
        }

        // Buscar en la tabla de conductores
        Optional<Conductor> conductorOpt = conductorRepository.findByCorreoElectronico(correo);
        if (conductorOpt.isPresent() && conductorOpt.get().getPassword().equals(password)) {
            return conductorOpt.get(); // Retorna el objeto Conductor
        }

        // Buscar en la tabla de usuarios
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(correo);
        if (usuarioOpt.isPresent() && usuarioOpt.get().getContraseña().equals(password)) {
            return usuarioOpt.get(); // Retorna el objeto Usuario
        }

        return null; // Si no encuentra coincidencias, retorna null
    }
}
