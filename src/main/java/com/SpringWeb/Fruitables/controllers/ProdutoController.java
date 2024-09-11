package com.SpringWeb.Fruitables.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.repositorio.ProdutosRepo;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutosRepo produtosRepo;

    @GetMapping("/shop")
    public String showShop(Model model) {
        List<Produto> produtos = (List<Produto>) produtosRepo.findAll();
        model.addAttribute("produtos", produtos);
        return "shop/index"; // Caminho para o template Thymeleaf
    }

}
