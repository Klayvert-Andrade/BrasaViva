package com.SpringWeb.Fruitables.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringWeb.Fruitables.models.Carrinho;
import com.SpringWeb.Fruitables.models.ItemCarrinho;
import com.SpringWeb.Fruitables.models.Produto;
import com.SpringWeb.Fruitables.repositorio.ProdutoRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class CarrinhoService {

    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private HttpSession session;

    public void adicionarProdutoAoCarrinho(Integer produtoId, int quantidade) {
        Produto produto = produtoRepo.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Carrinho carrinho = getCarrinhoFromSession();

        // Verificar se o produto já está no carrinho
        ItemCarrinho itemExistente = carrinho.getItens().stream()
            .filter(item -> item.getProduto().getId() == produtoId)
            .findFirst()
            .orElse(null);

        if (itemExistente != null) {
            // Se o produto já está no carrinho, aumentar a quantidade
            itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
        } else {
            // Se não está, adicionar novo item ao carrinho
            carrinho.adicionarProduto(produto, quantidade);
        }

        // Atualiza o carrinho na sessão
        session.setAttribute("carrinho", carrinho);
        // carrinhoRepo.save(carrinho); // Descomente se você estiver usando um repositório para persistir o carrinho
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
        Carrinho carrinho = getCarrinhoFromSession(); // Obter o carrinho da sessão
        ItemCarrinho item = carrinho.getItens().stream()
            .filter(i -> i.getProduto().getId() == produtoId)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado no carrinho"));
    
        if (quantidade <= 0) {
            // Se a quantidade for menor ou igual a zero, remova o item
            carrinho.getItens().remove(item);
        } else {
            item.setQuantidade(quantidade); // Atualiza a quantidade do item
        }
    
        session.setAttribute("carrinho", carrinho); // Atualiza o carrinho na sessão
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
