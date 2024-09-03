package org.restaurante.msplazoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class MsPlazoletaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPlazoletaApplication.class, args);
    }

}
