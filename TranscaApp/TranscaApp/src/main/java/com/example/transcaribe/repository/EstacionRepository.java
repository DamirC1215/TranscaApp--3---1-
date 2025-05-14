package com.example.transcaribe.repository;


import com.example.transcaribe.entity.Estacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends MongoRepository<Estacion, String> {
    Estacion findByNombre(String nombre);
}