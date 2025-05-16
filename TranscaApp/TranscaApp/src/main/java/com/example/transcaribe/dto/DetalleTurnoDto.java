
package com.example.transcaribe.dto;

import lombok.Data;

@Data
public class DetalleTurnoDto {
    private String dia;
    private String horaInicio;
    private String horaFin;
    private String descanso;
    private String ruta;
    private String modeloVehiculo;
    private String placa;
    private Integer idTranscar;
}
