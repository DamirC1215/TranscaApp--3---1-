package com.example.transcaribe.repository;

import com.example.transcaribe.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    Horario findByConductorIdAndDia(Long conductorId, String dia);
    List<Horario> findByConductorId(Long conductorId);
}
