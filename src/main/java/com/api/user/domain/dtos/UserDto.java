package com.api.user.domain.dtos;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String cpf;
    @Valid
    @NotNull
    private AddressDto address;
}
