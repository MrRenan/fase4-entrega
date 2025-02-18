package br.com.fiap.fase4entrega.features.domain.entity;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Produto {
    private String id;
    private String nomeProduto;
    private BigDecimal preco;
    private int quantidade;

    public Produto(String id, String nomeProduto, BigDecimal preco, int quantidade) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getId() { return id; }
    public String getNomeProduto() { return nomeProduto; }
    public BigDecimal getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
}