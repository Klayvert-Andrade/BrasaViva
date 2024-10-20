package com.SpringWeb.Fruitables.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Administrador;
import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.models.ItemCarrinho;
import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.models.Venda;
import com.SpringWeb.Fruitables.repositorio.VendaRepo;

@Service
public class VendaService {

    @Autowired
    private VendaRepo vendaRepo;

    @Autowired
    private ItemVendaService itemVendaService;

    public void finalizarVenda(Cliente cliente, Administrador administrador, List<ItemCarrinho> itens, double valorTotal, String metodoPagamento) {
        Venda novaVenda = new Venda();
        novaVenda.setCliente(cliente);
        novaVenda.setAdministrador(administrador);
        novaVenda.setValorTotal(valorTotal);
        novaVenda.setMetodoPagamento(metodoPagamento);
        
        vendaRepo.save(novaVenda);

        List<Produto> produtosVendidos = itens.stream()
            .map(ItemCarrinho::getProduto)
            .collect(Collectors.toList());

        itemVendaService.salvarItensVenda(novaVenda, produtosVendidos);
    }

    public List<Venda> findAll() {
        return vendaRepo.findAll();
    }
}
