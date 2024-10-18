package com.SpringWeb.Fruitables.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagamentoController {

   
    @GetMapping("/checkout")
    public String showCheckout() {
        return "pagamento/checkout";
    }

    @GetMapping("/pagamento")
    public String pagamento() {
        return "pagamento/pagamento"; 
    }
}
