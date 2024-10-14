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
public class LoginAdmController {

    @Autowired
    private AdministradoresRepo repo;

    @GetMapping("/login")
    public String index() {
        return "login_adm/index";
    }

    @GetMapping("/register")
    public String register() {
        return "login_adm/register";
    }

    @PostMapping("/logar")
    public String logar(Model model, Administrador admParam, String lembrar, HttpServletResponse response) {
       
        if(admParam.getEmail() == null || admParam.getSenha() == null || admParam.getEmail().isEmpty() || admParam.getSenha().isEmpty()) {
            model.addAttribute("erro", "Email e senha são obrigatórios");
            return "login_adm/index";
        }

        Administrador adm = this.repo.Login(admParam.getEmail(), admParam.getSenha());
        if(adm != null) {
            // Se lembrar de mim estiver ativado, adicionar lógica de cookie
            if("on".equals(lembrar)) {
                // Exemplo de como o serviço de cookies poderia ser chamado
                // CookieService.setCookie(response, adm);
            }
            return "redirect:/administrativo";  // Redireciona para a área administrativa após login
        }

        // Se a autenticação falhar, retorna à página de login com mensagem de erro
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login_adm/index";
    }
}





