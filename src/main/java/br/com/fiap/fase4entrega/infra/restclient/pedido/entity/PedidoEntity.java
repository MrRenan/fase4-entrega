package br.com.fiap.fase4entrega.infra.restclient.pedido.entity;

import br.com.fiap.fase4entrega.infra.restclient.cliente.entity.ClienteEntity;
import br.com.fiap.fase4entrega.infra.restclient.produto.entity.ProdutoEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Data
public class PedidoEntity {

    private String id;
    private ClienteEntity cliente;
    private List<ProdutoEntity> produtos;
    private LocalDate dataCriacao;
    private String status;
    private BigDecimal total;

}
