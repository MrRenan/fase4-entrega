package br.com.fiap.fase4entrega.features.adapter.out;

import br.com.fiap.fase4entrega.features.adapter.out.mapper.EntregaMapper;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.features.port.EntregaPort;
import br.com.fiap.fase4entrega.infra.mongodb.document.EntregaDocument;
import br.com.fiap.fase4entrega.infra.mongodb.repository.EntregaRepository;
import br.com.fiap.fase4entrega.infra.restapi.v1.model.Status;
import br.com.fiap.fase4entrega.infra.restclient.cliente.ClienteClient;
import br.com.fiap.fase4entrega.infra.restclient.cliente.entity.ClienteEntity;
import br.com.fiap.fase4entrega.infra.restclient.pedido.PedidoClient;
import br.com.fiap.fase4entrega.infra.restclient.pedido.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntregaAdapter implements EntregaPort {

    private final PedidoClient pedidoClient;
    private final ClienteClient clienteClient;
    private final EntregaRepository entregaRepository;
    private final EntregaMapper mapper;

    @Override
    public Entrega criarEntrega(Entrega entrega) {
        Entrega novoEntrega = new Entrega();

        PedidoEntity pedidoEntity = obterPedido(entrega);
        ClienteEntity clienteEntity = obterCliente(pedidoEntity);

        novoEntrega.setPedidoId(pedidoEntity.getId());
        novoEntrega.setStatus(Status.CRIADO);
        novoEntrega.setDataPrevistaEntrega(LocalDate.now().plusDays(5));
        novoEntrega.setDataEntrega(null);
        novoEntrega.setCodigoRastreio(UUID.randomUUID().toString());
        novoEntrega.setUltimaAtualizacao(LocalDateTime.now());
        novoEntrega.setEndereco(mapper.paraEndereco(clienteEntity.getEndereco()));

        EntregaDocument entregaDocument = mapper.paraEntregaDocument(novoEntrega);

        EntregaDocument entregaCriada = entregaRepository.save(entregaDocument);

        return mapper.paraEntrega(entregaCriada);
    }

    private ClienteEntity obterCliente(PedidoEntity pedidoEntity) {
        return clienteClient.obterCliente(pedidoEntity.getCliente().getId());
    }

    private PedidoEntity obterPedido(Entrega entrega) {
        return pedidoClient.obterPedido(entrega.getPedidoId());
    }

    @Override
    public List<Entrega> obterEntregas() {
        return entregaRepository.findAll()
                .stream()
                .map(mapper::paraEntrega)
                .collect(Collectors.toList());
    }

    @Override
    public Entrega obterEntregaPorId(String id) {
        return entregaRepository.findById(id)
                .map(mapper::paraEntrega)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada."));
    }

    @Override
    public Entrega atualizarEntrega(Entrega entrega) {
        return new Entrega();
    }

    @Override
    public Entrega cancelarEntrega(String id) {
        return null;
    }

    @Override
    public Entrega acompanharEntrega(String id) {
        return entregaRepository.findById(id)
                .map(mapper::paraEntrega)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada."));

    }

    @Override
    public Entrega finalizarEntrega(String id) {
        return null;
    }

}