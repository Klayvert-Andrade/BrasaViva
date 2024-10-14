package com.SpringWeb.Fruitables.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.services.ProdutoService;

import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/home")
    public String showHome(@RequestParam(name = "categoria", required = false) String categoria, Model model, HttpSession session) {
        List<Produto> produtos;

        // Obtém o cliente da sessão diretamente do modelo
        Cliente clienteLogado = (Cliente) session.getAttribute("cliente"); // Aqui é onde pegamos o cliente da sessão
        model.addAttribute("cliente", clienteLogado); // Adiciona o cliente ao modelo

        try {
            if (categoria == null || categoria.isEmpty()) {
                produtos = produtoService.findAll();
            } else {
                produtos = produtoService.findByCategoria(categoria);
            }
            model.addAttribute("produtos", produtos);
            return "home/index"; // Retorna para a página inicial
        } catch (Exception e) {
            e.printStackTrace(); // Log da exceção
            model.addAttribute("errorMessage", "Erro ao carregar produtos.");
            return "error"; // Retorna uma página de erro ou uma mensagem apropriada
        }
    }    
}
