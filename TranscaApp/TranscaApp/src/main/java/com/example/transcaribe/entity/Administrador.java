package com.example.transcaribe.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document(collection = "administradores")
public class Administrador {
    @Id
    private String id;
    private String nombre;
    private String correo;
    private String password;
    private String tipo = "ADMIN"; // Siempre será "ADMIN"




    
}
