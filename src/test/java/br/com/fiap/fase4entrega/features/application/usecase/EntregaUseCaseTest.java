package br.com.fiap.fase4entrega.features.application.usecase;

import br.com.fiap.fase4entrega.features.EntregaService;
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
class EntregaUseCaseTest {

    @InjectMocks
    private EntregaUseCase useCase;

    @Mock
    private EntregaService service;

    private Entrega entrega;

    @BeforeEach
    void setUp() {
        entrega = EntregaStub.novo().build();
    }

    @Test
    void criarEntrega() {
        // ASSETS
        given(service.criarEntrega(entrega)).willReturn(entrega);

        // ACTION
        Entrega resultado = useCase.criarEntrega(entrega);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(service).criarEntrega(entrega);
    }

    @Test
    void obterEntregas() {
        // ASSETS
        given(service.obterEntregas()).willReturn(List.of(entrega));

        // ACTION
        List<Entrega> resultado = useCase.obterEntregas();

        // ASSERTIONS
        assertThat(resultado).isNotEmpty().contains(entrega);
        verify(service).obterEntregas();
    }

    @Test
    void cancelarEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        given(service.cancelarEntrega(id)).willReturn(entrega);

        // ACTION
        Entrega resultado = useCase.cancelarEntrega(id);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(service).cancelarEntrega(id);
    }

    @Test
    void acompanharEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        given(service.acompanharEntrega(id)).willReturn(entrega);

        // ACTION
        Entrega resultado = useCase.acompanharEntrega(id);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(service).acompanharEntrega(id);
    }

    @Test
    void atualizarEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        given(service.atualizarEntrega(id, entrega)).willReturn(entrega);

        // ACTION
        Entrega resultado = useCase.atualizarEntrega(id, entrega);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(service).atualizarEntrega(id, entrega);
    }

    @Test
    void finalizarEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        given(service.finalizarEntrega(id)).willReturn(entrega);

        // ACTION
        Entrega resultado = useCase.finalizarEntrega(id);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(service).finalizarEntrega(id);
    }

    @Test
    void deletarEntrega() {
        // ASSETS
        String id = entrega.getEntregaId();
        willDoNothing().given(service).deletarEntrega(id);

        // ACTION
        useCase.deletarEntrega(id);

        // ASSERTIONS
        verify(service).deletarEntrega(id);
    }

    @Test
    void atualizarLatitudeLongitude() {
        // ASSETS
        String id = entrega.getEntregaId();
        BigDecimal latitude = BigDecimal.valueOf(-23.55052);
        BigDecimal longitude = BigDecimal.valueOf(-46.633308);

        given(service.atualizarLatitudeLongitude(id, latitude, longitude)).willReturn(entrega);

        // ACTION
        Entrega resultado = useCase.atualizarLatitudeLongitude(id, latitude, longitude);

        // ASSERTIONS
        assertThat(resultado).isEqualTo(entrega);
        verify(service).atualizarLatitudeLongitude(id, latitude, longitude);
    }

}