package com.SpringWeb.Fruitables.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Administrador;
import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.models.ItemCarrinho;
import com.SpringWeb.Fruitables.models.Venda;
import com.SpringWeb.Fruitables.repositorio.VendaRepo;

@Service
public class VendaService {

    @Autowired
    private VendaRepo vendaRepo;

    public Venda finalizarVenda(Cliente cliente, Administrador administrador, List<ItemCarrinho> itensCarrinho, double valorTotal, String metodoPagamento) {
        // Cria a instância de Venda com os dados fornecidos
        Venda venda = new Venda(cliente, administrador, valorTotal, metodoPagamento, itensCarrinho);
        
        // Associa cada item do carrinho à venda antes de salvar
        for (ItemCarrinho item : itensCarrinho) {
            item.setVenda(venda); // Associa o item à venda
        }
    
        // Salva a venda no banco de dados (isso também salvará os itens, devido à CascadeType.ALL)
        return vendaRepo.save(venda);
    }
}
