package br.com.fiap.fase4entrega.infra.restclient.produto;

import br.com.fiap.fase4entrega.infra.restclient.produto.entity.ProdutoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto", url = "http://localhost:8081/v1/produto")
public interface ProdutoClient {

    @GetMapping("/{id}")
    ProdutoEntity obterProduto(@PathVariable String id);

}

