package com.example.transcaribe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.transcaribe.entity.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    Optional<Tarjeta> findByUsuarioId(Long usuarioId);

    Tarjeta findFirstByUsuarioIsNull();

    List<Tarjeta> findAllByUsuarioId(Long usuarioId);

    @Query("SELECT t FROM Tarjeta t JOIN FETCH t.usuario")
    List<Tarjeta> findAllWithUsuarios();

    

}
