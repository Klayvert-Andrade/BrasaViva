package com.SpringWeb.Fruitables.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringWeb.Fruitables.models.ItemVenda;

@Repository
public interface ItemVendaRepo extends JpaRepository<ItemVenda, Integer> {
    
}
