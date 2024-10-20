package com.SpringWeb.Fruitables.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "administrador_id", nullable = false)
    private Administrador administrador;

    @Column(name = "valor_total", nullable = false)
    private double valorTotal;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;

    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemVenda> itens;

    // Construtor padrão
    public Venda() {
        this.dataVenda = LocalDateTime.now();
    }

    // Construtor com parâmetros
    public Venda(Cliente cliente, Administrador administrador, double valorTotal, String metodoPagamento) {
        this.cliente = cliente;
        this.administrador = administrador;
        this.valorTotal = valorTotal;
        this.metodoPagamento = metodoPagamento;
        this.dataVenda = LocalDateTime.now();  // Define a data da venda como a data atual
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
    
    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
}
