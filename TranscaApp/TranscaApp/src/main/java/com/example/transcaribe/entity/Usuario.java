package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;


    private String nombres;
    private String email;
    private String contraseña;
    private String apellido;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String tipo = "USUARIO"; // Siempre será "USUARIO"

    // Relación OneToMany con Recarga
    private List<Recarga> recargas;

    private List<Tarjeta> tarjetas = new ArrayList<>();


}
