package com.SpringWeb.Fruitables.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringWeb.Fruitables.models.Carrinho;

@Repository
public interface CarrinhoRepo extends JpaRepository<Carrinho, Integer> {
}
