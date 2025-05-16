package com.example.transcaribe.controller;

import com.example.transcaribe.entity.Administrador;
import com.example.transcaribe.entity.Conductor;
import com.example.transcaribe.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Logeo {
    Administrador administrador;
    Usuario usuario;
    Conductor conductor;
    boolean esConductor;
    boolean esAdministrador;
    boolean esUsuario;
    String redirect;

    public Logeo(Administrador administrador) {
        this.administrador = administrador;
        this.esAdministrador = true;
        this.redirect = "redirect:/admin/indexAdm";
    }

    public Logeo(Usuario usuario) {
        this.usuario = usuario;
        this.esUsuario = true;
        this.redirect = "redirect:/perfil";
    }

    public Logeo(Conductor conductor) {
        this.conductor   = conductor;
        this.esConductor = true;
        this.redirect    = "redirect:/admin/indexConductor";
        this.redirect    = "redirect:/conductor/index";
    }
}
