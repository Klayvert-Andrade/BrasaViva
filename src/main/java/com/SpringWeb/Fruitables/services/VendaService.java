package com.SpringWeb.Fruitables.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.models.ItemCarrinho;
import com.SpringWeb.Fruitables.models.Pagamento;
import com.SpringWeb.Fruitables.models.Venda;
import com.SpringWeb.Fruitables.repositorio.VendaRepo;

@Service
public class VendaService {

    @Autowired
    private VendaRepo vendaRepo;

    public Venda finalizarVenda(Cliente cliente, List<ItemCarrinho> itensCarrinho, double valorTotal, Pagamento pagamento) {
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setItens(itensCarrinho);
        venda.setValorTotal(valorTotal);
        venda.setPagamento(pagamento);

        return vendaRepo.save(venda);
    }
}
