package com.changelle.nisum.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Getter
@Builder
@AllArgsConstructor
public class CreateUserRequestDto {
    private String name;
    private String email;
    private String password;
    private List<PhonesRequestDto> phones;
}
