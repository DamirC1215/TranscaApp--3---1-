package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "horarios")
@Getter
@Setter
public class Horario {

    @Id
    private String id;
    private String dia;
    private String horaInicio;
    private String horaFin;
    private Conductor conductor;



}
