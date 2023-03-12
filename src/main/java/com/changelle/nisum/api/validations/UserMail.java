package com.changelle.nisum.api.validations;

import com.changelle.nisum.api.exception.MailInvalidFoundException;
import com.changelle.nisum.api.utils.ErrorMsg;
import org.springframework.beans.factory.annotation.Value;

public class UserMail extends Validations {
    @Value("${mail.expression}")
    private static String mailExpression = "[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}";

    public static void isValid(String mail) {
        if (!isValidByExpression(mail, mailExpression)) {
            throw new MailInvalidFoundException(ErrorMsg.buildMsg(ErrorMsg.ERROR_MAIL_VALIDATION_FORMAT, mail));
        }
    }
}
