package com.SpringWeb.Fruitables.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.repositorio.ProdutoRepo;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepo repo;

    // listar todos os produtos
    @GetMapping("/produtos")
    public String index(Model model) {
        List<Produto> produtos = (List<Produto>)repo.findAll(); // Busca todos os produtos do repositório
        model.addAttribute("produtos", produtos);  // Adiciona os produtos ao modelo para serem exibidos na view
        
        return "administrativo/produtos/index"; // Retorna o template que exibirá a lista de produtos
    }

    // Exibir formulário para criar um novo produto
    @GetMapping("/produtos/novo")
    public String novo() {
        return "administrativo/produtos/novo";
    }

    // Processar o formulário de criação de novo produto
    @PostMapping("/produtos/criar")
    public String criar(Produto produto) {
        repo.save(produto);
        return "redirect:/produtos";
    }

    // Excluir um produto por ID
    @GetMapping("/produtos/{id}/excluir")
    public String excluir(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/produtos";
    }

    // Buscar produto para edição
    @GetMapping("/produtos/{id}")
    public String buscar(@PathVariable int id, Model model) {
        Optional<Produto> produto = repo.findById(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "administrativo/produtos/editar";
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
            return "redirect:/produtos";
        }
    }

    @PostMapping("/produtos/{id}/atualizar")
    public String atualizar(@PathVariable int id, Produto produto) {
        if(!repo.existsById(id)) {
            return "redirect:/produtos";
        }

        repo.save(produto);
        
        return "redirect:/produtos";
    }    

    @GetMapping("/relatorios/produtos")
    public String listar(Model model) {
        List<Produto> produtos = (List<Produto>)repo.findAll();
        model.addAttribute("produtos", produtos);

        return "administrativo/produtos/lista";
    }   

    // // Lista todos os produtos
    // @GetMapping
    // public ResponseEntity<List<Produto>> listarProdutos() {
    //     List<Produto> produtos = produtoService.findAll();
    //     return new ResponseEntity<>(produtos, HttpStatus.OK);
    // }

    // // Adiciona um novo produto
    // @PostMapping
    // public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
    //     Produto produtoCriado = produtoService.adicionarProduto(produto);
    //     return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    // }

    // // Atualiza um produto existente
    // @PutMapping("/{id}")
    // public ResponseEntity<Produto> atualizarProduto(@PathVariable int id, @RequestBody Produto produtoAtualizado) {
    //     try {
    //         Produto produto = produtoService.atualizarProduto(id, produtoAtualizado);
    //         return new ResponseEntity<>(produto, HttpStatus.OK);
    //     } catch (IllegalArgumentException e) {
    //         return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    //     }
    // }

    // // Remove um produto
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> removerProduto(@PathVariable int id) {
    //     try {
    //         produtoService.removerProduto(id);
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     } catch (IllegalArgumentException e) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
}
