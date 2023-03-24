package com.changelle.nisum.api.unit.service;

import com.changelle.nisum.api.domain.repository.UserRepository;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.dto.PhonesRequestDto;
import com.changelle.nisum.api.dto.UpdateUserRequestDto;
import com.changelle.nisum.api.dto.UpdateUserResponseDto;
import com.changelle.nisum.api.exception.*;
import com.changelle.nisum.api.service.imp.UpdateUserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
class UpdateUserServiceImpTest {

    @InjectMocks
    UpdateUserServiceImp updateUserServiceImp;

    @Mock
    private UserRepository userRepository;

    public String token = UUID.randomUUID().toString();

    public int idUser = 1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
     }

    @Test
    void testUpdateUser_thenReturnUserUpdate() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder().active(true).name("walter").email("waltera2SS2@gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(User.builder().name("marcos").token(token).created(LocalDateTime.now()).lastLogin(LocalDateTime.now()).modified(LocalDateTime.now()).id(idUser).build()));

        UpdateUserResponseDto updateUserResponseDto = updateUserServiceImp.updateUserById(idUser, updateUserRequestDto, token);

        //Assert
        Assertions.assertNotNull(updateUserResponseDto);
        Assertions.assertEquals("walter", updateUserResponseDto.getName());
        Assertions.assertEquals(token, updateUserResponseDto.getToken());
        Assertions.assertNotNull(updateUserResponseDto.getCreated());
        Assertions.assertNotNull(updateUserResponseDto.getLastLogin());
        Assertions.assertNotNull(updateUserResponseDto.getModified());
        Assertions.assertNotNull(updateUserResponseDto.getMail());
        Assertions.assertNotNull(updateUserResponseDto.getPassword());
        Assertions.assertTrue(updateUserResponseDto.isActive());

    }

    @Test
    void testUpdateUser_checkPassword_thenReturnErrorPasswordInvalidFoundException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder().email("waltera2SS2@gmail.com").password("aaaaaaa").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(User.builder().name("marcos").token(token).created(LocalDateTime.now()).lastLogin(LocalDateTime.now()).modified(LocalDateTime.now()).id(idUser).build()));
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);

        //Assert
        assertThatThrownBy(() -> updateUserServiceImp.updateUserById(idUser, updateUserRequestDto, token))
                .isInstanceOf(PasswordInvalidFoundException.class);
    }

    @Test
    void testUpdateUser_checkMail_thenReturnErrorMailInvalidFoundException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder().email("waltera2SS2¢gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(User.builder().name("marcos").token(token).created(LocalDateTime.now()).lastLogin(LocalDateTime.now()).modified(LocalDateTime.now()).id(idUser).build()));
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);

        //Assert
        assertThatThrownBy(() -> updateUserServiceImp.updateUserById(idUser, updateUserRequestDto, token))
                .isInstanceOf(MailInvalidFoundException.class);
    }

    @Test
    void testUpdateUser_checkMail_thenReturnConflictException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder().email("walteraka22@gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(User.builder().name("marcos").token(token).created(LocalDateTime.now()).lastLogin(LocalDateTime.now()).modified(LocalDateTime.now()).id(idUser).build()));
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(true);

        //Assert
        assertThatThrownBy(() -> updateUserServiceImp.updateUserById(idUser, updateUserRequestDto, token))
                .isInstanceOf(MailConflictException.class);

    }

    @Test
    void testUpdateUser_throwErrorUserNotFoundException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder().email("walteraka22@gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        when(userRepository.findById(Mockito.anyInt())).thenThrow(new UserNotFoundException("UserNotFoundException"));
        //Assert
        assertThatThrownBy(() -> updateUserServiceImp.updateUserById(idUser, updateUserRequestDto, token))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void testUpdateUser_checkMail_thenReturnErrorDataBaseException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder().email("walteraka22@gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(User.builder().name("marcos").token(token).created(LocalDateTime.now()).lastLogin(LocalDateTime.now()).modified(LocalDateTime.now()).id(idUser).build()));
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);
        when(userRepository.save(Mockito.any())).thenThrow(ConstraintViolationException.class);

        //Assert
        assertThatThrownBy(() -> updateUserServiceImp.updateUserById(idUser, updateUserRequestDto, token))
                .isInstanceOf(UpdateUserNotFoundException.class);

    }
}
