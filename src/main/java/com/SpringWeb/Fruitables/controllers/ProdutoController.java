package com.SpringWeb.Fruitables.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.SpringWeb.Fruitables.services.ProdutoService;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepo repo;

    @Autowired
    private ProdutoService produtoService;

    // listar todos os produtos
    @GetMapping("/produtos")
    public String index(Model model) {
        List<Produto> produtos = (List<Produto>)repo.findAll(); // Busca todos os produtos do repositório
        model.addAttribute("produtos", produtos);  // Adiciona os produtos ao modelo para serem exibidos na view
        
        return "administrativo/produtos/index"; // Retorna o template que exibirá a lista de produtos
    }

    @GetMapping("/produtos/listar")
    public String listarProdutos(@RequestParam(required = false, defaultValue = "padrao") String ordenar, Model model) {
        List<Produto> produtos;

        switch (ordenar) {
            case "preco_asc":
                produtos = produtoService.listarProdutosOrdenadosPorPrecoAsc();
                break;
            case "preco_desc":
                produtos = produtoService.listarProdutosOrdenadosPorPrecoDesc();
                break;
            case "nome_asc":
                produtos = produtoService.listarProdutosOrdenadosPorNomeAsc();
                break;
            case "nome_desc":
                produtos = produtoService.listarProdutosOrdenadosPorNomeDesc();
                break;
            case "padrao":
            default:
                produtos = produtoService.findAll(); // Padrão: sem ordenação
                break;
        }

        model.addAttribute("produtos", produtos);
    
        return "fragments/produtos :: produtosFiltrados"; // Retorna a lista de produtos
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

    // Filtrar produtos por categoria
    @GetMapping("/produtos/filtrar")
    public String filtrarProdutos(@RequestParam("categoria") String categoria, Model model) {
        List<Produto> produtos;

        if (categoria.isEmpty()) {
            // Caso a categoria esteja vazia, retorna todos os produtos
            produtos = (List<Produto>) repo.findAll();
        } else {
            // Filtra os produtos pela categoria
            produtos = ((List<Produto>) repo.findAll()).stream()
                .filter(produto -> produto.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
        }

        model.addAttribute("produtos", produtos);
        // Retorna um fragmento de HTML para ser atualizado via AJAX
        return "fragments/produtos :: produtosFiltrados";
    }

    @GetMapping("/produtos/buscar")
    public String buscarProdutos(@RequestParam String nome, Model model) {
        List<Produto> produtosFiltrados = produtoService.buscarPorNome(nome);
        model.addAttribute("produtos", produtosFiltrados);
        return "fragments/produtos :: produtosFiltrados"; // Certifique-se de que o nome do fragmento está correto
    }
}
