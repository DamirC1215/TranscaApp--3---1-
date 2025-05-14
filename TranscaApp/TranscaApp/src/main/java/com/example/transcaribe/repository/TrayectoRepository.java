package com.example.transcaribe.repository;


import com.example.transcaribe.entity.Estacion;
import com.example.transcaribe.entity.Trayecto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrayectoRepository extends MongoRepository<Trayecto, String> {
    List<Trayecto> findByEstacionesIn(List<Estacion> estaciones);

}

