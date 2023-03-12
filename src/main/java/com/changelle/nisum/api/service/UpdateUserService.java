package com.changelle.nisum.api.service;

import com.changelle.nisum.api.dto.UpdateUserResponseDto;
import com.changelle.nisum.api.dto.UpdateUserRequestDto;

public interface UpdateUserService {
    UpdateUserResponseDto updateUserById(int id, UpdateUserRequestDto updateUserRequestDto, String bearerToken);
}
