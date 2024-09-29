package com.SpringWeb.Fruitables.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "metodo_pagamento", length = 50, nullable = false)
    private String metodoPagamento; // Pix, Cartão, Boleto

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @Column(name = "status_pagamento", length = 50, nullable = false)
    private String status; // Pendente, Completo, Falha

    @OneToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho; // Relacionamento com o Carrinho

    // Construtores
    public Pagamento() {}

    public Pagamento(String metodoPagamento, Double valorTotal, Carrinho carrinho) {
        this.metodoPagamento = metodoPagamento;
        this.valorTotal = valorTotal;
        this.dataPagamento = LocalDateTime.now();
        this.status = "Pendente"; // Inicialmente como "Pendente"
        this.carrinho = carrinho;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
