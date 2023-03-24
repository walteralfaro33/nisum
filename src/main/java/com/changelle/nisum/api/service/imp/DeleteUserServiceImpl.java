package com.changelle.nisum.api.service.imp;

import com.changelle.nisum.api.domain.repository.UserRepository;
import com.changelle.nisum.api.domain.model.User;
import com.changelle.nisum.api.exception.DeleteUserNotFoundException;
import com.changelle.nisum.api.exception.UserNotFoundException;
import com.changelle.nisum.api.service.DeleteUserService;
import com.changelle.nisum.api.utils.ErrorMsg;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserServiceImpl implements DeleteUserService {
    private final UserRepository userRepository;

    public DeleteUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Delete user by id
     *
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ErrorMsg.buildMsg(ErrorMsg.ERROR_USER_NOT_FOUND, String.valueOf(id))));
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            throw new DeleteUserNotFoundException(ErrorMsg.buildMsg(ErrorMsg.ERROR_DELETE_USER_DB, e.getMessage()));
        }
    }
}
