package br.com.fiap.fase4entrega.features.adapter.out;

import br.com.fiap.fase4entrega.features.adapter.out.mapper.EntregaMapper;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.features.domain.entity.EntregaStub;
import br.com.fiap.fase4entrega.infra.mongodb.document.EntregaDocument;
import br.com.fiap.fase4entrega.infra.mongodb.repository.EntregaRepository;
import br.com.fiap.fase4entrega.infra.restclient.cliente.ClienteClient;
import br.com.fiap.fase4entrega.infra.restclient.cliente.entity.ClienteEntity;
import br.com.fiap.fase4entrega.infra.restclient.pedido.PedidoClient;
import br.com.fiap.fase4entrega.infra.restclient.pedido.entity.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EntregaAdapterTest {

    @InjectMocks
    private EntregaAdapter adapter;

    @Mock
    private PedidoClient pedidoClient;

    @Mock
    private ClienteClient clienteClient;

    @Mock
    private EntregaRepository entregaRepository;

    @Mock
    private EntregaMapper mapper;

    private Entrega entrega;
    private EntregaDocument entregaDocument;

    @BeforeEach
    void setUp() {
        entrega = EntregaStub.novo().build();
        entregaDocument = EntregaDocument.builder()
                .id(entrega.getEntregaId())
                .pedidoId(entrega.getPedidoId())
                .status(entrega.getStatus().name())
                .dataPrevistaEntrega(entrega.getDataPrevistaEntrega())
                .dataEntrega(entrega.getDataEntrega())
                .endereco(EntregaDocument.EnderecoDocument.builder()
                        .rua(entrega.getEndereco().getRua())
                        .numero(entrega.getEndereco().getNumero())
                        .bairro(entrega.getEndereco().getBairro())
                        .cidade(entrega.getEndereco().getCidade())
                        .estado(entrega.getEndereco().getEstado())
                        .cep(entrega.getEndereco().getCep())
                        .build())
                .codigoRastreio(entrega.getCodigoRastreio())
                .ultimaAtualizacao(entrega.getUltimaAtualizacao())
                .latitude(entrega.getLatitude())
                .longitude(entrega.getLongitude())
                .build();
    }

    @Test
    void criarEntrega() {
        // ASSETS
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(entrega.getPedidoId());
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(entrega.getPedidoId());
        pedidoEntity.setCliente(clienteEntity);

        given(pedidoClient.obterPedido(any())).willReturn(pedidoEntity);
        given(clienteClient.obterCliente(pedidoEntity.getCliente().getId())).willReturn(clienteEntity);
        given(mapper.paraEndereco(clienteEntity.getEndereco())).willReturn(entrega.getEndereco());
        given(mapper.paraEntregaDocument(any())).willReturn(entregaDocument);
        given(entregaRepository.save(any(EntregaDocument.class))).willReturn(entregaDocument);
        given(mapper.paraEntrega(any(EntregaDocument.class))).willReturn(entrega);

        // ACTION
        Entrega resultado = adapter.criarEntrega(entrega);

        // ASSERTIONS
        assertThat(resultado).isNotNull();
        verify(entregaRepository).save(any(EntregaDocument.class));
    }

    @Test
    void obterEntregas() {
        // ASSETS
        given(entregaRepository.findAll()).willReturn(List.of(entregaDocument));
        given(mapper.paraEntrega(any(EntregaDocument.class))).willReturn(entrega);

        // ACTION
        List<Entrega> entregas = adapter.obterEntregas();

        // ASSERTIONS
        assertThat(entregas).isNotEmpty();
        verify(entregaRepository).findAll();
    }

    @Test
    void obterEntregaPorId() {
        // ASSETS
        given(entregaRepository.findById(entrega.getEntregaId())).willReturn(Optional.of(entregaDocument));
        given(mapper.paraEntrega(any(EntregaDocument.class))).willReturn(entrega);

        // ACTION
        Entrega resultado = adapter.obterEntregaPorId(entrega.getEntregaId());

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(entregaRepository).findById(entrega.getEntregaId());
    }

    @Test
    void atualizarLatitudeLongitude() {
        // ASSETS
        BigDecimal latitude = BigDecimal.valueOf(-23.55052);
        BigDecimal longitude = BigDecimal.valueOf(-46.633308);

        given(entregaRepository.findById(any())).willReturn(Optional.of(entregaDocument));
        given(entregaRepository.save(any())).willReturn(entregaDocument);
        given(mapper.paraEntrega(any())).willReturn(entrega);
        given(mapper.paraEntregaDocument(any())).willReturn(entregaDocument);

        // ACTION
        Entrega resultado = adapter.atualizarLatitudeLongitude(entrega.getEntregaId(), latitude, longitude);

        // ASSERTIONS
        assertThat(resultado.getLatitude()).isEqualTo(latitude);
        assertThat(resultado.getLongitude()).isEqualTo(longitude);
        verify(entregaRepository).save(any(EntregaDocument.class));
        verify(entregaRepository).findById(entrega.getEntregaId());
    }

    @Test
    void deletarEntrega() {
        // ASSETS
        given(entregaRepository.findById(any())).willReturn(Optional.of(entregaDocument));
        given(mapper.paraEntrega(any())).willReturn(entrega);
        given(mapper.paraEntregaDocument(any())).willReturn(entregaDocument);
        doNothing().when(entregaRepository).delete(entregaDocument);

        // ACTION
        adapter.deletarEntrega(entrega.getEntregaId());

        // ASSERTIONS
        verify(entregaRepository).delete(entregaDocument); // Verifica se o delete foi chamado com o entregaDocument correto
    }

}