// src/main/java/com/example/transcaribe/repository/VehiculoRepository.java
package com.example.transcaribe.repository;

import com.example.transcaribe.entity.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
    // no necesitas métodos extra por ahora
    Optional<Vehiculo> findByIdTranscar(Integer idTranscar);
}
