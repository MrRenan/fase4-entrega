package br.com.fiap.fase4entrega.infra.restapi.v1;

import br.com.fiap.fase4entrega.infra.restapi.v1.model.EntregaRequest;
import br.com.fiap.fase4entrega.infra.restapi.v1.model.EntregaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Entrega", description = "Operações relacionadas ao dominio de entrega")
@RequestMapping("/v1/entrega")
public interface EntregaApi {

    @Operation(summary = "Criar entrega.")
    @PostMapping
    @ResponseStatus(CREATED)
    EntregaResponse criarEntrega(@RequestBody EntregaRequest entregaRequest);

    @Operation(summary = "Obter todos entregas.")
    @GetMapping
    @ResponseStatus(OK)
    List<EntregaResponse> obterEntregas();

    @Operation(summary = "Cancelar entrega.")
    @PutMapping("/{id}/cancelar")
    @ResponseStatus(OK)
    EntregaResponse cancelarEntrega(@PathVariable String id);

    @Operation(summary = "Acompanhar entrega.")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    EntregaResponse acompanharEntrega(@PathVariable String id);

    @Operation(summary = "Atualizar entrega.")
    @PutMapping("/{id}/atualizar")
    @ResponseStatus(OK)
    EntregaResponse atualizarEntrega(@PathVariable String id, @RequestBody EntregaRequest entregaRequest);

    @Operation(summary = "Finalizar entrega.")
    @PutMapping("/{id}/finalizar")
    @ResponseStatus(OK)
    EntregaResponse finalizarEntrega(@PathVariable String id);

    @Operation(summary = "Deletar entrega.")
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void deletarEntrega(@PathVariable String id);

    @Operation(summary = "Atualizar coordenadas.")
    @PutMapping("/{id}/coordenadas/{latitude}/{longitude}")
    @ResponseStatus(OK)
    EntregaResponse atualizarLatitudeLongitude(@PathVariable("id") String id,
                                               @PathVariable("latitude") BigDecimal latitude,
                                               @PathVariable("longitude") BigDecimal longitude);
}