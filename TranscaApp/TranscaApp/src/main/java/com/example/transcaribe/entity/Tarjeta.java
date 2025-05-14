package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "tarjetas")
public class Tarjeta {

    @Id
    private String id;
    private String numeroDeBarras;
    private Double saldo;
    private Usuario usuario;
    private List<Recarga> recargas = new ArrayList<>();

    public void agregarRecarga(Recarga recarga) {
        recarga.setTarjeta(this); // Asegurar la relación bidireccional
        this.recargas.add(recarga);
    }


}
    

   