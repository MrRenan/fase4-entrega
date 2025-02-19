package br.com.fiap.fase4entrega.features.domain.exception;

public class EntregaNaoEncontradaException extends RuntimeException{
    public EntregaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
