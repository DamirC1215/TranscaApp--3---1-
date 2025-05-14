package com.example.transcaribe.services;


import com.example.transcaribe.entity.Estacion;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface EstacionService {
    List<Estacion> obtenerTodasLasEstaciones();
}
