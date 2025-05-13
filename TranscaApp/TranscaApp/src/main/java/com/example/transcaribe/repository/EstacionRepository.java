package com.example.transcaribe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transcaribe.entity.Estacion;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
    Estacion findByNombre(String nombre);
}