package br.com.fiap.fase4entrega.infra.restclient.produto.entity;

import java.math.BigDecimal;

public class ProdutoEntity {
    private String id;
    private String nomeProduto;
    private BigDecimal preco;
    private int quantidade;

    public ProdutoEntity() {}

    public ProdutoEntity(String id, String nomeProduto, BigDecimal preco, int quantidade) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
