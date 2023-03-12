package com.changelle.nisum.api.unit.exception;

import com.changelle.nisum.api.exception.*;
import com.changelle.nisum.api.exception.api.ErrorResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ApiExceptionHandlerTest {

    @InjectMocks
    ApiExceptionHandler apiExceptionHandler;

    /**
     * test status code and message - handleCreateUserNotFoundException
     */
    @Test
    public void checkStatusCodeAndMessageHandleCreateUserNotFoundException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = apiExceptionHandler.handleCreateUserNotFoundException(new CreateUserNotFoundException("DataBaseException"));

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), errorResponseResponseEntity.getStatusCodeValue());
        Assert.assertEquals("Not Found", errorResponseResponseEntity.getStatusCode().getReasonPhrase());
        Assert.assertEquals("DataBaseException", errorResponseResponseEntity.getBody().getMessage());
    }

    /**
     * test status code and message - handleConflictException
     */
    @Test
    public void checkStatusCodeAndMessageHandleConflictException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = apiExceptionHandler.handleConflictException(new MailConflictException("ConflictException"));

        Assert.assertEquals(HttpStatus.CONFLICT.value(), errorResponseResponseEntity.getStatusCodeValue());
        Assert.assertEquals("Conflict", errorResponseResponseEntity.getStatusCode().getReasonPhrase());
        Assert.assertEquals("ConflictException", errorResponseResponseEntity.getBody().getMessage());
    }

    /**
     * test status code and message - handleMailInvalidFoundException
     */
    @Test
    public void checkStatusCodeAndMessageHandleMailInvalidFoundException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = apiExceptionHandler.handleMailInvalidFoundException(new MailInvalidFoundException("MailInvalidFoundException"));

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponseResponseEntity.getStatusCodeValue());
        Assert.assertEquals("Bad Request", errorResponseResponseEntity.getStatusCode().getReasonPhrase());
        Assert.assertEquals("MailInvalidFoundException", errorResponseResponseEntity.getBody().getMessage());
    }

    /**
     * test status code and message - handlePasswordInvalidFoundException
     */
    @Test
    public void checkStatusCodeAndMessageHandlePasswordInvalidFoundException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = apiExceptionHandler.handlePasswordInvalidFoundException(new PasswordInvalidFoundException("PasswordInvalidFoundException"));

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponseResponseEntity.getStatusCodeValue());
        Assert.assertEquals("Bad Request", errorResponseResponseEntity.getStatusCode().getReasonPhrase());
        Assert.assertEquals("PasswordInvalidFoundException", errorResponseResponseEntity.getBody().getMessage());
    }

    /**
     * test status code and message - handleUserNotFoundException
     */
    @Test
    public void checkStatusCodeAndMessageHandleUserNotFoundException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = apiExceptionHandler.handleUserNotFoundException(new UserNotFoundException("UserNotFoundException"));

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponseResponseEntity.getStatusCodeValue());
        Assert.assertEquals("Bad Request", errorResponseResponseEntity.getStatusCode().getReasonPhrase());
        Assert.assertEquals("UserNotFoundException", errorResponseResponseEntity.getBody().getMessage());
    }

    /**
     * test status code and message - handleUserNotFoundException
     */
    @Test
    public void checkStatusCodeAndMessageHandleDeleteUserNotFoundException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = apiExceptionHandler.handleDeleteUserNotFoundException(new DeleteUserNotFoundException("DeleteUserNotFoundException"));

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponseResponseEntity.getStatusCodeValue());
        Assert.assertEquals("Bad Request", errorResponseResponseEntity.getStatusCode().getReasonPhrase());
        Assert.assertEquals("DeleteUserNotFoundException", errorResponseResponseEntity.getBody().getMessage());
    }

    /**
     * test status code and message - handleUpdateUserNotFoundException
     */
    @Test
    public void checkStatusCodeAndMessageHandleUpdateUserNotFoundException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = apiExceptionHandler.handleUpdateUserNotFoundException(new UpdateUserNotFoundException("UpdateUserNotFoundException"));

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponseResponseEntity.getStatusCodeValue());
        Assert.assertEquals("Bad Request", errorResponseResponseEntity.getStatusCode().getReasonPhrase());
        Assert.assertEquals("UpdateUserNotFoundException", errorResponseResponseEntity.getBody().getMessage());
    }
}
