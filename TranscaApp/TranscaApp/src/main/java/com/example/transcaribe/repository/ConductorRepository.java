package com.example.transcaribe.repository;

import com.example.transcaribe.entity.Conductor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConductorRepository extends MongoRepository<Conductor, String> {
    Optional<Conductor> findByCorreoElectronico(String correoElectronico);
}
