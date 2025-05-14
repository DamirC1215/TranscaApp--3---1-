package com.example.transcaribe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.transcaribe.entity.Administrador;
import com.example.transcaribe.entity.Trayecto;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.repository.AdministradorRepository;
import com.example.transcaribe.repository.TrayectoRepository;
import com.example.transcaribe.repository.UsuarioRepository;


@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final UsuarioRepository usuarioRepository;
    private final TrayectoRepository trayectoRepository;

    
    public AdministradorService(UsuarioRepository usuarioRepository, TrayectoRepository trayectoRepository, AdministradorRepository administradorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.trayectoRepository = trayectoRepository;
        this.administradorRepository = administradorRepository;
    }

    public Administrador buscarPorCorreo(String correo) {
        return administradorRepository.findByCorreo(correo).orElse(null);
    }

    
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    
    public void cambiarEstadoTrayecto(String trayectoId, boolean Estado) {
        Trayecto trayecto = trayectoRepository.findById(trayectoId)
            .orElseThrow(() -> new RuntimeException("Trayecto no encontrado"));
        trayecto.setEstado(Estado);
        trayectoRepository.save(trayecto);
    }

    
}

