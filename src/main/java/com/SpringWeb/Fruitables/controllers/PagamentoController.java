package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringWeb.Fruitables.models.Administrador;
import com.SpringWeb.Fruitables.models.Carrinho;
import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.services.AdministradorService;
import com.SpringWeb.Fruitables.services.CarrinhoService;
import com.SpringWeb.Fruitables.services.VendaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PagamentoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private VendaService vendaService;

    @Autowired
    private AdministradorService administradorService;
   
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

    @PostMapping("/pagamento/confirmar")
    public String confirmarVenda(@RequestParam("metodoPagamento") String metodoPagamento, 
                                @RequestParam("sellerCode") String sellerCode, 
                                HttpSession session) {
        // Recuperar cliente logado
        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");
        Carrinho carrinho = carrinhoService.getCarrinhoFromSession(); // Recupera o carrinho do cliente logado
        double valorTotal = carrinhoService.calcularTotal() + 3.00; // Adiciona o valor do frete

        // Obter o administrador pelo código
        Administrador administrador = administradorService.findById(Integer.parseInt(sellerCode));

        if (clienteLogado == null || administrador == null) {
            // Redirecionar de volta para /home com erro se não houver cliente ou administrador
            return "redirect:/home?erro=invalid_payment";
        }

        // Chama o serviço para finalizar a venda
        vendaService.finalizarVenda(clienteLogado, administrador, carrinho.getItens(), valorTotal, metodoPagamento);

        // Limpar o carrinho após a venda
        carrinhoService.limparCarrinho();

        // Redirecionar para /home após a compra ser finalizada com sucesso
        return "redirect:/home?compra=sucesso";
    }

}
