package br.com.fiap.fase4entrega.features.adapter.out;

import br.com.fiap.fase4entrega.features.adapter.out.mapper.EntregaMapper;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.features.domain.exception.EntregaNaoAtualizadaException;
import br.com.fiap.fase4entrega.features.domain.exception.EntregaNaoEncontradaException;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.fiap.fase4entrega.infra.restapi.v1.model.Status.*;
import static java.time.LocalDate.now;

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
        novoEntrega.setStatus(CRIADO);
        novoEntrega.setDataPrevistaEntrega(now().plusDays(5));
        novoEntrega.setDataEntrega(null);
        novoEntrega.setCodigoRastreio(gerarCodigoRastreio());
        novoEntrega.setUltimaAtualizacao(LocalDateTime.now());
        novoEntrega.setEndereco(mapper.paraEndereco(clienteEntity.getEndereco()));
        novoEntrega.setLatitude(BigDecimal.valueOf(-23.6916828));
        novoEntrega.setLongitude(BigDecimal.valueOf(-46.4482505));

        EntregaDocument entregaDocument = mapper.paraEntregaDocument(novoEntrega);

        return criarEntrega(entregaDocument);
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
                .orElseThrow(() -> new EntregaNaoEncontradaException("Entrega não encontrada."));
    }

    @Override
    public Entrega atualizarEntrega(Entrega entrega) {
        Entrega entregaExistente = obterEntrega(entrega.getEntregaId());
        try {
            return criarEntrega(mapper.paraEntregaDocument(entregaExistente));
        } catch (Exception e) {
            throw new EntregaNaoAtualizadaException("Não foi possivel atualizar a entrega.");
        }

    }

    @Override
    public Entrega cancelarEntrega(String id) {
        return atualizarStatusEntrega(id, CANCELADO);
    }

    @Override
    public Entrega acompanharEntrega(String id) {
        return obterEntrega(id);

    }

    @Override
    public Entrega finalizarEntrega(String id) {
        return atualizarStatusEntrega(id, ENTREGUE);
    }

    @Override
    public void deletarEntrega(String id) {
        Entrega entrega = obterEntrega(id);
        entregaRepository.delete(mapper.paraEntregaDocument(entrega));
    }

    @Override
    public Entrega atualizarLatitudeLongitude(String id, BigDecimal latitude, BigDecimal longitude) {
        Entrega entrega = obterEntrega(id);
        entrega.setLatitude(latitude);
        entrega.setLongitude(longitude);
        EntregaDocument atualizado = entregaRepository.save(mapper.paraEntregaDocument(entrega));
        return mapper.paraEntrega(atualizado);
    }

    private ClienteEntity obterCliente(PedidoEntity pedidoEntity) {
        return clienteClient.obterCliente(pedidoEntity.getCliente().getId());
    }

    private PedidoEntity obterPedido(Entrega entrega) {
        return pedidoClient.obterPedido(entrega.getPedidoId());
    }

    private Entrega criarEntrega(EntregaDocument entregaDocument) {
        EntregaDocument entregaCriada = entregaRepository.save(entregaDocument);
        return mapper.paraEntrega(entregaCriada);
    }

    private Entrega obterEntrega(String id) {
        return entregaRepository.findById(id)
                .map(mapper::paraEntrega)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada"));
    }

    private Entrega atualizarStatusEntrega(String id, Status status) {
        Entrega entrega = obterEntrega(id);
        entrega.setStatus(status);
        if(ENTREGUE.equals(status)) {
            entrega.setDataEntrega(now());
        }
        EntregaDocument atualizado = entregaRepository.save(mapper.paraEntregaDocument(entrega));
        return mapper.paraEntrega(atualizado);
    }

    private String gerarCodigoRastreio() {
        return "TRACK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}