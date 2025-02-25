package br.com.fiap.fase4entrega.features.domain.entity;

import br.com.fiap.fase4entrega.infra.restapi.v1.model.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Entrega {

    private String entregaId;
    private String pedidoId;
    private Status status;
    private LocalDate dataPrevistaEntrega;
    private LocalDate dataEntrega;
    private Endereco endereco;
    private String codigoRastreio;
    private LocalDateTime ultimaAtualizacao;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Entrega(String pedidoId) {
        this.pedidoId = pedidoId;
    }
}