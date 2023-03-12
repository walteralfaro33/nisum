package com.changelle.nisum.api.unit.service;

import com.changelle.nisum.api.domain.UserRepository;
import com.changelle.nisum.api.dto.CreateUserRequestDto;
import com.changelle.nisum.api.dto.CreateUserResponseDto;
import com.changelle.nisum.api.dto.PhonesRequestDto;
import com.changelle.nisum.api.exception.MailConflictException;
import com.changelle.nisum.api.exception.CreateUserNotFoundException;
import com.changelle.nisum.api.exception.MailInvalidFoundException;
import com.changelle.nisum.api.exception.PasswordInvalidFoundException;
import com.changelle.nisum.api.service.imp.CreateUserServiceImp;
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
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
class CreateUserServiceImpTest {

    @InjectMocks
    CreateUserServiceImp createUserServiceImp;

    @Mock
    private UserRepository userRepository;

    public String token = UUID.randomUUID().toString();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
     }

    @Test
    void testCreateUser_thenReturnDtoUser() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        CreateUserRequestDto createUserRequestDto = CreateUserRequestDto.builder().name("walter").email("waltera2SS2@gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);

        CreateUserResponseDto createUserResponseDto = createUserServiceImp.createUser(createUserRequestDto, token);

        //Assert
        Assertions.assertNotNull(createUserResponseDto);
        Assertions.assertNotNull(createUserResponseDto.getToken());
        Assertions.assertNotNull(createUserResponseDto.getCreated());
        Assertions.assertNotNull(createUserResponseDto.getCreated());
        Assertions.assertNotNull(createUserResponseDto.getLastLogin());
        Assertions.assertNotNull(createUserResponseDto.getModified());
        Assertions.assertTrue(createUserResponseDto.isActive());
        Assertions.assertEquals(token, createUserResponseDto.getToken());
    }

    @Test
    void testCreateUser_checkPassword_thenReturnErrorPasswordInvalidFoundException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        CreateUserRequestDto createUserRequestDto = CreateUserRequestDto.builder().email("waltera2SS2@gmail.com").password("aaaaaaa").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);

        //Assert
        assertThatThrownBy(() -> createUserServiceImp.createUser(createUserRequestDto, token))
                .isInstanceOf(PasswordInvalidFoundException.class);
    }


    @Test
    void testCreateUser_checkMail_thenReturnErrorMailInvalidFoundException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        CreateUserRequestDto createUserRequestDto = CreateUserRequestDto.builder().email("waltera2SS2¢gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);

        //Assert
        assertThatThrownBy(() -> createUserServiceImp.createUser(createUserRequestDto, token))
                .isInstanceOf(MailInvalidFoundException.class);

    }

    @Test
    void testCreateUser_checkMail_thenReturnErrorMailEmptyInvalidFoundException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        CreateUserRequestDto createUserRequestDto = CreateUserRequestDto.builder().password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);

        //Assert
        assertThatThrownBy(() -> createUserServiceImp.createUser(createUserRequestDto, token))
                .isInstanceOf(MailInvalidFoundException.class);

    }

    @Test
    void testCreateUser_checkMail_thenReturnConflictException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        CreateUserRequestDto createUserRequestDto = CreateUserRequestDto.builder().email("walteraka22@gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(true);

        //Assert
        assertThatThrownBy(() -> createUserServiceImp.createUser(createUserRequestDto, token))
                .isInstanceOf(MailConflictException.class);

    }

    @Test
    void testCreateUser_checkMail_thenReturnErrorDataBaseException() {
        //Data preparation
        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        CreateUserRequestDto createUserRequestDto = CreateUserRequestDto.builder().email("walteraka23@gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        //when
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);
        when(userRepository.save(Mockito.any())).thenThrow(ConstraintViolationException.class);

        //Assert
        assertThatThrownBy(() -> createUserServiceImp.createUser(createUserRequestDto, token))
                .isInstanceOf(CreateUserNotFoundException.class);

    }
}
