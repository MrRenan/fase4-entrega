package br.com.fiap.fase4entrega.features.adapter.out.mapper;

import br.com.fiap.fase4entrega.features.domain.entity.Endereco;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.infra.mongodb.document.EntregaDocument;
import br.com.fiap.fase4entrega.infra.restclient.cliente.entity.EnderecoEntity;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@AnnotateWith(
        value = Component.class,
        elements = @AnnotateWith.Element(strings = "featuresAdapterOutMapperEntregaMapperImpl")
)
public interface EntregaMapper {

    @Mapping(source = "entregaId", target = "id")
    EntregaDocument paraEntregaDocument(Entrega entrega);

    @Mapping(source = "id", target = "entregaId")
    Entrega paraEntrega(EntregaDocument entregaDocument);

    Endereco paraEndereco(EnderecoEntity endereco);
}
