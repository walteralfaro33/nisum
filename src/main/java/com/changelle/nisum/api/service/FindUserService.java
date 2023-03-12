package com.changelle.nisum.api.service;

import com.changelle.nisum.api.dto.FindUserResponseDto;

public interface FindUserService {
    FindUserResponseDto getUserById(int id);
}
