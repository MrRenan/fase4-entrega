package br.com.fiap.fase4entrega.features;

import br.com.fiap.fase4entrega.features.adapter.out.EntregaAdapter;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaAdapter adapter;

    public Entrega criarEntrega(Entrega entrega) {
        return adapter.criarEntrega(entrega);
    }

    public List<Entrega> obterEntregas() {
        return adapter.obterEntregas();
    }

    public Entrega atualizarEntrega(String id, Entrega entrega) {
        return adapter.atualizarEntrega(entrega);
    }

    public Entrega cancelarEntrega(String id) {
        return adapter.cancelarEntrega(id);
    }

    public Entrega acompanharEntrega(String id) {
        return adapter.acompanharEntrega(id);
    }

    public Entrega finalizarEntrega(String id) {
        return adapter.finalizarEntrega(id);
    }

    public void deletarEntrega(String id) {
        adapter.deletarEntrega(id);
    }

    public Entrega atualizarLatitudeLongitude(String id, BigDecimal latitude, BigDecimal longitude) {
        return adapter.atualizarLatitudeLongitude(id, latitude, longitude);
    }
}
