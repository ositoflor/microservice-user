package com.api.user.domain.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddressDto {

    @NotBlank
    private String street;
    @NotNull
    private int number;
    private String complement;
    @NotBlank
    private String district;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
}
