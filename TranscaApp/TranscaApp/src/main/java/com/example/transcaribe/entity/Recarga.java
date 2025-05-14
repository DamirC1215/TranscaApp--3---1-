package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "recargas")
public class Recarga {

    @Id
    private String id;

    private Double monto;
    private LocalDate fecha;

    private Usuario usuario;


    private Tarjeta tarjeta;


}