package com.changelle.nisum.api.unit.service;

import com.changelle.nisum.api.domain.UserRepository;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.dto.FindUserResponseDto;
import com.changelle.nisum.api.exception.UserNotFoundException;
import com.changelle.nisum.api.service.imp.FindUserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
class FindUserServiceImpTest {

    @InjectMocks
    FindUserServiceImpl findUserService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public String token = UUID.randomUUID().toString();

    public int idUser = 1;

    @Test
    void testGetUserById_thenReturnUser() {
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(User.builder().token(token).id(idUser).phones(new ArrayList<>()).build()));
        FindUserResponseDto findUserResponseDto = findUserService.getUserById(idUser);
        verify(userRepository, times(1)).findById(Mockito.any());

        //Assert
        Assert.assertNotNull(findUserResponseDto);
        Assert.assertEquals(idUser, findUserResponseDto.getIdUser());
        Assert.assertEquals(token, findUserResponseDto.getToken());
    }


    @Test
    void testGetUserById_throwErrorUserNotFoundException() {
        when(userRepository.findById(Mockito.anyInt())).thenThrow(new UserNotFoundException("UserNotFoundException"));
        //Assert
        Assert.assertThrows(UserNotFoundException.class, () -> {
            findUserService.getUserById(idUser);
        });
    }
}
