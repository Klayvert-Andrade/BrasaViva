package com.SpringWeb.Fruitables.services;

import java.time.LocalDateTime;
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

    public void finalizarVenda(Cliente cliente, Administrador administrador, List<ItemCarrinho> itens, double valorTotal, String metodoPagamento) {
        Venda novaVenda = new Venda();
        novaVenda.setCliente(cliente);
        novaVenda.setAdministrador(administrador);
        novaVenda.setValorTotal(valorTotal);
        novaVenda.setMetodoPagamento(metodoPagamento);

        novaVenda.setDataVenda(LocalDateTime.now());
        // Persistir a nova venda no banco de dados
        vendaRepo.save(novaVenda);
    }
}
