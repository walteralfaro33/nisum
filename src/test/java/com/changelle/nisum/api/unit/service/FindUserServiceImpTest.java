package com.changelle.nisum.api.unit.service;

import com.changelle.nisum.api.domain.model.Phone;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.domain.repository.UserRepository;
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

import java.time.LocalDateTime;
import java.util.List;
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
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(
                User.builder()
                        .token(token)
                        .id(idUser)
                        .modified(LocalDateTime.now())
                        .created(LocalDateTime.now())
                        .lastLogin(LocalDateTime.now())
                        .name("zyx")
                        .email("aaaa@gmail.com")
                        .isActive(true)
                        .password("aaaaa")
                        .phones(List.of(getPhone()))
                        .build()));
        FindUserResponseDto findUserResponseDto = findUserService.getUserById(idUser);
        verify(userRepository, times(1)).findById(Mockito.any());

        //Assert
        Assert.assertNotNull(findUserResponseDto);
        Assert.assertEquals(idUser, findUserResponseDto.getIdUser());
        Assert.assertEquals(token, findUserResponseDto.getToken());
        Assert.assertNotNull(findUserResponseDto.getCreated());
        Assert.assertNotNull(findUserResponseDto.getLastLogin());
        Assert.assertNotNull(findUserResponseDto.getMail());
        Assert.assertNotNull(findUserResponseDto.getModified());
        Assert.assertNotNull(findUserResponseDto.getName());
        Assert.assertNotNull(findUserResponseDto.getPassword());
        Assert.assertTrue(findUserResponseDto.isActive());
        Assert.assertNotNull(findUserResponseDto.getPhones());
        Assert.assertNotNull(findUserResponseDto.getPhones().get(0).getCityCode());
        Assert.assertNotNull(findUserResponseDto.getPhones().get(0).getCountryCode());
        Assert.assertNotNull(findUserResponseDto.getPhones().get(0).getNumber());
    }


    @Test
    void testGetUserById_throwErrorUserNotFoundException() {
        when(userRepository.findById(Mockito.anyInt())).thenThrow(new UserNotFoundException("UserNotFoundException"));
        //Assert
        Assert.assertThrows(UserNotFoundException.class, () -> {
            findUserService.getUserById(idUser);
        });
    }

    private Phone getPhone() {
        return Phone.builder().cityCode("1212").countryCode("121").number("11").id(1).build();
    }
}
