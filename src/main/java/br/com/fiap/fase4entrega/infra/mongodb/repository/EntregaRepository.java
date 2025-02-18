package br.com.fiap.fase4entrega.infra.mongodb.repository;

import br.com.fiap.fase4entrega.infra.mongodb.document.EntregaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntregaRepository extends MongoRepository<EntregaDocument, String> {
}
