package com.example.transcaribe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.transcaribe.entity.Conductor;
import java.util.Optional;

public interface ConductorRepository extends MongoRepository<Conductor, String>
{
    Optional<Conductor> findByCorreoElectronico(String correoElectronico);
}
