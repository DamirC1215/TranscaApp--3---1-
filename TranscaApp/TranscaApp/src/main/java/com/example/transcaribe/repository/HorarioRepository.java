// src/main/java/com/example/transcaribe/repository/HorarioRepository.java
package com.example.transcaribe.repository;

import com.example.transcaribe.entity.Horario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HorarioRepository extends MongoRepository<Horario, String> {
    // Spring Data genera la consulta automáticamente
    List<Horario> findAllByConductorId(String conductorId);
    // Y si quisieras buscar por conductor y día:
    Horario findByConductorIdAndDia(String conductorId, String dia);
}
