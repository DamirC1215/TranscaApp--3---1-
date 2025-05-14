package com.example.transcaribe.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.transcaribe.entity.Administrador;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, String>
{
    Optional<Administrador> findByCorreo(String correo);

}
