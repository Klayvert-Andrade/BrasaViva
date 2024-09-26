package com.SpringWeb.Fruitables.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    public String criar(Produto produto, @RequestParam("imagem") MultipartFile imagem) {
        // Verifica se o arquivo de imagem foi enviado
        if (!imagem.isEmpty()) {
            try {
                // Define o caminho onde a imagem será salva
                String caminho = new File("src/main/resources/static/img/" + imagem.getOriginalFilename()).getAbsolutePath();

                
                // Salva o arquivo na pasta especificada
                File destino = new File(caminho);
                imagem.transferTo(destino);
                
                // Define a URL da imagem no produto (caminho relativo para ser usado no HTML)
                produto.setImagemUrl("/img/" + imagem.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/produtos"; // ou exiba uma mensagem de erro apropriada
            }
        }
        // Salva o produto com a URL da imagem
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
    public String atualizar(@PathVariable int id, Produto produto, @RequestParam("imagem") MultipartFile imagem) {
        // Verifica se o produto existe
        if (!repo.existsById(id)) {
            return "redirect:/produtos";
        }

        try {
            // Processar a nova imagem, se fornecida
            if (!imagem.isEmpty()) {
                // Define o caminho onde a nova imagem será salva
                String nomeArquivo = imagem.getOriginalFilename();
                String caminho = new File("src/main/resources/static/img/" + nomeArquivo).getAbsolutePath();

                // Verifica se o arquivo foi salvo corretamente
                File destino = new File(caminho);
                imagem.transferTo(destino);

                // Define a nova URL da imagem no produto
                produto.setImagemUrl("/img/" + nomeArquivo);
            } else {
                // Se nenhuma nova imagem for enviada, mantém a URL da imagem existente
                Optional<Produto> produtoExistente = repo.findById(id);
                if (produtoExistente.isPresent()) {
                    produto.setImagemUrl(produtoExistente.get().getImagemUrl());
                }
            }

            // Salva o produto atualizado no banco de dados
            repo.save(produto);

        } catch (IOException e) {
            // Captura exceções relacionadas ao processamento da imagem
            e.printStackTrace();
            return "redirect:/produtos?erroImagem";  // Redireciona com uma mensagem de erro
        }

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
