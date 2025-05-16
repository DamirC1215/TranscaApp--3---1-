package com.example.transcaribe.services;

import com.example.transcaribe.entity.Administrador;
import com.example.transcaribe.entity.Trayecto;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.repository.AdministradorRepository;
import com.example.transcaribe.repository.TrayectoRepository;
import com.example.transcaribe.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final UsuarioRepository usuarioRepository;
    private final TrayectoRepository trayectoRepository;

    public AdministradorService(UsuarioRepository usuarioRepository,
                                TrayectoRepository trayectoRepository,
                                AdministradorRepository administradorRepository) {
        this.usuarioRepository       = usuarioRepository;
        this.trayectoRepository      = trayectoRepository;
        this.administradorRepository = administradorRepository;
    }

    public Administrador buscarPorCorreo(String correo) {
        return administradorRepository.findByCorreo(correo).orElse(null);
    }

    public Boolean buscarPorCorreoYContraseña(String correo, String password) {
        return administradorRepository.findByCorreoAndPassword(correo, password).isPresent();
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Cambia el estado del trayecto con id dado.
     * Si estaba 1 lo pone a 0; en caso contrario (0 o null) lo pone a 1.
     */
    public void cambiarEstadoTrayecto(String trayectoId) {
        Trayecto trayecto = trayectoRepository.findById(trayectoId)
                .orElseThrow(() -> new RuntimeException("Trayecto no encontrado con ID: " + trayectoId));

        Integer estadoActual = trayecto.getEstado(); // puede ser null, 0 o 1
        Integer nuevoEstado  = (estadoActual != null && estadoActual == 1) ? 0 : 1;
        trayecto.setEstado(nuevoEstado);

        trayectoRepository.save(trayecto);
    }
}
