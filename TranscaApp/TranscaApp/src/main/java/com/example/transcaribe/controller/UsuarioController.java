package com.example.transcaribe.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.services.UsuarioServices;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("usuario", new Usuario()); 
        return "registro";  
    }

    
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        if (usuarioServices.existePorEmail(usuario.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "El correo ya está en uso");
            return "redirect:/registro";  
        }
        usuarioServices.guardarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario registrado exitosamente");
        return "redirect:/login";  
    }
}