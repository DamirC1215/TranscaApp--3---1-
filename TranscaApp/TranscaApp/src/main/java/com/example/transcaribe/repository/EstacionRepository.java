package com.example.transcaribe.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.example.transcaribe.entity.Estacion;

@Repository
public interface EstacionRepository extends MongoRepository<Estacion, String>
{
    Estacion findByNombre(String nombre);
}