package br.com.fiap.fase4entrega.infra.rabbitmq;

import br.com.fiap.fase4entrega.features.adapter.out.EntregaAdapter;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class PedidoConsumer {

    private final StreamBridge streamBridge;
    private final EntregaAdapter adapter;

    @Bean
    public Consumer<String> pedidoPago() {
        return pedidoId -> {
            // Lógica para processar o pedido pago
            System.out.println("Pedido recebido para entrega: " + pedidoId);
            // Aqui você pode chamar o método que inicia o processo de entrega
            Entrega entrega = new Entrega(pedidoId);
            adapter.criarEntrega(entrega);
        };
    }
}
