package com.SpringWeb.Fruitables.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.SpringWeb.Fruitables.models.Estoque;

public interface EstoqueRepo extends CrudRepository<Estoque, Integer> {
    // Se necessário, adicione consultas customizadas aqui
}