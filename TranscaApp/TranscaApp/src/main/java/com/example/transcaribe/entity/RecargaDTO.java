package com.example.transcaribe.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecargaDTO {
    private Double monto;

    // Getters y Setters
    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
