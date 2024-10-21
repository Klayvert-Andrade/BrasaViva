package com.SpringWeb.Fruitables.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.models.Venda;

@Repository
public interface VendaRepo extends JpaRepository<Venda, Integer> {
    List<Venda> findByCliente(Cliente cliente);
}