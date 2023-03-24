package com.changelle.nisum.api.unit.service;

import com.changelle.nisum.api.domain.repository.UserRepository;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.exception.DeleteUserNotFoundException;
import com.changelle.nisum.api.exception.UserNotFoundException;
import com.changelle.nisum.api.service.imp.DeleteUserServiceImpl;
import com.changelle.nisum.api.utils.ErrorMsg;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
class DeleteUserServiceImpTest {

    @InjectMocks
    DeleteUserServiceImpl deleteUserService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public int idUser = 1;

    @Test
    void testDeleteUser_thenDeleteUser() {
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(User.builder().build()));
        deleteUserService.deleteUser(idUser);
        verify(userRepository, times(1)).delete(Mockito.any());
    }

    @Test
    void testDeleteUser_thenUserNotFoundException() {
        when(userRepository.findById(Mockito.any())).thenThrow(new UserNotFoundException("UserNotFoundException"));
        Assert.assertThrows(UserNotFoundException.class, () -> {
            deleteUserService.deleteUser(idUser);
        });
    }

    @Test
    void testDeleteUser_thenDeleteUserNotFoundException() {
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(User.builder().build()));

        doThrow(new DeleteUserNotFoundException(ErrorMsg.ERROR_DELETE_USER_DB))
                .when(userRepository).delete(Mockito.any());

        Assert.assertThrows(DeleteUserNotFoundException.class, () -> {
            deleteUserService.deleteUser(idUser);
        });
    }
}
