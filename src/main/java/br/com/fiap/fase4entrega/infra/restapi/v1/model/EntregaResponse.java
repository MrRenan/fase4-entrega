package br.com.fiap.fase4entrega.infra.restapi.v1.model;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record EntregaResponse(

        String entregaId,
        String pedidoId,
        Status status,
        LocalDate dataPrevistaEntrega,
        LocalDate dataEntrega,
        EnderecoResponse endereco,
        String codigoRastreio,
        LocalDateTime ultimaAtualizacao
){
    @Builder
    public record EnderecoResponse(
            String rua,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep
    ){}
}
