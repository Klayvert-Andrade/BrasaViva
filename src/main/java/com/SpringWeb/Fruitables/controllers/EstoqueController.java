package com.SpringWeb.Fruitables.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringWeb.Fruitables.models.Estoque;
import com.SpringWeb.Fruitables.repositorio.EstoqueRepo;
import com.SpringWeb.Fruitables.repositorio.ProdutoRepo;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepo estoqueRepo;

    @Autowired
    private ProdutoRepo produtoRepo;

    // Exibe todos os produtos com seus respectivos estoques
    @GetMapping("/listar")
    public String listarEstoques(Model model) {
        model.addAttribute("estoques", estoqueRepo.findAll());
        return "estoque/listar"; // Template para exibir a lista de estoques
    }

    // Exibe o formulário para adicionar um estoque
    @GetMapping("/adicionar")
    public String exibirFormAdicionarEstoque(Model model) {
        model.addAttribute("produtos", produtoRepo.findAll()); // Lista de produtos disponíveis
        model.addAttribute("estoque", new Estoque()); // Novo objeto Estoque
        return "estoque/adicionar"; // Template para o formulário de adição de estoque
    }

    // // Processa o formulário de criação de um novo estoque
    // @PostMapping("/adicionar")
    // public String adicionarEstoque(@ModelAttribute Estoque estoque, @RequestParam("produtoId") int produtoId, Model model) {
    //     Produto produto = produtoRepo.findById(produtoId);
    //     if (produto == null) {
    //         model.addAttribute("error", "Produto não encontrado.");
    //         model.addAttribute("produtos", produtoRepo.findAll()); // Recarrega a lista de produtos
    //         return "estoque/adicionar"; // Retorna ao formulário de adição com erro
    //     }
    //     estoque.setProduto(produto);
    //     estoqueRepo.save(estoque);
    //     return "redirect:/estoque/listar"; // Redireciona para a lista de estoques
    // }

    // Exibe o formulário para editar um estoque existente
    @GetMapping("/editar/{id}")
    public String exibirFormEditarEstoque(@PathVariable("id") int id, Model model) {
        Estoque estoque = estoqueRepo.findById(id).orElse(null);
        if (estoque == null) {
            model.addAttribute("error", "Estoque não encontrado.");
            return "redirect:/estoque/listar"; // Redireciona para a lista de estoques com mensagem de erro
        }
        model.addAttribute("estoque", estoque);
        return "estoque/editar"; // Template para o formulário de edição
    }

    // Processa o formulário de edição de um estoque
    @PostMapping("/editar/{id}")
    public String editarEstoque(@PathVariable("id") int id, @ModelAttribute Estoque estoque, Model model) {
        Estoque estoqueExistente = estoqueRepo.findById(id).orElse(null);
        if (estoqueExistente == null) {
            model.addAttribute("error", "Estoque não encontrado.");
            return "redirect:/estoque/listar"; // Redireciona para a lista de estoques com mensagem de erro
        }
        estoqueExistente.setQuantidade(estoque.getQuantidade());
        estoqueRepo.save(estoqueExistente);
        return "redirect:/estoque/listar"; // Redireciona para a lista de estoques após edição
    }

    // Deleta um estoque pelo seu ID
    @GetMapping("/deletar/{id}")
    public String deletarEstoque(@PathVariable("id") int id, Model model) {
        if (!estoqueRepo.existsById(id)) {
            model.addAttribute("error", "Estoque não encontrado.");
            return "redirect:/estoque/listar"; // Redireciona para a lista de estoques com mensagem de erro
        }
        estoqueRepo.deleteById(id);
        return "redirect:/estoque/listar"; // Redireciona para a lista de estoques após exclusão
    }
}
