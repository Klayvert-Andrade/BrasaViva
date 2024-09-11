package com.SpringWeb.Fruitables.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SpringWeb.Fruitables.models.Produto;

public interface ProdutosRepo extends JpaRepository<Produto, Integer> {

    // Consulta para encontrar um produto por nome e categoria
    @Query(value = "SELECT * FROM produtos WHERE nome = :nome AND categoria = :categoria", nativeQuery = true)
    Produto findByNomeAndCategoria(@Param("nome") String nome, @Param("categoria") String categoria);

    List<Produto> findByCategoria(String categoria);
}

