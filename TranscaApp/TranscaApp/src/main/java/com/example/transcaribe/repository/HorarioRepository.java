package com.example.transcaribe.repository;

import com.example.transcaribe.entity.Horario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HorarioRepository extends MongoRepository<Horario, String> {
    Horario findByConductorIdAndDia(String conductorId, String dia);

    List<Horario> findByConductorId(String conductorId);
}
