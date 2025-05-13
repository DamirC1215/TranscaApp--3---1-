package com.example.transcaribe.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
        @GetMapping({"/", "/index"})
        public String mostrarInicio() {
            return "index";  // index.html
        }
    
        @GetMapping("/sobre")
        public String mostrarSobre() {
            return "sobre";  // sobre.html
        }
    
        @GetMapping("/servicios")
        public String mostrarServicios() {
            return "servicios";  // servicios.html
        }
    
        @GetMapping("/paginas")
        public String mostrarPaginas() {
            return "paginas";  // paginas.html
        }
    
        @GetMapping("/contactos")
        public String mostrarContactos() {
            return "contactos";  // contactos.html
        }
    
}    