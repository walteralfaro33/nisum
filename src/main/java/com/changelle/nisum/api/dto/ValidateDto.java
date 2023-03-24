package com.changelle.nisum.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class ValidateDto {
    private String password;
    private String mail;
    private String oldMail;
}
