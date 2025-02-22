package br.com.fiap.fase4entrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EntregaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntregaApplication.class, args);
    }

}
