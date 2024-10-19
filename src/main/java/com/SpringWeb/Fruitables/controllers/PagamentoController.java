package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringWeb.Fruitables.models.Carrinho;
import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.services.CarrinhoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PagamentoController {

    @Autowired
    private CarrinhoService carrinhoService;
   
    @GetMapping("/checkout")
    public String showCheckout(HttpSession session, Model model) {
        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");

        if (clienteLogado != null) {
            return "redirect:/pagamento/finalizar";
        }

        return "pagamento/checkout";
    }

    @GetMapping("pagamento/finalizar")
    public String finalizarCompra(Model model) {
        Carrinho carrinho = carrinhoService.getCarrinhoFromSession(); // Obter o carrinho da sessão
        double shipping = 3.00;
        model.addAttribute("cart", carrinho);
        model.addAttribute("subtotal", carrinhoService.calcularTotal());
        model.addAttribute("shipping", shipping);
        model.addAttribute("total", carrinhoService.calcularTotal() + shipping); // Total para exibir o preço total
        model.addAttribute("quantidadeItens", carrinhoService.contarItensNoCarrinho()); // Adiciona a quantidade de itens ao modelo

        return "pagamento/finalizar";
    }
}
