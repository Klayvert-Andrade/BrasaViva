package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.repositorio.ClientesRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginClienteController {

    @Autowired
    private ClientesRepo repo;

    @GetMapping("/login_cliente")
    public String index() {
        return "login_cliente/index";
    }

    @GetMapping("/register_cliente")
    public String register() {
        return "login_cliente/register";
    }

    @PostMapping("/logar_cliente")
    public String logarCliente(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        Cliente cliente = repo.login(email, senha);
        
        if (cliente != null) {
            session.setAttribute("cliente", cliente);
            return "redirect:/home"; // Redireciona para home após login bem-sucedido
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login_cliente/index"; // Retorna para a tela de login em caso de erro
        }
    }


    @GetMapping("/logout")
    public String logoutCliente(HttpSession session) {
        session.invalidate();
        return "redirect:/login_cliente";
    }
    
}
