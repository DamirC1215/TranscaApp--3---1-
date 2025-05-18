package com.example.transcaribe.repository;

import com.example.transcaribe.entity.RegistroRuta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRutaRepository extends MongoRepository<RegistroRuta, String> {
    // Métodos personalizados si necesitas
}
