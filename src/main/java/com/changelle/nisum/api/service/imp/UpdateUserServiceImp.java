package com.changelle.nisum.api.service.imp;

import com.changelle.nisum.api.domain.UserRepository;
import com.changelle.nisum.api.domain.model.Phone;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.dto.UpdateUserRequestDto;
import com.changelle.nisum.api.dto.UpdateUserResponseDto;
import com.changelle.nisum.api.dto.ValidateDto;
import com.changelle.nisum.api.exception.UpdateUserNotFoundException;
import com.changelle.nisum.api.exception.UserNotFoundException;
import com.changelle.nisum.api.service.UpdateUserService;
import com.changelle.nisum.api.utils.ErrorMsg;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateUserServiceImp extends UserServiceImp implements UpdateUserService {

    public UpdateUserServiceImp(UserRepository userRepository) {
        super(userRepository);
    }

    /**
     * The user is updated by id
     *
     * @param id
     * @param updateUserRequestDto
     * @param bearerToken
     * @return UpdateUserResponseDto
     */
    @Override
    public UpdateUserResponseDto updateUserById(int id, UpdateUserRequestDto updateUserRequestDto, String bearerToken) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ErrorMsg.buildMsg(ErrorMsg.ERROR_USER_NOT_FOUND, String.valueOf(id))));

        validateData(ValidateDto.builder().mail(updateUserRequestDto.getEmail()).password(updateUserRequestDto.getPassword()).oldMail(user.getEmail()).build());

        buildUser(updateUserRequestDto, bearerToken, user);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UpdateUserNotFoundException(ErrorMsg.buildMsg(ErrorMsg.ERROR_UPDATE_USER_DB, e.getMessage()));
        }
        return UpdateUserResponseDto.from(user);
    }

    private void buildUser(UpdateUserRequestDto updateUserRequestDto, String bearerToken, User user) {
        user.setName(updateUserRequestDto.getName());
        user.setActive(updateUserRequestDto.isActive());
        user.setEmail(updateUserRequestDto.getEmail());
        user.setPassword(updateUserRequestDto.getPassword());
        user.setModified(LocalDateTime.now());
        user.setToken(bearerToken);
        user.setModified(LocalDateTime.now());
        List<Phone> phoneList = updateUserRequestDto.getPhones().stream().map(phonesRequestDto -> Phone.builder()
                .cityCode(phonesRequestDto.getCityCode())
                .countryCode(phonesRequestDto.getCountryCode())
                .number(phonesRequestDto.getNumber())
                .user(user)
                .build()).collect(Collectors.toList());
        user.setPhones(phoneList);
    }
}
