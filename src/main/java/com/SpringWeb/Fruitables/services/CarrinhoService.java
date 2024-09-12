package com.SpringWeb.Fruitables.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Carrinho;
import com.SpringWeb.Fruitables.models.ItemCarrinho;
import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.repositorio.CarrinhoRepo;
import com.SpringWeb.Fruitables.repositorio.ProdutoRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class CarrinhoService {

    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private CarrinhoRepo carrinhoRepo;

    @Autowired
    private HttpSession session;

    public void adicionarProdutoAoCarrinho(Integer produtoId, int quantidade) {
        Produto produto = produtoRepo.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Carrinho carrinho = getCarrinhoFromSession();
        carrinho.adicionarProduto(produto, quantidade);

        carrinhoRepo.save(carrinho);
    }

    // Remove um produto do carrinho
    public void removerProdutoDoCarrinho(int produtoId) {
        Produto produto = produtoRepo.findById(produtoId).orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + produtoId));
        Carrinho carrinho = getCarrinhoFromSession();
        carrinho.removerProduto(produto);
        session.setAttribute("carrinho", carrinho);
    }

    // Atualiza a quantidade de um produto no carrinho
    public void atualizarQuantidadeNoCarrinho(int produtoId, int quantidade) {
        Produto produto = produtoRepo.findById(produtoId).orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + produtoId));
        Carrinho carrinho = getCarrinhoFromSession();
        carrinho.atualizarQuantidade(produto, quantidade);
        session.setAttribute("carrinho", carrinho);
    }

    // Limpa todos os itens do carrinho
    public void limparCarrinho() {
        Carrinho carrinho = getCarrinhoFromSession();
        carrinho.limpar();
        session.setAttribute("carrinho", carrinho);
    }

    // Retorna o carrinho da sessão
    public Carrinho getCarrinhoFromSession() {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new Carrinho();
            session.setAttribute("carrinho", carrinho);
        }
        return carrinho;
    }

    // Calcula o valor total do carrinho
    public double calcularTotal() {
        Carrinho carrinho = getCarrinhoFromSession();
        return carrinho.calcularTotal();
    }

    public int contarItensNoCarrinho() {
        Carrinho carrinho = getCarrinhoFromSession();
        return carrinho.getItens().stream()
                .mapToInt(ItemCarrinho::getQuantidade)
                .sum();
    }
}
