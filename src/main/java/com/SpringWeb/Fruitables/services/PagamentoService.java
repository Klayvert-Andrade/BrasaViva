package com.SpringWeb.Fruitables.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Pagamento;
import com.SpringWeb.Fruitables.repositorio.PagamentoRepo;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepo pagamentoRepo;

    public void salvarPagamento(Pagamento pagamento) {
        pagamentoRepo.save(pagamento);
    }
}
