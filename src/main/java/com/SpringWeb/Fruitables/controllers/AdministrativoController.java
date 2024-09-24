package com.SpringWeb.Fruitables.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrativoController {
    
    @GetMapping("/administrativo")
    public String acessarHome() {
        return "administrativo/home/index";
    }
}
