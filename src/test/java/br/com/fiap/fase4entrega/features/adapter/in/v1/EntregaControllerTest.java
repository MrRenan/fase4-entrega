package br.com.fiap.fase4entrega.features.adapter.in.v1;

import br.com.fiap.fase4entrega.features.adapter.in.v1.mapper.EntregaMapper;
import br.com.fiap.fase4entrega.features.application.usecase.EntregaUseCase;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.features.domain.entity.EntregaStub;
import br.com.fiap.fase4entrega.infra.restapi.v1.model.EntregaRequest;
import br.com.fiap.fase4entrega.infra.restapi.v1.model.EntregaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EntregaControllerTest {

    private static final String BASE_URL = "/v1/entrega";

    @InjectMocks
    private EntregaController entregaController;
    @Mock
    private EntregaUseCase useCase;
    @Mock
    private EntregaMapper mapper;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    EntregaRequest entregaRequest;
    EntregaResponse entregaResponse;
    Entrega entrega;

    @BeforeEach
    void setUp() {
        entrega = EntregaStub.novo().build();
        mockMvc = MockMvcBuilders.standaloneSetup(entregaController).build();
        objectMapper = new ObjectMapper();

        entregaRequest = EntregaRequest.builder()
                .id(entrega.getEntregaId())
                .pedidoId(entrega.getPedidoId())
                .build();

        entregaResponse = EntregaResponse.builder()
                .entregaId(entrega.getEntregaId())
                .pedidoId(entrega.getPedidoId())
                .status(br.com.fiap.fase4entrega.infra.restapi.v1.model.Status.valueOf(entrega.getStatus().name()))
                .dataPrevistaEntrega(entrega.getDataPrevistaEntrega())
                .endereco(EntregaResponse.EnderecoResponse.builder()
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
    void criarEntrega() throws Exception {
        // ASSETS
        given(useCase.criarEntrega(entrega))
                .willReturn(entrega);
        given(mapper.paraEntregaResponse(entrega))
                .willReturn(entregaResponse);
        given(mapper.paraEntrega(entregaRequest))
                .willReturn(entrega);
        // ACTION
        var result = mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entregaRequest)));
        // ASSERTIONS
        result.andExpect(status().isCreated());

    }

    @Test
    void obterEntregas() throws Exception {
        // ASSETS
        given(useCase.obterEntregas())
                .willReturn(List.of(entrega));
        given(mapper.paraEntregaResponse(entrega))
                .willReturn(entregaResponse);
        // ACTION
        var result = mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        // ASSERTIONS
        result.andExpect(status().isOk());
    }

    @Test
    void cancelarEntrega() throws Exception {
        // ASSETS
        given(useCase.cancelarEntrega(entregaRequest.id()))
                .willReturn(entrega);
        given(mapper.paraEntregaResponse(entrega))
                .willReturn(entregaResponse);
        // ACTION
        var result = mockMvc.perform(put(BASE_URL.concat("/" + entregaRequest.id() + "/cancelar"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entregaRequest)));
        // ASSERTIONS
        result.andExpect(status().isOk());
    }

    @Test
    void acompanharEntrega() throws Exception {
        // ASSETS
        given(useCase.acompanharEntrega(entregaRequest.id()))
                .willReturn(entrega);
        given(mapper.paraEntregaResponse(entrega))
                .willReturn(entregaResponse);
        // ACTION
        var result = mockMvc.perform(get(BASE_URL.concat("/" + entregaRequest.id()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entregaRequest)));
        // ASSERTIONS
        result.andExpect(status().isOk());
    }

    @Test
    void atualizarEntrega() throws Exception {
        // ASSETS
        given(useCase.atualizarEntrega(entregaRequest.id(), entrega))
                .willReturn(entrega);
        given(mapper.paraEntregaResponse(entrega))
                .willReturn(entregaResponse);
        given(mapper.paraEntrega(entregaRequest))
                .willReturn(entrega);
        // ACTION
        var result = mockMvc.perform(put(BASE_URL.concat("/" + entregaRequest.id() + "/atualizar"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entregaRequest)));
        // ASSERTIONS
        result.andExpect(status().isOk());
    }

    @Test
    void finalizarEntrega() throws Exception {
        // ASSETS
        given(useCase.finalizarEntrega(entregaRequest.id()))
                .willReturn(entrega);
        given(mapper.paraEntregaResponse(entrega))
                .willReturn(entregaResponse);
        // ACTION
        var result = mockMvc.perform(put(BASE_URL.concat("/" + entregaRequest.id() + "/finalizar"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entregaRequest)));
        // ASSERTIONS
        result.andExpect(status().isOk());
    }

    @Test
    void deletarEntrega() throws Exception {
        // ASSETS
        willDoNothing().given(useCase).deletarEntrega(entregaRequest.id());
        // ACTION
        var result = mockMvc.perform(delete(BASE_URL.concat("/" + entregaRequest.id()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entregaRequest)));
        // ASSERTIONS
        result.andExpect(status().isNoContent());
    }

    @Test
    void atualizarLatitudeLongitude() throws Exception {
        // ASSETS
        given(useCase.atualizarLatitudeLongitude(entrega.getEntregaId(), entrega.getLatitude(),
                entrega.getLongitude()))
                .willReturn(entrega);

        given(mapper.paraEntregaResponse(entrega))
                .willReturn(entregaResponse);
        // ACTION
        var result = mockMvc.perform(put(BASE_URL + "/" + entrega.getEntregaId()
                + "/coordenadas/" + entrega.getLatitude() + "/" + entrega.getLongitude())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        // ASSERTIONS
        result.andExpect(status().isOk());
    }

}