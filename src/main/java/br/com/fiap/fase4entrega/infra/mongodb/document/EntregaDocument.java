package br.com.fiap.fase4entrega.infra.mongodb.document;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Document(collection = "entrega")
public record EntregaDocument (

        String id,
        String pedidoId,
        String status,
        LocalDate dataPrevistaEntrega,
        LocalDate dataEntrega,
        EnderecoDocument endereco,
        String codigoRastreio,
        LocalDateTime ultimaAtualizacao,
        BigDecimal latitude,
        BigDecimal longitude
){
    @Builder
    public record EnderecoDocument(
            String rua,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep
    ){}
}
