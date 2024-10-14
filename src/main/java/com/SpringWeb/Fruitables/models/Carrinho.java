package com.SpringWeb.Fruitables.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itens = new ArrayList<>();

    public Carrinho() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto().equals(produto)) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }
        itens.add(new ItemCarrinho(produto, this, quantidade));
    }

    public void removerProduto(Produto produto) {
        itens.removeIf(item -> item.getProduto().equals(produto));
    }

    public void atualizarQuantidade(Produto produto, int quantidade) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto().equals(produto)) {
                if (quantidade > 0) {
                    item.setQuantidade(quantidade);
                } else {
                    removerProduto(produto);
                }
                return;
            }
        }
    }

    public void limpar() {
        itens.clear();
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemCarrinho::getTotal)
                .sum();
    }

    public int contarItens() {
        int quantidadeTotal = 0;
        for (ItemCarrinho item : itens) {
            quantidadeTotal += item.getQuantidade();
        }
        return quantidadeTotal;
    }
}
