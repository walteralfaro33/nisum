package com.changelle.nisum.api.dto;

import com.changelle.nisum.api.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Getter
@Builder
@AllArgsConstructor
public class CreateUserResponseDto {
    private int idUser;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;

    public static CreateUserResponseDto from(User user) {
        return CreateUserResponseDto.builder().created(user.getCreated()).idUser(user.getId()).lastLogin(user.getCreated()).modified(user.getModified()).isActive(user.isActive()).token(user.getToken()).build();
    }
}
