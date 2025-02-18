package br.com.fiap.fase4entrega.infra.restclient.pedido;

import br.com.fiap.fase4entrega.infra.restclient.pedido.entity.PedidoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pedido", url = "http://localhost:8082/v1/pedido")
public interface PedidoClient {

    @GetMapping("/{id}")
    PedidoEntity obterPedido(@PathVariable String id);

}