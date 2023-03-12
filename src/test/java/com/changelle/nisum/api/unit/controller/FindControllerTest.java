package com.changelle.nisum.api.unit.controller;

import com.changelle.nisum.api.controller.UserController;
import com.changelle.nisum.api.dto.FindUserResponseDto;
import com.changelle.nisum.api.exception.UserNotFoundException;
import com.changelle.nisum.api.service.FindUserService;
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
public class FindControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private FindUserService findUserService;

    @Test
    public void test_getUserById_ok() {
        String token = UUID.randomUUID().toString();
        int idUser = 1;
        when(findUserService.getUserById(Mockito.anyInt())).thenReturn(FindUserResponseDto.builder().idUser(idUser).token(token).build());

        FindUserResponseDto findUserResponseDto = userController.getUserById(idUser);

        //Assert
        Assert.assertNotNull(findUserResponseDto);
        Assert.assertEquals(idUser, findUserResponseDto.getIdUser());
        Assert.assertEquals(token, findUserResponseDto.getToken());
    }

    @Test
    void test_getUser_thenReturnErrorUserNotFoundException() {
        when(findUserService.getUserById(Mockito.anyInt())).thenThrow(new UserNotFoundException("UserNotFoundException"));
        //Assert
        Assert.assertThrows(UserNotFoundException.class, () -> {
            userController.getUserById(1);
        });
    }

}
