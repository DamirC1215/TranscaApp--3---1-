package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter
@Document(collection = "trayectos")
public class Trayecto {

    @Id
    private String id;
    private String nombre;
    private Integer cantidadEstaciones;
    private String tipoVehiculo;

    /**
     * Cambiado a Integer para que encaje con 0/1
     */
    private Integer estado = 1;

    private Double duracionEstimada;
    private List<Estacion> estaciones;

    /**
     * Nuevo campo para contar las confirmaciones de uso.
     */
    private Integer uso = 0;

    // constructor vacío, otros getters/setters generados por Lombok

    /**
     * Método de conveniencia para usarlo como boolean
     */
    public boolean isActivo() {
        return estado != null && estado.equals(1);
    }
}
