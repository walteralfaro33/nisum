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
public class UpdateUserResponseDto {
    private int idUser;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    private String name;
    private String mail;
    private String password;

    public static UpdateUserResponseDto from(User user) {
        return UpdateUserResponseDto.builder().mail(user.getEmail()).name(user.getName()).password(user.getPassword()).created(user.getCreated()).idUser(user.getId()).lastLogin(user.getCreated()).modified(user.getModified()).isActive(user.isActive()).token(user.getToken()).build();
    }
}
