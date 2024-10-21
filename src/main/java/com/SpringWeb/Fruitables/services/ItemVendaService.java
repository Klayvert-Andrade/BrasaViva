package com.SpringWeb.Fruitables.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.ItemCarrinho;
import com.SpringWeb.Fruitables.models.ItemVenda;
import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.models.Venda;
import com.SpringWeb.Fruitables.repositorio.ItemVendaRepo;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepo itemVendaRepo;

    public void salvarItensVenda(Venda venda, List<ItemCarrinho> itens) {
    for (ItemCarrinho item : itens) {
        Produto produto = item.getProduto();
        ItemVenda itemVenda = new ItemVenda();
        
        // Setar os dados do item da venda
        itemVenda.setVenda(venda);
        itemVenda.setProduto(produto);
        itemVenda.setQuantidade(item.getQuantidade());  // Quantidade vendida
        itemVenda.setPrecoUnitario(produto.getPreco());

        // Salvar o item da venda
        itemVendaRepo.save(itemVenda);
    }
}
}
