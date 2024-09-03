package org.restaurante.msplazoleta.infrastructure.client;

import org.restaurante.msplazoleta.application.dto.client.response.UserResponseDto;
import org.restaurante.msplazoleta.infrastructure.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-Usuarios", url = "http://localhost:8090", configuration = FeignConfiguration.class)
public interface IUserClient {

    @GetMapping(value = "/user/admin/owner/{id}")
    UserResponseDto getUser(@PathVariable("id") Long id);

    @GetMapping(value = "/user/owner/employee/{email}")
    UserResponseDto getEmployee(@PathVariable("email") String email);

    @GetMapping(value = "/user/client/{id}")
    UserResponseDto getClient(@PathVariable("id") Long id);
}
