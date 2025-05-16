
package com.example.transcaribe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TurnoDto {
    private String dia;
    private String horaInicio;
    private String horaFin;
    private String descanso;
    private String ruta;
    private Integer idTranscar;
}
