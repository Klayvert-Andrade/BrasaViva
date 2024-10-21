package com.SpringWeb.Fruitables.controllers;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SpringWeb.Fruitables.models.Administrador;
import com.SpringWeb.Fruitables.models.Carrinho;
import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.models.Venda;
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
        double desconto = 0.0;

        double total = carrinhoService.calcularTotal() - desconto;

        // Formatar o total para duas casas decimais
        DecimalFormat df = new DecimalFormat("#.00");
        String totalFormatado = df.format(total);

        model.addAttribute("cart", carrinho);
        model.addAttribute("subtotal", carrinhoService.calcularTotal());
        model.addAttribute("desconto", desconto);
        model.addAttribute("total", totalFormatado); // Total para exibir o preço total
        model.addAttribute("quantidadeItens", carrinhoService.contarItensNoCarrinho()); // Adiciona a quantidade de itens ao modelo

        return "pagamento/finalizar";
    }

    @PostMapping("/pagamento/confirmar")
    public String confirmarVenda(
            @RequestParam("metodoPagamento") String metodoPagamento, 
            @RequestParam("sellerCode") int sellerCode, 
            HttpSession session, 
            Model model, RedirectAttributes redirectAttributes) {
        
        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");
    
        // Verifica se há um cliente logado
        if (clienteLogado == null) {
            redirectAttributes.addFlashAttribute("erro", "Você precisa estar logado para finalizar a compra.");
            return "redirect:/login_cliente"; 
        }
    
        // Recuperar o carrinho da sessão
        Carrinho carrinho = carrinhoService.getCarrinhoFromSession();
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "O carrinho está vazio.");
            return "redirect:/carrinho"; 
        }
    
        // Calcular o valor total com o frete
        double valorTotal = carrinhoService.calcularTotal() + 3.00; // 3.00 é o valor do frete
    
        // Obter o administrador pelo código do vendedor usando o serviço
        Administrador administrador = administradorService.findById(sellerCode);
        if (administrador == null) {
            redirectAttributes.addFlashAttribute("erro", "Código de vendedor inválido.");
            return "redirect:/carrinho"; 
        }
    
        try {
            vendaService.finalizarVenda(clienteLogado, administrador, carrinho.getItens(), valorTotal, metodoPagamento);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("erro", "Ocorreu um erro ao finalizar a venda: " + e.getMessage());
            return "redirect:/shop";
        }
    
        carrinhoService.limparCarrinho();
    
        return "redirect:/home";
    }

    @GetMapping("/relatorios/vendas")
    public String listar(Model model) {
        try {
            List<Venda> vendas = vendaService.findAll(); 
            model.addAttribute("vendas", vendas);
            return "administrativo/vendas/lista"; // Verifique se o nome do template está correto
        } catch (Exception e) {
            e.printStackTrace(); // Log da exceção para depuração
            model.addAttribute("error", "Erro ao carregar as vendas."); // Mensagem de erro
            return "error"; // Redireciona para uma página de erro genérica
        }
    }
}
