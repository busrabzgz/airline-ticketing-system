package com.example.airlineticketingsystem.dto.request;

import com.example.airlineticketingsystem.helper.message.ValidationMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCountryRequest {
    @NotEmpty(message = ValidationMessage.Country.NAME_NOT_EMPTY)
    @NotNull(message = ValidationMessage.Country.NAME_NOT_NULL)
    private String name;
}
