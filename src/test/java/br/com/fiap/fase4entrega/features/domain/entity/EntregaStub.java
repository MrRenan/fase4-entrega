package br.com.fiap.fase4entrega.features.domain.entity;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntregaStub {

    Faker faker = new Faker();

    public static EntregaStub novo() {
        return new EntregaStub();
    }

    public Entrega build(){
        return Entrega.builder()
                .entregaId(faker.number().digits(10))
                .pedidoId(faker.number().digits(10))
                .status(faker.options().option(Status.class))
                .dataPrevistaEntrega(LocalDate.now().plusDays(5))
                .dataEntrega(null)
                .endereco(Endereco.builder()
                        .rua(faker.address().streetName())
                        .numero(faker.number().digits(3))
                        .bairro(faker.address().state())
                        .cidade(faker.address().city())
                        .estado(faker.address().state())
                        .cep(faker.number().digits(10))
                        .build())
                .codigoRastreio(faker.number().digits(10))
                .ultimaAtualizacao(LocalDateTime.now())
                .latitude(BigDecimal.valueOf(faker.number().numberBetween(-500000000, 500000000)))
                .longitude(BigDecimal.valueOf(faker.number().numberBetween(-500000000, 500000000)))
                .build();
    }
}