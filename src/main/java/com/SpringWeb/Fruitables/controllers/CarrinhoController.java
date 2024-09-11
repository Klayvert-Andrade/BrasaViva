package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SpringWeb.Fruitables.models.Carrinho;
import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.repositorio.ProdutosRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutosRepo produtosRepo;

    @PostMapping("/add/{id}")
    @ResponseBody
    public ResponseEntity<String> adicionarAoCarrinho(@PathVariable("id") int id, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new Carrinho();
        }

        Produto produto = produtosRepo.findById(id).orElse(null); // Certifique-se de que o nome do repositório está correto
        if (produto == null) {
            return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        carrinho.adicionarItem(produto, 1);
        session.setAttribute("carrinho", carrinho);

        return new ResponseEntity<>("Produto adicionado com sucesso", HttpStatus.OK);
    }

    @PostMapping("/update")
    public String atualizarQuantidade(@RequestParam("produtoId") int produtoId,
                                      @RequestParam("quantidade") int quantidade,
                                      HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho != null) {
            carrinho.atualizarQuantidade(produtoId, quantidade);
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removerItem(@RequestParam("produtoId") int produtoId, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho != null) {
            carrinho.removerItem(produtoId);
        }
        return "redirect:/cart";
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new Carrinho();
        }
        model.addAttribute("cart", carrinho);
        return "carrinho/cart"; // Página que mostra os itens no carrinho
    }

    @GetMapping("/quantidade")
    @ResponseBody
    public int getQuantidadeCarrinho(HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho == null) {
            return 0;
        }
        return carrinho.getItens().size();
    }

}
