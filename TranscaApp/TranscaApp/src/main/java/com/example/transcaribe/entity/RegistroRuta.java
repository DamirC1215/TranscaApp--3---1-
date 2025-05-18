package com.example.transcaribe.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "registroRutas")
public class RegistroRuta {
    @Id
    private String id;

    private int rutaId;
    private String rutaNombre;
    private String origen;
    private String destino;
    private List<String> estacionesRecorridas;
    private int tiempoEstimado; // en minutos
    private LocalDateTime fecha;

    // Getters y setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getRutaId() { return rutaId; }
    public void setRutaId(int rutaId) { this.rutaId = rutaId; }

    public String getRutaNombre() { return rutaNombre; }
    public void setRutaNombre(String rutaNombre) { this.rutaNombre = rutaNombre; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public List<String> getEstacionesRecorridas() { return estacionesRecorridas; }
    public void setEstacionesRecorridas(List<String> estacionesRecorridas) { this.estacionesRecorridas = estacionesRecorridas; }

    public int getTiempoEstimado() { return tiempoEstimado; }
    public void setTiempoEstimado(int tiempoEstimado) { this.tiempoEstimado = tiempoEstimado; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
