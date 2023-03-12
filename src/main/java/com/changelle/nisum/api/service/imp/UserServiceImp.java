package com.changelle.nisum.api.service.imp;

import com.changelle.nisum.api.domain.UserRepository;
import com.changelle.nisum.api.dto.ValidateDto;
import com.changelle.nisum.api.exception.MailConflictException;
import com.changelle.nisum.api.utils.ErrorMsg;
import com.changelle.nisum.api.validations.UserMail;
import com.changelle.nisum.api.validations.UserPassword;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {

    protected final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Validate email and password
     *
     * @param mail
     * @param password
     */
    public void validateData(ValidateDto validateDto) {

        UserMail.isValid(validateDto.getMail());

        UserPassword.isValid(validateDto.getPassword());

        if (!validateDto.getMail().equalsIgnoreCase(validateDto.getOldMail())) {
            if (userRepository.existsByEmail(validateDto.getMail())) {
                throw new MailConflictException(ErrorMsg.buildMsg(ErrorMsg.ERROR_MAIL_VALIDATION_EXISTS, validateDto.getMail()));
            }
        }
    }
}
