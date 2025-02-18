package br.com.fiap.fase4entrega.features.application.usecase;

import br.com.fiap.fase4entrega.features.EntregaService;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaUseCase {

    private final EntregaService service;

    public Entrega criarEntrega(Entrega entrega) {
        return service.criarEntrega(entrega);
    }

    public List<Entrega> obterEntregas() {
        return service.obterEntregas();
    }

    public Entrega cancelarEntrega(String id) {
        return service.cancelarEntrega(id);
    }

    public Entrega acompanharEntrega(String id) {
        return service.acompanharEntrega(id);
    }

    public Entrega atualizarEntrega(String id, Entrega entrega) {
        return service.atualizarEntrega(id, entrega);
    }

    public Entrega finalizarEntrega(String id) {
        return service.finalizarEntrega(id);
    }
}
