package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "horarios")
@Getter
@Setter
public class Horario {
    @Id
    private String id;

    private String conductorId;      // Relación al conductor
    private LocalTime inicioJornada;
    private LocalTime inicioDescanso;
    private LocalTime finDescanso;
    private LocalTime finJornada;

    private String numeroTranscaribe;
    private String rutaAsignada;
}
