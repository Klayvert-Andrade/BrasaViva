package com.SpringWeb.Fruitables.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.repositorio.ProdutoRepo;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepo produtoRepo;

    // Adiciona um novo produto
    @Transactional
    public Produto adicionarProduto(Produto produto) {
        return produtoRepo.save(produto);
    }

    // Atualiza um produto existente
    @Transactional
    public Produto atualizarProduto(int produtoId, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepo.findById(produtoId);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setCategoria(produtoAtualizado.getCategoria());
            produto.setImagemUrl(produtoAtualizado.getImagemUrl());
            return produtoRepo.save(produto);
        } else {
            throw new IllegalArgumentException("Produto não encontrado: " + produtoId);
        }
    }

    // Remove um produto
    @Transactional
    public void removerProduto(int produtoId) {
        if (produtoRepo.existsById(produtoId)) {
            produtoRepo.deleteById(produtoId);
        } else {
            throw new IllegalArgumentException("Produto não encontrado: " + produtoId);
        }
    }

    // Recupera um produto pelo ID
    public Produto obterProdutoPorId(int produtoId) {
        return produtoRepo.findById(produtoId).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produtoId));
    }

    // Recupera todos os produtos
    public List<Produto> findAll() {
        Iterable<Produto> produtos = produtoRepo.findAll();
        return StreamSupport.stream(produtos.spliterator(), false)
                            .collect(Collectors.toList());
    }

    // Recupera produtos por categoria
    public List<Produto> findByCategoria(String categoria) {
        return produtoRepo.findByCategoria(categoria);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepo.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> listarProdutosOrdenadosPorPrecoAsc() {
        return produtoRepo.findAll(Sort.by(Sort.Direction.ASC, "preco"));
    }

    public List<Produto> listarProdutosOrdenadosPorPrecoDesc() {
        return produtoRepo.findAll(Sort.by(Sort.Direction.DESC, "preco"));
    }
    
    public List<Produto> listarProdutosOrdenadosPorNomeAsc() {
        return produtoRepo.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }
    
    public List<Produto> listarProdutosOrdenadosPorNomeDesc() {
        return produtoRepo.findAll(Sort.by(Sort.Direction.DESC, "nome"));
    }

    
}
