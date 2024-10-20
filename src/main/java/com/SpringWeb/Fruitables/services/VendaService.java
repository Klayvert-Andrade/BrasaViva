package com.SpringWeb.Fruitables.services;

import java.util.List;

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

    @Autowired
    private ProdutoService produtoService;

    public void finalizarVenda(Cliente cliente, Administrador administrador, List<ItemCarrinho> itens, double valorTotal, String metodoPagamento) {
       
        Venda novaVenda = new Venda();
        novaVenda.setCliente(cliente);
        novaVenda.setAdministrador(administrador);
        novaVenda.setValorTotal(valorTotal);
        novaVenda.setMetodoPagamento(metodoPagamento);
        
        vendaRepo.save(novaVenda);

        for (ItemCarrinho item : itens) {
            Produto produto = item.getProduto();
            int quantidadeVendida = item.getQuantidade();
            
            // Verificar se o estoque é suficiente para a quantidade vendida
            if (produto.getQuantidadeEstoque() >= quantidadeVendida) {
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidadeVendida);
                produtoService.save(produto);
            } else {
                throw new IllegalArgumentException("Quantidade insuficiente em estoque para o produto: " + produto.getNome());
            }
        }

        itemVendaService.salvarItensVenda(novaVenda, itens);
    }

    public List<Venda> findAll() {
        return vendaRepo.findAll();
    }
}
