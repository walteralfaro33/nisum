package com.changelle.nisum.api.service;

import com.changelle.nisum.api.dto.CreateUserRequestDto;
import com.changelle.nisum.api.dto.CreateUserResponseDto;

public interface CreateUserService {
    CreateUserResponseDto createUser(CreateUserRequestDto userRequest, String bearerToken);
}
