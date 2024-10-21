package com.SpringWeb.Fruitables.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Cliente;
import com.SpringWeb.Fruitables.models.Venda;
import com.SpringWeb.Fruitables.repositorio.ClientesRepo;
import com.SpringWeb.Fruitables.repositorio.VendaRepo;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepo clientesRepo; // Repositório para acesso ao banco de dados

    @Autowired
    private VendaRepo vendasRepo; // Repositório para acessar vendas do cliente

    // Método para obter as informações do cliente
    public Cliente getClienteById(int clienteId) {
        return clientesRepo.findById(clienteId).orElse(null);
    }

    // Método para obter todas as vendas de um cliente
    public List<Venda> getVendasByCliente(Cliente cliente) {
        return vendasRepo.findByCliente(cliente);
    }

    public Cliente findByEmail(String email) {
        return clientesRepo.findByEmail(email);
    }
}