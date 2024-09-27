package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        double shipping = 3.00;
        model.addAttribute("cart", carrinho);
        model.addAttribute("subtotal", carrinhoService.calcularTotal());
        model.addAttribute("shipping", shipping);
        model.addAttribute("total", carrinhoService.calcularTotal() + shipping); // Total para exibir o preço total
        model.addAttribute("quantidadeItens", carrinhoService.contarItensNoCarrinho()); // Adiciona a quantidade de itens ao modelo
        return "carrinho/index"; 
    }

    // Adiciona um produto ao carrinho e redireciona para a página do carrinho
    @GetMapping("/adicionarCarrinho/{id}")
    public String adicionarCarrinho(@PathVariable int id, @RequestParam(defaultValue = "1") int quantidade, Model model) {
        carrinhoService.adicionarProdutoAoCarrinho(id, quantidade); 
        return "redirect:/carrinho"; 
    }

    // Atualiza a quantidade de um produto no carrinho
    @PostMapping("/update")
    public String atualizarQuantidade(@RequestParam("produtoId") int produtoId,
                                      @RequestParam("quantidade") int quantidade, Model model) {
        // Atualiza a quantidade do produto no carrinho
        carrinhoService.atualizarQuantidadeNoCarrinho(produtoId, quantidade);

        Carrinho carrinho = carrinhoService.getCarrinhoFromSession();
        model.addAttribute("cart", carrinho);
        
        // Redireciona de volta para a página do carrinho
        return "redirect:/carrinho";
    }

    // Remove um produto do carrinho
    @PostMapping("/remove")
    public String removerProduto(@RequestParam("produtoId") int produtoId, Model model) {
        // Remove o produto do carrinho
        carrinhoService.removerProdutoDoCarrinho(produtoId);

        // Redireciona para a página do carrinho após remover o item
        return "redirect:/carrinho";
    }

    @ModelAttribute
    public void addCarrinhoAttributes(Model model) {
        int quantidadeItens = carrinhoService.contarItensNoCarrinho();
        model.addAttribute("quantidadeItens", quantidadeItens);
    }
}
