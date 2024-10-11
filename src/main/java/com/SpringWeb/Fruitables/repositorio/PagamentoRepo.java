package com.SpringWeb.Fruitables.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringWeb.Fruitables.models.Pagamento;

public interface PagamentoRepo extends JpaRepository<Pagamento, Integer> {
}