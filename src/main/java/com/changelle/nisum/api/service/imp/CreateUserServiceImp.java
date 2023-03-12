package com.changelle.nisum.api.service.imp;

import com.changelle.nisum.api.domain.UserRepository;
import com.changelle.nisum.api.domain.model.Phone;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.dto.CreateUserRequestDto;
import com.changelle.nisum.api.dto.CreateUserResponseDto;
import com.changelle.nisum.api.dto.ValidateDto;
import com.changelle.nisum.api.exception.CreateUserNotFoundException;
import com.changelle.nisum.api.service.CreateUserService;
import com.changelle.nisum.api.utils.ErrorMsg;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateUserServiceImp extends UserServiceImp implements CreateUserService {

    public CreateUserServiceImp(UserRepository userRepository) {
        super(userRepository);
    }

    /**
     * User is created
     *
     * @param userRequest
     * @param bearerToken
     * @return CreateUserResponseDto
     */
    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto userRequest, String bearerToken) {

        validateData(ValidateDto.builder().mail(userRequest.getEmail()).password(userRequest.getPassword()).build());

        User user = buildUser(userRequest, bearerToken);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new CreateUserNotFoundException(ErrorMsg.buildMsg(ErrorMsg.ERROR_CREATE_USER_DB, e.getMessage()));
        }

        return CreateUserResponseDto.from(user);
    }

    /**
     * The response is generated for the user with the required fields.
     *
     * @param userRequest
     * @param bearerToken
     * @return User
     */
    private User buildUser(CreateUserRequestDto userRequest, String bearerToken) {
        User user = User.builder()
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .email(userRequest.getEmail())
                .isActive(true)
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .token(bearerToken)
                .build();

        List<Phone> phoneList = userRequest.getPhones().stream().map(phonesRequestDto -> Phone.builder()
                .cityCode(phonesRequestDto.getCityCode())
                .countryCode(phonesRequestDto.getCountryCode())
                .number(phonesRequestDto.getNumber())
                .user(user)
                .build()).collect(Collectors.toList());

        user.setPhones(phoneList);
        return user;
    }
}
