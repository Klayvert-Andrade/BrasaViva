package com.SpringWeb.Fruitables.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.repositorio.ProdutosRepo; 



@Controller
public class HomeController {

    @Autowired
    private ProdutosRepo produtosRepo;


    @GetMapping("/home")

    public String showHome(@RequestParam(name = "categoria", required = false) String categoria, Model model) {
        List<Produto> produtos = produtosRepo.findAll();
        
        model.addAttribute("produtos", produtos);
        
        return "home/index"; // Caminho para o template da página inicial
    }

    @GetMapping("/produtos/filtrar")
    public String filtrarProdutos(@RequestParam(name = "categoria", required = false) String categoria, Model model) {
    List<Produto> produtos;

    try {
        if (categoria == null || categoria.isEmpty()) {
            produtos = produtosRepo.findAll();
        } else {
            produtos = produtosRepo.findByCategoria(categoria);
        }

        model.addAttribute("produtos", produtos);
        System.out.println("Produtos filtrados: " + produtos); // Log para depuração
        return "fragments/produtos :: produtos";
    } catch (Exception e) {
        e.printStackTrace(); // Log da exceção
        return "error"; // Retorna uma página de erro ou uma mensagem apropriada
    }
}

}


