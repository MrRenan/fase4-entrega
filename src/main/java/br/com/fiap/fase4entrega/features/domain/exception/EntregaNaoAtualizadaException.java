package br.com.fiap.fase4entrega.features.domain.exception;

public class EntregaNaoAtualizadaException extends RuntimeException {
    public EntregaNaoAtualizadaException(String mensagem) {
        super(mensagem);
    }
}
