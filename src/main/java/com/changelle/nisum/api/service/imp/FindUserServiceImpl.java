package com.changelle.nisum.api.service.imp;

import com.changelle.nisum.api.domain.UserRepository;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.dto.FindUserResponseDto;
import com.changelle.nisum.api.exception.UserNotFoundException;
import com.changelle.nisum.api.service.FindUserService;
import com.changelle.nisum.api.utils.ErrorMsg;
import org.springframework.stereotype.Service;

@Service
public class FindUserServiceImpl implements FindUserService {
    private final UserRepository userRepository;

    public FindUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * The search for the user and their phone numbers is performed
     *
     * @param id
     * @return FindUserResponseDto
     */
    @Override
    public FindUserResponseDto getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ErrorMsg.buildMsg(ErrorMsg.ERROR_USER_NOT_FOUND, String.valueOf(id))));
        return FindUserResponseDto.from(user);
    }
}
