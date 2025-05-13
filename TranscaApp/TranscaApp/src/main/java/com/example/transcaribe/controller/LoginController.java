package com.example.transcaribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.services.UsuarioServices;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private UsuarioServices usuarioServices;
    

    @GetMapping("/login")
    public String irLogin(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "login";  // login.html
    }

        
        @PostMapping("/login")        
        public String login(@RequestParam("email") String email, @RequestParam("contraseña") String contraseña, 
                        HttpSession session, Model model) {
        Usuario usuario = usuarioServices.buscarPorEmail(email);
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            session.setAttribute("usuarioLogueado", usuario); 
            return "redirect:/perfil";  
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            model.addAttribute("usuario", new Usuario());
            return "login";  
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  
        return "redirect:/login";  
    }

    
}
