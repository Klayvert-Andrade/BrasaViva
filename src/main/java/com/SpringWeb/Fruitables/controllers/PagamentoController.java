package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringWeb.Fruitables.services.PagamentoService;
import com.SpringWeb.Fruitables.services.VendaService;

@Controller
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private VendaService vendaService;

    // @PostMapping("/checkout")
    // public String finalizarCompra(@RequestParam("metodoPagamento") String metodoPagamento, Model model) {
    //     // Supondo que o cliente e os itens do carrinho estejam na sessão ou contexto
    //     Cliente cliente = ... // Obtenha o cliente autenticado
    //     List<ItemCarrinho> itensCarrinho = ... // Obtenha os itens do carrinho
    //     double valorTotal = ... // Calcule o valor total do carrinho

    //     // Processar o pagamento
    //     Pagamento pagamento = pagamentoService.processarPagamento(metodoPagamento, valorTotal);

    //     // Finalizar a venda
    //     Venda venda = vendaService.finalizarVenda(cliente, itensCarrinho, valorTotal, pagamento);

    //     // Redirecionar ou exibir a página de confirmação
    //     model.addAttribute("venda", venda);
    //     return "confirmacao";
    // }

    @GetMapping("/checkout")
    public String showCheckout() {
        return "pagamento/checkout";
    }

    @GetMapping("/pagamento")
    public String pagamento() {
        return "pagamento/pagamento"; 
    }
}
