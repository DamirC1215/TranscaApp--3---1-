package com.example.transcaribe.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.example.transcaribe.entity.Estacion;
import com.example.transcaribe.entity.Trayecto;

@Repository
public interface TrayectoRepository extends MongoRepository<Trayecto, String>
{
    List<Trayecto> findByEstacionesIn(List<Estacion> estaciones);

}

