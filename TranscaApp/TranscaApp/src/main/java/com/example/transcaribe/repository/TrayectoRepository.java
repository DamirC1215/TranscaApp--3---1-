package com.example.transcaribe.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transcaribe.entity.Estacion;
import com.example.transcaribe.entity.Trayecto;

@Repository
public interface TrayectoRepository extends JpaRepository<Trayecto, Long> {
    List<Trayecto> findByEstacionesIn(List<Estacion> estaciones);

}

