package com.example.transcaribe.controller;



import com.example.transcaribe.entity.RegistroRuta;
import com.example.transcaribe.services.RegistroRutaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rutas")
public class RegistroRutaController {

    private final RegistroRutaService registroRutaService;

    public RegistroRutaController(RegistroRutaService registroRutaService) {
        this.registroRutaService = registroRutaService;
    }

    /**
     * Recibe el JSON con los datos de la ruta confirmada y la guarda en MongoDB.
     */
    @PostMapping("/registro")
    public ResponseEntity<RegistroRuta> registrarRuta(@RequestBody RegistroRuta registro) {
        RegistroRuta guardado = registroRutaService.guardarRegistro(registro);
        return ResponseEntity.ok(guardado);
    }
}
