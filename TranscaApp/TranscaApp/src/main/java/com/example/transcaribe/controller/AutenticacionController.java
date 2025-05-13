package com.example.transcaribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.transcaribe.services.AutenticacionService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/autenticacion")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (para pruebas)

public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String correo, @RequestParam String password) {
        Object usuario = autenticacionService.autenticarUsuario(correo, password);
        Map<String, Object> response = new HashMap<>();

        if (usuario != null) {
            response.put("success", true);
            response.put("user", usuario);
            response.put("message", "Autenticación exitosa");
        } else {
            response.put("success", false);
            response.put("message", "Credenciales incorrectas");
        }

        return response;
    }
}
