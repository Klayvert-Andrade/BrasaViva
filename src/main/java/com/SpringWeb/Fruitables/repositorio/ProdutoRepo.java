package com.SpringWeb.Fruitables.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringWeb.Fruitables.models.Produto;

@Repository
public interface ProdutoRepo extends JpaRepository<Produto, Integer> {
    // Método customizado para buscar produtos por categoria
    List<Produto> findByCategoria(String categoria);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}

