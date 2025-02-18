package br.com.fiap.fase4entrega.features.adapter.out.mapper;

import br.com.fiap.fase4entrega.features.domain.entity.Cliente;
import br.com.fiap.fase4entrega.features.domain.entity.Endereco;
import br.com.fiap.fase4entrega.features.domain.entity.Entrega;
import br.com.fiap.fase4entrega.features.domain.entity.Produto;
import br.com.fiap.fase4entrega.infra.mongodb.document.EntregaDocument;
import br.com.fiap.fase4entrega.infra.restclient.cliente.entity.ClienteEntity;
import br.com.fiap.fase4entrega.infra.restclient.cliente.entity.EnderecoEntity;
import br.com.fiap.fase4entrega.infra.restclient.produto.entity.ProdutoEntity;
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


    EntregaDocument paraEntregaDocument(Entrega entrega);

    @Mapping(source = "id", target = "entregaId")
    Entrega paraEntrega(EntregaDocument entregaDocument);

    Cliente paraCliente(ClienteEntity clienteEntity);

    Produto paraProduto(ProdutoEntity produtoEntity);

    ProdutoEntity paraProdutoEntity(Produto produto);

    Endereco paraEndereco(EnderecoEntity endereco);
}
