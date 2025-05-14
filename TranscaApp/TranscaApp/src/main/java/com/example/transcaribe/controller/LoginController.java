package com.example.transcaribe.controller;

import com.example.transcaribe.services.AdministradorService;
import com.example.transcaribe.services.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.transcaribe.entity.Usuario;
import com.example.transcaribe.services.UsuarioServices;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;


@Controller
public class LoginController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private AdministradorService administradorService ;

    @Autowired
    private AutenticacionService autenticacionService;
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

            Optional<Logeo> logeo  =   autenticacionService.autenticarUsuario(email, contraseña);

          if(logeo.isPresent()){
              Logeo log= logeo.get();
              if(log.esAdministrador){
                  session.setAttribute("administradorLogueado", log.administrador);
                  return log.redirect;
              }
                if(log.esUsuario){
                    session.setAttribute("usuarioLogueado", log.usuario);
                    return log.redirect;
                }
                if(log.esConductor){
                    session.setAttribute("conductorLogueado", log.conductor);
                    return log.redirect;
                }
          }


            model.addAttribute("error", "Correo o contraseña incorrectos");
            model.addAttribute("usuario", new Usuario());
            return "login";  

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  
        return "redirect:/login";  
    }

    
}
