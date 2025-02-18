package br.com.fiap.fase4entrega.infra.restapi.v1.model;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record EntregaRequest(
        @Nullable String id,
        String pedidoId
        ) {
}