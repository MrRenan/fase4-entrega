package br.com.fiap.fase4entrega.features;

import br.com.fiap.fase4entrega.features.adapter.out.EntregaAdapter;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.features.domain.entity.EntregaStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EntregaServiceTest {

    @InjectMocks
    private EntregaService service;

    @Mock
    private EntregaAdapter adapter;

    private Entrega entrega;

    @BeforeEach
    void setUp() {
        entrega = EntregaStub.novo().build();
    }

    @Test
    void criarEntrega() {
        // ASSETS
        given(adapter.criarEntrega(entrega)).willReturn(entrega);

        // ACTION
        Entrega resultado = service.criarEntrega(entrega);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(adapter).criarEntrega(entrega);
    }

    @Test
    void obterEntregas() {
        // ASSETS
        given(adapter.obterEntregas()).willReturn(List.of(entrega));

        // ACTION
        List<Entrega> resultado = service.obterEntregas();

        // ASSERTIONS
        assertThat(resultado).isNotEmpty().contains(entrega);
        verify(adapter).obterEntregas();
    }

    @Test
    void atualizarEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        given(adapter.atualizarEntrega(entrega)).willReturn(entrega);

        // ACTION
        Entrega resultado = service.atualizarEntrega(id, entrega);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(adapter).atualizarEntrega(entrega);
    }

    @Test
    void cancelarEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        given(adapter.cancelarEntrega(id)).willReturn(entrega);

        // ACTION
        Entrega resultado = service.cancelarEntrega(id);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(adapter).cancelarEntrega(id);
    }

    @Test
    void acompanharEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        given(adapter.acompanharEntrega(id)).willReturn(entrega);

        // ACTION
        Entrega resultado = service.acompanharEntrega(id);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(adapter).acompanharEntrega(id);
    }

    @Test
    void finalizarEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        given(adapter.finalizarEntrega(id)).willReturn(entrega);

        // ACTION
        Entrega resultado = service.finalizarEntrega(id);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(adapter).finalizarEntrega(id);
    }

    @Test
    void deletarEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        willDoNothing().given(adapter).deletarEntrega(id);

        // ACTION
        service.deletarEntrega(id);

        // ASSERTIONS
        verify(adapter).deletarEntrega(id);
    }

    @Test
    void atualizarLatitudeLongitude() {
        // ASSETS
        String id = entrega.getEntregaId();
        BigDecimal latitude = BigDecimal.valueOf(-23.55052);
        BigDecimal longitude = BigDecimal.valueOf(-46.633308);

        given(adapter.atualizarLatitudeLongitude(id, latitude, longitude)).willReturn(entrega);

        // ACTION
        Entrega resultado = service.atualizarLatitudeLongitude(id, latitude, longitude);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(adapter).atualizarLatitudeLongitude(id, latitude, longitude);
    }

}