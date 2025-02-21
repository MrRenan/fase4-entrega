package br.com.fiap.fase4entrega.features.port;

import br.com.fiap.fase4entrega.features.domain.entity.Entrega;

import java.util.List;

public interface EntregaPort {

    Entrega criarEntrega(Entrega entrega);

    List<Entrega> obterEntregas();

    Entrega obterEntregaPorId(String id); // Adicionado

    Entrega atualizarEntrega(Entrega entrega); // Adicionado

    Entrega cancelarEntrega(String id);

    Entrega acompanharEntrega(String id);

    Entrega finalizarEntrega(String id);

    void deletarEntrega(String id);
}