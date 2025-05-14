package com.example.transcaribe.services;

import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {


    @Autowired
    private UsuarioRepository usuarioRepository;


    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }


}
    