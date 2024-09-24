package com.SpringWeb.Fruitables.repositorio;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SpringWeb.Fruitables.models.Produto;

@Repository
public interface ProdutoRepo extends CrudRepository<Produto, Integer> {
    List<Produto> findByCategoria(String categoria);
}

