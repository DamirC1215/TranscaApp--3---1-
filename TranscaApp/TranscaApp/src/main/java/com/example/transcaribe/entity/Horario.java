package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "horarios")
public class Horario {

    @Id
    private String id;

    private String conductorId;        // asociamos al conductor
    private String dia;                // "Lunes", "Martes", …

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime inicioJornada;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime inicioDescanso;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finDescanso;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finJornada;

    private Integer numeroTranscaribe;
    private String rutaAsignada;


    // Constructor de conveniencia
    public Horario(String conductorId, String dia,
                   LocalDateTime inicioJornada, LocalDateTime inicioDescanso,
                   LocalDateTime finDescanso, LocalDateTime finJornada,
                   Integer numeroTranscaribe, String rutaAsignada) {
        this.conductorId = conductorId;
        this.dia = dia;
        this.inicioJornada = inicioJornada;
        this.inicioDescanso = inicioDescanso;
        this.finDescanso = finDescanso;
        this.finJornada = finJornada;
        this.numeroTranscaribe = numeroTranscaribe;
        this.rutaAsignada = rutaAsignada;
    }
}
