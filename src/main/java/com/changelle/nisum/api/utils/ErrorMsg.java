package com.changelle.nisum.api.utils;

public class ErrorMsg {
    public final static String ERROR_CREATE_USER_DB = "Error creating user in database: ";
    public final static String ERROR_MAIL_VALIDATION_EXISTS = "The mail already exists: ";
    public final static String ERROR_MAIL_VALIDATION_FORMAT = "The email is not in the correct format: ";
    public final static String ERROR_PASSWORD_VALIDATION_FORMAT = "Password format error: ";
    public final static String ERROR_USER_NOT_FOUND = "User not found: ";
    public final static String ERROR_DELETE_USER_DB = "Error deleting user in database";
    public final static String ERROR_UPDATE_USER_DB = "Error updating user in database: ";


    private ErrorMsg() {
    }

    public static String buildMsg(String msgError, String msgData) {
        return new StringBuilder()
                .append(msgError)
                .append(msgData)
                .toString();
    }
}
