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
            return "redirect:/finalizar";
        }

        return "pagamento/checkout";
    }

    @GetMapping("/finalizar")
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
    public String confirmarVenda(
            @RequestParam("metodoPagamento") String metodoPagamento, 
            @RequestParam("sellerCode") int sellerCode, 
            HttpSession session, 
            Model model) {
        // Recuperar cliente logado
        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");
    
        // Verifica se há um cliente logado
        if (clienteLogado == null) {
            model.addAttribute("erro", "Você precisa estar logado para finalizar a compra.");
            return "redirect:/login_cliente"; // Redireciona para o login se não houver cliente logado
        }
    
        // Recuperar o carrinho da sessão
        Carrinho carrinho = carrinhoService.getCarrinhoFromSession();
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            model.addAttribute("erro", "O carrinho está vazio.");
            return "redirect:/carrinho"; // Redireciona para a página do carrinho se estiver vazio
        }
    
        // Calcular o valor total com o frete
        double valorTotal = carrinhoService.calcularTotal() + 3.00; // 3.00 é o valor do frete
    
        // Obter o administrador pelo código do vendedor usando o serviço
        Administrador administrador = administradorService.findById(sellerCode);
        if (administrador == null) {
            model.addAttribute("erro", "Código de vendedor inválido.");
            return "redirect:/carrinho"; // Redireciona para a página do carrinho se o vendedor for inválido
        }
    
        try {
            // Chama o serviço para finalizar a venda
            vendaService.finalizarVenda(clienteLogado, administrador, carrinho.getItens(), valorTotal, metodoPagamento);
        } catch (Exception e) {
            // Redireciona com erro em vez de adicionar ao modelo
            session.setAttribute("erro", "Ocorreu um erro ao finalizar a venda: " + e.getMessage());
            return "redirect:/carrinho"; // Redireciona para a página do carrinho em caso de erro
        }
    
        // Limpar o carrinho após a venda
        carrinhoService.limparCarrinho();
    
        // Redirecionar para /home após a compra ser finalizada com sucesso
        return "redirect:/home";
    }
    
}
