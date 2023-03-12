package com.changelle.nisum.api.validations;

import com.changelle.nisum.api.exception.PasswordInvalidFoundException;
import com.changelle.nisum.api.utils.ErrorMsg;
import org.springframework.beans.factory.annotation.Value;

public class UserPassword extends Validations {
    @Value("${password.expression}")
    private static String passwordExpression = "(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{8,15}";


    public static void isValid(String password) {
        if (!isValidByExpression(password, passwordExpression)) {
            throw new PasswordInvalidFoundException(ErrorMsg.buildMsg(ErrorMsg.ERROR_PASSWORD_VALIDATION_FORMAT, password));
        }
    }
}
