package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;

import com.SpringWeb.Fruitables.models.Administrador;
import com.SpringWeb.Fruitables.repositorio.AdministradoresRepo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private AdministradoresRepo repo;

    @GetMapping("/login")
    public String index() {
        return "login/index";
    }

    @GetMapping("/register")
    public String register() {
        return "login/register";
    }

    @PostMapping("/logar")
    public String logar(Model model, Administrador admParam, String lembrar, HttpServletResponse response) {
        Administrador adm = this.repo.Login(admParam.getEmail(), admParam.getSenha());
        if(adm != null) {
            // CookieService.setCookie(response);
            return "redirect:/home";
        }
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login/index";
    }
}





