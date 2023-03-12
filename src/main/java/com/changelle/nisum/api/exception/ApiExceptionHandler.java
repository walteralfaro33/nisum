package com.changelle.nisum.api.exception;


import com.changelle.nisum.api.exception.api.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private ErrorResponse getBuild(String localizedMessage) {
        return ErrorResponse.builder().message(localizedMessage).build();
    }

    @ExceptionHandler({CreateUserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleCreateUserNotFoundException(CreateUserNotFoundException ex) {
        ErrorResponse errorResponse = getBuild(ex.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MailConflictException.class})
    public ResponseEntity<ErrorResponse> handleConflictException(MailConflictException ex) {
        ErrorResponse errorResponse = getBuild(ex.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({MailInvalidFoundException.class})
    public ResponseEntity<ErrorResponse> handleMailInvalidFoundException(MailInvalidFoundException ex) {
        ErrorResponse errorResponse = getBuild(ex.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PasswordInvalidFoundException.class})
    public ResponseEntity<ErrorResponse> handlePasswordInvalidFoundException(PasswordInvalidFoundException ex) {
        ErrorResponse errorResponse = getBuild(ex.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = getBuild(ex.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DeleteUserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleDeleteUserNotFoundException(DeleteUserNotFoundException ex) {
        ErrorResponse errorResponse = getBuild(ex.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UpdateUserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUpdateUserNotFoundException(UpdateUserNotFoundException ex) {
        ErrorResponse errorResponse = getBuild(ex.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
