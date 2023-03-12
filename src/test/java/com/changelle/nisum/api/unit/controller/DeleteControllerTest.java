package com.changelle.nisum.api.unit.controller;

import com.changelle.nisum.api.controller.UserController;
import com.changelle.nisum.api.exception.DeleteUserNotFoundException;
import com.changelle.nisum.api.exception.UserNotFoundException;
import com.changelle.nisum.api.service.DeleteUserService;
import com.changelle.nisum.api.utils.ErrorMsg;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DeleteControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private DeleteUserService deleteUserService;

    @Test
    void test_deleteUser_byId() {
        int idUser = 1;

        userController.deleteUser(idUser);
        //Assert
        verify(deleteUserService, times(1)).deleteUser(Mockito.anyInt());
    }

    @Test
    void testCheckIfDeleteUser_Error_DeleteUserNotFoundException() {
        //Data preparation
        int idUser = 1;

        doThrow(new DeleteUserNotFoundException(ErrorMsg.ERROR_DELETE_USER_DB))
                .when(deleteUserService).deleteUser(Mockito.anyInt());

        Assert.assertThrows(DeleteUserNotFoundException.class, () -> {
            userController.deleteUser(idUser);
        });
        //Assert
        verify(deleteUserService, times(1)).deleteUser(anyInt());
    }

    @Test
    void testCheckIfDeleteUser_Error_UserNotFoundException() {
        //Data preparation
        int idUser = 1;

        doThrow(new UserNotFoundException(ErrorMsg.ERROR_USER_NOT_FOUND))
                .when(deleteUserService).deleteUser(Mockito.anyInt());

        Assert.assertThrows(UserNotFoundException.class, () -> {
            userController.deleteUser(idUser);
        });
        //Assert
        verify(deleteUserService, times(1)).deleteUser(anyInt());
    }

}
