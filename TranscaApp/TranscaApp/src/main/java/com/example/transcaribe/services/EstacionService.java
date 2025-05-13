package com.example.transcaribe.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.transcaribe.entity.Estacion;


@Service
public interface EstacionService {
    List<Estacion> obtenerTodasLasEstaciones();
}
