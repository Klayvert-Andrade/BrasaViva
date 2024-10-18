package com.SpringWeb.Fruitables.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringWeb.Fruitables.models.Cliente;

import jakarta.servlet.http.HttpSession;

@Controller
public class PagamentoController {

   
    @GetMapping("/checkout")
    public String showCheckout(HttpSession session, Model model) {
        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");

        if (clienteLogado != null) {
            return "redirect:/pagamento/pagamento";
        }

        return "pagamento/checkout";
    }

    @GetMapping("/pagamento")
    public String pagamento() {
        return "pagamento/pagamento"; 
    }
}
