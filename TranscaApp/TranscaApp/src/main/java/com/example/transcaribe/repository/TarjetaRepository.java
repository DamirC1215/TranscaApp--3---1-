package com.example.transcaribe.repository;

import com.example.transcaribe.entity.Tarjeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TarjetaRepository extends MongoRepository<Tarjeta, String> {
    Optional<Tarjeta> findByUsuarioId(String usuarioId);

    Tarjeta findFirstByUsuarioIsNull();

    List<Tarjeta> findAllByUsuarioId(String usuarioId);

    @Query("SELECT t FROM Tarjeta t JOIN FETCH t.usuario")
    List<Tarjeta> findAllWithUsuarios();


}
