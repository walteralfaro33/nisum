package com.changelle.nisum.api.validations;

import java.util.regex.Pattern;

public class Validations {
    /**
     * Validates the value according to the expression
     *
     * @param value
     * @param expression
     * @return boolean
     */
    static boolean isValidByExpression(String value, String expression) {
        Pattern pattern = Pattern.compile("^" + expression + "$");
        if (value == null) {
            return false;
        }
        return pattern.matcher(value).matches();
    }
}
