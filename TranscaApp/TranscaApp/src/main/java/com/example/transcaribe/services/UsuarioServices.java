package com.example.transcaribe.services;

import com.example.transcaribe.entity.Tarjeta;
import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarjetaService tarjetaService;  // Inyectamos el servicio de Tarjeta

    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    // Método corregido para descontar saldo en la tarjeta del usuario
    @Transactional
    public boolean descontarSaldo(String email, double monto) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario == null) return false;

        // Obtener tarjeta asociada al usuario
        Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorUsuario(usuario.getId());
        if (tarjeta == null) {
            return false; // Usuario sin tarjeta asociada
        }

        Double saldoActual = tarjeta.getSaldo();
        if (saldoActual == null) saldoActual = 0.0;

        if (saldoActual < monto) {
            return false; // saldo insuficiente
        }

        // Descontar monto
        tarjeta.setSaldo(saldoActual - monto);

        // Guardar la tarjeta actualizada (reusa método de creación/actualización)
        tarjetaService.crearTarjeta(tarjeta);

        return true;
    }
}
