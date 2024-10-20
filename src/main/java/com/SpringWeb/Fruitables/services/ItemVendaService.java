package com.SpringWeb.Fruitables.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Administrador;
import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.models.ItemVenda;
import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.models.Venda;
import com.SpringWeb.Fruitables.repositorio.ItemVendaRepo;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepo itemVendaRepo;

    public void salvarItensVenda(Venda venda, Cliente cliente, Administrador administrador, List<Produto> produtos) {
        for (Produto produto : produtos) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);
            itemVenda.setProduto(produto);
            itemVenda.setCliente(cliente);
            itemVenda.setAdministrador(administrador);
            itemVenda.setQuantidade(produto.getQuantidadeEstoque());
            itemVenda.setPrecoUnitario(produto.getPreco());
            
            itemVendaRepo.save(itemVenda);
        }
    }
}
