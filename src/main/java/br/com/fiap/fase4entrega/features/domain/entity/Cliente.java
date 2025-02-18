package br.com.fiap.fase4entrega.features.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Cliente {
    private String id;
    private String nome;

    public Cliente(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
}