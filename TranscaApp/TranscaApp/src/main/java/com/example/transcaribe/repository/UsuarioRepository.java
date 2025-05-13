package com.example.transcaribe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.transcaribe.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
