package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringWeb.Fruitables.models.Carrinho;
import com.SpringWeb.Fruitables.services.CarrinhoService;
@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public String showCart(Model model) {
        Carrinho carrinho = carrinhoService.getCarrinhoFromSession(); // Obter o carrinho da sessão
        model.addAttribute("cart", carrinho); // Use "cart" para casar com o que é esperado no HTML
        model.addAttribute("total", carrinhoService.calcularTotal()); // Total para exibir o preço total
        return "carrinho/index"; // Página do carrinho
    }

    // Adiciona um produto ao carrinho e redireciona para a página do carrinho
    @GetMapping("/adicionarCarrinho/{id}")
    public String adicionarCarrinho(@PathVariable int id, Model model) {
        carrinhoService.adicionarProdutoAoCarrinho(id, 1); // Adiciona o produto ao carrinho
        model.addAttribute("carrinho", carrinhoService.getCarrinhoFromSession());
        model.addAttribute("total", carrinhoService.calcularTotal());
        return "carrinho/carrinho"; // Retorna diretamente a página
    }
}
