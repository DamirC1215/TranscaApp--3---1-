package com.example.transcaribe.repository;

import com.example.transcaribe.entity.Administrador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, String> {
    Optional<Administrador> findByCorreo(String correo);

    Optional<Administrador> findByCorreoAndPassword(String correo, String password);
}
