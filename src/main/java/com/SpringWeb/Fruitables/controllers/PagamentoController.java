package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringWeb.Fruitables.services.CarrinhoService;

@Controller
public class PagamentoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/checkout")
    public String showCheckout() {
        return "pagamento/checkout";
    }

    @GetMapping("/pagamento")
    public String pagamento() {
        return "pagamento/pagamento"; 
    }
}
