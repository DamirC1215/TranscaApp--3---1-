package com.example.transcaribe.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import com.example.transcaribe.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>
{
    Optional<Usuario> findByEmail(String email);
}
