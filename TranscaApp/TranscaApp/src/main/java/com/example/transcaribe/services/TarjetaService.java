package com.example.transcaribe.services;

import com.example.transcaribe.entity.Recarga;
import com.example.transcaribe.entity.Tarjeta;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    @Autowired
    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public void recargarTarjeta(String usuarioId, Double monto) {
        Tarjeta tarjeta = tarjetaRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Tarjeta no encontrada"));

        tarjeta.setSaldo(tarjeta.getSaldo() + monto);
        tarjetaRepository.save(tarjeta);
    }


    public List<Recarga> obtenerHistorialRecargas(String tarjetaId) {
        Tarjeta tarjeta = tarjetaRepository.findById(tarjetaId)
                .orElseThrow(() -> new IllegalArgumentException("Tarjeta no encontrada"));
        return tarjeta.getRecargas();
    }

    public Tarjeta crearTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }


    public List<Tarjeta> obtenerTarjetasSinUsuario() {
        return tarjetaRepository.findAll();
    }

    public List<Tarjeta> obtenerTarjetasUsuario(Usuario usuario) {
        return tarjetaRepository.findAllByUsuarioId(usuario.getId());
    }
    

    // Asignar una tarjeta al azar al usuario
    public Tarjeta asignarTarjetaExistente(Usuario usuario) {
        // Buscar una tarjeta no asignada (usuario es null)
        List<Tarjeta> tarjetasUsuarios = obtenerTarjetasUsuario(usuario);
        Tarjeta tn = new Tarjeta();

        if (tarjetasUsuarios.isEmpty()) {
            tn.setUsuario(usuario);
            tn.setSaldo(0.0);
            tn.setNumeroDeBarras(UUID.randomUUID().toString());
            tn = crearTarjeta(tn);
        } else {
            tn = tarjetasUsuarios.get(0);
        }
        {
            tn = tarjetasUsuarios.get(0);
        }
        {
            tn = tarjetasUsuarios.get(0);
        }
        {
            tn = tarjetasUsuarios.get(0);
        }

        return tn;
    }

    // Obtener la tarjeta asociada a un usuario
    public Tarjeta obtenerTarjetaPorUsuario(String usuarioId) {
        return tarjetaRepository.findByUsuarioId(usuarioId).orElse(null);
    }

    public List<Tarjeta> obtenerTodasLasTarjetasConUsuarios() {
        return tarjetaRepository.findAllWithUsuarios();
    }

    public Optional<Tarjeta> obtenerTarjetaPorId(String tarjetaId) {
        return tarjetaRepository.findById(tarjetaId);
    }

}