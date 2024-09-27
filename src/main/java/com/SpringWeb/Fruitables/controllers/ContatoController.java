package com.SpringWeb.Fruitables.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContatoController {
    @GetMapping("/contato")
    public String showCheckout() {
        return "contato/contact"; 
    }
    
}
