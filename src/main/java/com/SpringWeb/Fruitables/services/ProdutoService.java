package com.SpringWeb.Fruitables.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
        return produtoRepo.findAll();
    }

    // Recupera produtos por categoria
    public List<Produto> findByCategoria(String categoria) {
        return produtoRepo.findByCategoria(categoria);
    }
}
