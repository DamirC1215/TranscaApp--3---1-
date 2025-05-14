package com.example.transcaribe.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "estaciones")
public class Estacion {

    @Id
    private String id;


    private String nombre;
    private Double latitud;
    private Double longitud;
    
    private List<Trayecto> trayectos; // Una estación puede pertenecer a múltiples trayectos


    public Estacion() {
    }


    public Estacion(String id, String nombre, Double latitud, Double longitud, List<Trayecto> trayectos) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.trayectos = trayectos;
    }

    

}