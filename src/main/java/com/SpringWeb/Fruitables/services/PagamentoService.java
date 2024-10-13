package com.SpringWeb.Fruitables.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Pagamento;
import com.SpringWeb.Fruitables.repositorio.PagamentoRepo;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepo pagamentoRepo;

    public Pagamento processarPagamento(String metodoPagamento, double valorTotal) {
        Pagamento pagamento = new Pagamento();
        pagamento.setMetodoPagamento(metodoPagamento);
        pagamento.setValorTotal(valorTotal);
        pagamento.setDataPagamento(LocalDateTime.now());

        return pagamentoRepo.save(pagamento);
    }
}
