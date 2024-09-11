package com.SpringWeb.Fruitables.models;

import java.util.HashMap;
import java.util.Map;

public class Carrinho {

    private Map<Integer, ItemCarrinho> itens = new HashMap<>();

    public void adicionarItem(Produto produto, int quantidade) {
        ItemCarrinho item = itens.get(produto.getId());
        if (item == null) {
            item = new ItemCarrinho(produto, quantidade);
        } else {
            item.setQuantidade(item.getQuantidade() + quantidade);
        }
        itens.put(produto.getId(), item);
    }

    public void removerItem(int produtoId) {
        itens.remove(produtoId);
    }

    public void atualizarQuantidade(int produtoId, int quantidade) {
        ItemCarrinho item = itens.get(produtoId);
        if (item != null) {
            item.setQuantidade(quantidade);
        }
    }

    public Map<Integer, ItemCarrinho> getItens() {
        return itens;
    }

    public double calcularTotal() {
        return itens.values().stream()
            .mapToDouble(ItemCarrinho::getTotal)
            .sum();
    }
}
