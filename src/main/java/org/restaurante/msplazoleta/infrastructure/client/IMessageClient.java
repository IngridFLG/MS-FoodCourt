package org.restaurante.msplazoleta.infrastructure.client;

import org.restaurante.msplazoleta.application.dto.client.request.TwilioRequestDto;
import org.restaurante.msplazoleta.infrastructure.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MS-Mensajeria", url = "http://localhost:8070", configuration = FeignConfiguration.class)
public interface IMessageClient {

    @PostMapping(value = "/twilio")
    void sendMessage(@RequestBody TwilioRequestDto twilioDto);
}
