package com.example.transcaribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transcaribe.entity.Conductor;
import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    Optional<Conductor> findByCorreoElectronico(String correoElectronico);
}
