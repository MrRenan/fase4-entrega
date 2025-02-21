package br.com.fiap.fase4entrega.features.adapter.in.v1;

import br.com.fiap.fase4entrega.features.adapter.in.v1.mapper.EntregaMapper;
import br.com.fiap.fase4entrega.features.application.usecase.EntregaUseCase;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.infra.restapi.v1.EntregaApi;
import br.com.fiap.fase4entrega.infra.restapi.v1.model.EntregaRequest;
import br.com.fiap.fase4entrega.infra.restapi.v1.model.EntregaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/entrega")
@RequiredArgsConstructor
public class EntregaController implements EntregaApi {

    private final EntregaMapper mapper;
    private final EntregaUseCase useCase;

    @Override
    @PostMapping
    public EntregaResponse criarEntrega(@RequestBody EntregaRequest entregaRequest) {
        Entrega entrega = useCase.criarEntrega(mapper.paraEntrega(entregaRequest));
        return mapper.paraEntregaResponse(entrega);
    }

    @Override
    @GetMapping
    public List<EntregaResponse> obterEntregas() {
        List<Entrega> entregas = useCase.obterEntregas();
        return entregas.stream().map(mapper::paraEntregaResponse).collect(Collectors.toList());
    }

    @Override
    @PutMapping("/{id}/cancelar")
    public EntregaResponse cancelarEntrega(@PathVariable String id) {
        Entrega entrega = useCase.cancelarEntrega(id);
        return mapper.paraEntregaResponse(entrega);
    }

    @Override
    public EntregaResponse acompanharEntrega(String id) {
        Entrega entrega = useCase.acompanharEntrega(id);
        return mapper.paraEntregaResponse(entrega);
    }

    @Override
    public EntregaResponse atualizarEntrega(String id, EntregaRequest entregaRequest) {
        Entrega entrega = useCase.atualizarEntrega(id, mapper.paraEntrega(entregaRequest));
        return mapper.paraEntregaResponse(entrega);
    }

    @Override
    public EntregaResponse finalizarEntrega(String id) {
        Entrega entrega = useCase.finalizarEntrega(id);
        return mapper.paraEntregaResponse(entrega);
    }

    @Override
    public void deleteEntrega(String id) {
        useCase.deletarEntrega(id);
    }
}