package com.SpringWeb.Fruitables.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.services.ProdutoService;

@Controller
public class ShopController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/shop")
    public String showShop(Model model) {
        try {
            List<Produto> produtos = produtoService.findAll(); // Carrega todos os produtos
            model.addAttribute("produtos", produtos);  // Adiciona à view
            return "shop/index";  // Retorna o template do Thymeleaf para a página de compras
        } catch (Exception e) {
            e.printStackTrace(); // Log para depuração
            model.addAttribute("error", "Não foi possível carregar os produtos."); // Mensagem de erro
            return "error"; // Retorna uma página de erro
        }
    }
}
