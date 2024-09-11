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

import com.SpringWeb.Fruitables.models.Estoque;
import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.repositorio.EstoqueRepo;
import com.SpringWeb.Fruitables.repositorio.ProdutosRepo;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepo estoqueRepo;

    @Autowired
    private ProdutosRepo produtosRepo;

    // Exibe todos os produtos com seus respectivos estoques
    @GetMapping("/listar")
    public String listarEstoques(Model model) {
        model.addAttribute("estoques", estoqueRepo.findAll());
        return "estoque/listar"; // Um arquivo de template (listar.html) que exibe os estoques
    }

    // Exibe o formulário para adicionar um estoque
    @GetMapping("/adicionar")
    public String exibirFormAdicionarEstoque(Model model) {
        model.addAttribute("produtos", produtosRepo.findAll()); // Lista de produtos
        model.addAttribute("estoque", new Estoque()); // Objeto vazio para criar novo estoque
        return "estoque/adicionar"; // O template para o formulário (adicionar.html)
    }

    // Processa o formulário de criação de um novo estoque
    @PostMapping("/adicionar")
    public String adicionarEstoque(@ModelAttribute Estoque estoque, @RequestParam("produtoId") int produtoId) {
        Produto produto = produtosRepo.findById(produtoId).orElse(null);
        if (produto != null) {
            estoque.setProduto(produto);
            estoqueRepo.save(estoque);
        }
        return "redirect:/estoque/listar"; // Redireciona para a listagem de estoques
    }

    // Exibe o formulário para editar um estoque existente
    @GetMapping("/editar/{id}")
    public String exibirFormEditarEstoque(@PathVariable("id") int id, Model model) {
        Estoque estoque = estoqueRepo.findById(id).orElse(null);
        if (estoque != null) {
            model.addAttribute("estoque", estoque);
            return "estoque/editar"; // O template para editar (editar.html)
        }
        return "redirect:/estoque/listar"; // Redireciona se o estoque não for encontrado
    }

    // Processa o formulário de edição de um estoque
    @PostMapping("/editar/{id}")
    public String editarEstoque(@PathVariable("id") int id, @ModelAttribute Estoque estoque) {
        Estoque estoqueExistente = estoqueRepo.findById(id).orElse(null);
        if (estoqueExistente != null) {
            estoqueExistente.setQuantidade(estoque.getQuantidade());
            estoqueRepo.save(estoqueExistente);
        }
        return "redirect:/estoque/listar";
    }

    // Deleta um estoque pelo seu ID
    @GetMapping("/deletar/{id}")
    public String deletarEstoque(@PathVariable("id") int id) {
        estoqueRepo.deleteById(id);
        return "redirect:/estoque/listar";
    }
}
