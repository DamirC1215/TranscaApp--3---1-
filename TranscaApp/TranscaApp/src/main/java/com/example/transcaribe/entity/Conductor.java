package com.example.transcaribe.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "conductores")
@Getter
@Setter
public class Conductor {
    @Id
    private String id;

    private String nombre;
    private String nombres;
    private String apellido;
    private String correoElectronico;
    private String password;
    private String licencia;
    private String rutaAsignada;
    private String tipo = "CONDUCTOR";
    private String idTranscar;     // para “conductor.idTranscar”


}
