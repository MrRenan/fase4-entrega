package br.com.fiap.fase4entrega.infra.rabbitmq;

import br.com.fiap.fase4entrega.features.adapter.out.EntregaAdapter;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class PedidoConsumer {

    private final EntregaAdapter adapter;

    @Bean
    public Consumer<String> pedidoPago() {
        return pedidoId -> {
            System.out.println("Pedido recebido para entrega: " + pedidoId);
            Entrega entrega = new Entrega(pedidoId);
            adapter.criarEntrega(entrega);
        };
    }
}
