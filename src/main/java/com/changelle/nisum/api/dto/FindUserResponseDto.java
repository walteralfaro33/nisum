package com.changelle.nisum.api.dto;

import com.changelle.nisum.api.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;

@Jacksonized
@Setter
@Getter
@Builder
@AllArgsConstructor
public class FindUserResponseDto {
    private int idUser;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    private String name;
    private String mail;
    private String password;
    private List<PhonesRequestDto> phones;

    public static FindUserResponseDto from(User user) {
        return FindUserResponseDto.builder().phones(PhonesRequestDto.from(user.getPhones())).created(user.getCreated()).idUser(user.getId()).name(user.getName()).mail(user.getEmail()).password(user.getPassword()).lastLogin(user.getCreated()).modified(user.getModified()).isActive(user.isActive()).token(user.getToken()).build();
    }
}
