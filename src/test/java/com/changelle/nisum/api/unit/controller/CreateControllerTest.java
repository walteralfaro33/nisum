package com.changelle.nisum.api.unit.controller;

import com.changelle.nisum.api.controller.UserController;
import com.changelle.nisum.api.dto.CreateUserRequestDto;
import com.changelle.nisum.api.dto.CreateUserResponseDto;
import com.changelle.nisum.api.exception.CreateUserNotFoundException;
import com.changelle.nisum.api.service.CreateUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class CreateControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private CreateUserService createUserService;

    @Test
    public void test_saveUser_ok() {
        String token = UUID.randomUUID().toString();
        int idUser = 1;
        when(createUserService.createUser(Mockito.any(), Mockito.anyString())).thenReturn(CreateUserResponseDto.builder().idUser(idUser).token(token).build());

        CreateUserResponseDto createUserResponseDto = userController.createUser(CreateUserRequestDto.builder().build(), token);

        //Assert
        Assert.assertNotNull(createUserResponseDto);
        Assert.assertEquals(idUser, createUserResponseDto.getIdUser());
        Assert.assertEquals(token, createUserResponseDto.getToken());
    }


    @Test
    public void test_saveUser_errorDataBaseException() {
        String token = UUID.randomUUID().toString();

        when(createUserService.createUser(Mockito.any(), Mockito.anyString())).thenThrow(new CreateUserNotFoundException("DataBaseException"));

        //Assert
        Assert.assertThrows(CreateUserNotFoundException.class, () -> {
            userController.createUser(CreateUserRequestDto.builder().build(), token);
        });
    }
}
