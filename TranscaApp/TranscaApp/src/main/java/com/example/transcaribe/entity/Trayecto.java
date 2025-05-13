package com.example.transcaribe.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderColumn;

@Entity
public class Trayecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer cantidadEstaciones;
    private String tipoVehiculo;

    @Column(nullable = false)
    private boolean estado = true;
    private Double duracionEstimada;

     @ManyToMany
@JoinTable(
    name = "trayecto_estacion",
    joinColumns = @JoinColumn(name = "trayecto_id"),
    inverseJoinColumns = @JoinColumn(name = "estacion_id")
)
@OrderColumn(name = "orden") //
private List<Estacion> estaciones;


    public Trayecto() {}

    public Trayecto(String nombre, Integer cantidadEstaciones, String tipoVehiculo, Boolean estado, Double duracionEstimada) {
        this.nombre = nombre;
        this.cantidadEstaciones = cantidadEstaciones;
        this.tipoVehiculo = tipoVehiculo;
        this.estado = estado;
        this.duracionEstimada = duracionEstimada;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadEstaciones() {
        return this.cantidadEstaciones;
    }

    public void setCantidadEstaciones(Integer cantidadEstaciones) {
        this.cantidadEstaciones = cantidadEstaciones;
    }

    public String getTipoVehiculo() {
        return this.tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;

    }

    public Double getDuracionEstimada() {
        return this.duracionEstimada;
    }

    public void setDuracionEstimada(Double duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public List<Estacion> getEstaciones() {
        return this.estaciones;
    }

    public void setEstaciones(List<Estacion> estaciones) {
        this.estaciones = estaciones;
    }
    
}