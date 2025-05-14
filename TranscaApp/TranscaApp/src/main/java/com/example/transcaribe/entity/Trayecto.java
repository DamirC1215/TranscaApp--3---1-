package com.example.transcaribe.entity;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document(collection = "trayectos")

public class Trayecto {

    @Id private String id;
    private String nombre;
    private Integer cantidadEstaciones;
    private String tipoVehiculo;

    private boolean estado = true;

    public boolean getEstado() {
        return estado;
    }

    private Double duracionEstimada;

    private List<Estacion> estaciones;


    public Trayecto() {}

    public Trayecto(String nombre, Integer cantidadEstaciones, String tipoVehiculo, Boolean estado, Double duracionEstimada) {
        this.nombre = nombre;
        this.cantidadEstaciones = cantidadEstaciones;
        this.tipoVehiculo = tipoVehiculo;
        this.estado = estado;
        this.duracionEstimada = duracionEstimada;
    }



    
}