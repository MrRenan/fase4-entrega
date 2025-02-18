package br.com.fiap.fase4entrega.features.adapter.in.v1.mapper;

import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.infra.restapi.v1.model.EntregaRequest;
import br.com.fiap.fase4entrega.infra.restapi.v1.model.EntregaResponse;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@AnnotateWith(
        value = Component.class,
        elements = @AnnotateWith.Element(strings = "featuresAdapterInMapperEntregaMapperImpl")
)
public interface EntregaMapper {

    @Mapping(source = "pedidoId", target = "pedidoId")
    Entrega paraEntrega(EntregaRequest entregaRequest);

    EntregaResponse paraEntregaResponse(Entrega entrega);
}
