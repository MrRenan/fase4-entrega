package br.com.fiap.fase4entrega.config.exception;

import br.com.fiap.fase4entrega.features.domain.exception.EntregaNaoAtualizadaException;
import br.com.fiap.fase4entrega.features.domain.exception.EntregaNaoEncontradaException;
import br.com.fiap.fase4entrega.features.domain.exception.dto.SimpleError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(EntregaNaoEncontradaException.class)
    public ResponseEntity<SimpleError> handleEntregaNaoEncontradaException(EntregaNaoEncontradaException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new SimpleError(ex.getMessage(), NOT_FOUND.toString()));
    }

    @ExceptionHandler(EntregaNaoAtualizadaException.class)
    public ResponseEntity<SimpleError> handleEntregaNaoAtualizadaException(EntregaNaoAtualizadaException ex) {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(new SimpleError(ex.getMessage(),
                UNPROCESSABLE_ENTITY.toString()));
    }

}
