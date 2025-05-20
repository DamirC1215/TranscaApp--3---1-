package com.example.transcaribe.dto;


import java.util.List;

public class RegistroRutaDTO {
    private Integer rutaId;
    private String rutaNombre;
    private String origen;
    private String destino;
    private List<String> estacionesRecorridas;
    private Integer tiempoEstimado;

    // Getters y setters

    public Integer getRutaId() {
        return rutaId;
    }

    public void setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
    }

    public String getRutaNombre() {
        return rutaNombre;
    }

    public void setRutaNombre(String rutaNombre) {
        this.rutaNombre = rutaNombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<String> getEstacionesRecorridas() {
        return estacionesRecorridas;
    }

    public void setEstacionesRecorridas(List<String> estacionesRecorridas) {
        this.estacionesRecorridas = estacionesRecorridas;
    }

    public Integer getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Integer tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }
}

