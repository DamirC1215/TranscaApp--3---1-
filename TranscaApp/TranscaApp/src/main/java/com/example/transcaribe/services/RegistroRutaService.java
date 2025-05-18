package com.example.transcaribe.services;



import com.example.transcaribe.entity.RegistroRuta;
import com.example.transcaribe.repository.RegistroRutaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistroRutaService {

    private final RegistroRutaRepository repository;

    public RegistroRutaService(RegistroRutaRepository repository) {
        this.repository = repository;
    }

    public RegistroRuta guardarRegistro(RegistroRuta registro) {
        registro.setFecha(LocalDateTime.now());
        return repository.save(registro);
    }
}

