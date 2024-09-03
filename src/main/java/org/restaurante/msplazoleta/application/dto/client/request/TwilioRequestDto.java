package org.restaurante.msplazoleta.application.dto.client.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwilioRequestDto {

    private String phoneNumber;

    private Integer message;
}
