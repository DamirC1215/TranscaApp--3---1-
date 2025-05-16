package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "vehiculos")
public class Vehiculo {
    @Id
    private String id;
    private String modelo;
    private String placa;
    private Integer idTranscar;
}
