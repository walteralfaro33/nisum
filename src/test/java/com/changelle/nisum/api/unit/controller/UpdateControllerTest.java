package com.changelle.nisum.api.unit.controller;

import com.changelle.nisum.api.controller.UserController;
import com.changelle.nisum.api.dto.UpdateUserRequestDto;
import com.changelle.nisum.api.dto.UpdateUserResponseDto;
import com.changelle.nisum.api.exception.UpdateUserNotFoundException;
import com.changelle.nisum.api.exception.UserNotFoundException;
import com.changelle.nisum.api.service.UpdateUserService;
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
public class UpdateControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UpdateUserService updateUserService;

    @Test
    public void test_getUserById_ok() {
        String token = UUID.randomUUID().toString();
        int idUser = 1;
        when(updateUserService.updateUserById(Mockito.anyInt(), Mockito.any(), Mockito.anyString())).thenReturn(UpdateUserResponseDto.builder().idUser(idUser).token(token).build());

        UpdateUserResponseDto updateUserResponseDto = userController.updateUserById(idUser, UpdateUserRequestDto.builder().name("walter").build(), token);

        //Assert
        Assert.assertNotNull(updateUserResponseDto);
        Assert.assertEquals(idUser, updateUserResponseDto.getIdUser());
        Assert.assertEquals(token, updateUserResponseDto.getToken());
    }

    @Test
    void test_getUser_thenReturnErrorUserNotFoundException() {
        String token = UUID.randomUUID().toString();
        int idUser = 1;
        when(updateUserService.updateUserById(Mockito.anyInt(), Mockito.any(), Mockito.anyString())).thenThrow(new UpdateUserNotFoundException("UpdateUserNotFoundException"));
        //Assert
        Assert.assertThrows(UpdateUserNotFoundException.class, () -> {
            userController.updateUserById(idUser, UpdateUserRequestDto.builder().name("walter").build(), token);
        });
    }
}
