package com.changelle.nisum.api.controller;

import com.changelle.nisum.api.dto.*;
import com.changelle.nisum.api.service.CreateUserService;
import com.changelle.nisum.api.service.DeleteUserService;
import com.changelle.nisum.api.service.FindUserService;
import com.changelle.nisum.api.service.UpdateUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Validated
@RestController
@Api(value = "Control to operate users")
@RequestMapping("/api-nisum/")
public class UserController {

    private final CreateUserService createUserService;
    private final FindUserService findUserService;
    private final DeleteUserService deleteUserService;
    private final UpdateUserService updateUserService;

    public UserController(CreateUserService createUserService, FindUserService findUserService, DeleteUserService deleteUserService, UpdateUserService updateUserService) {
        this.createUserService = createUserService;
        this.findUserService = findUserService;
        this.deleteUserService = deleteUserService;
        this.updateUserService = updateUserService;
    }

    @PostMapping("/v1/user")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "A user is create", notes = "A new user is loaded.")
    @ApiResponses(
            {
                    @ApiResponse(code = HttpServletResponse.SC_OK, message = "addUser successfully"),
                    @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "There was an unexpected error on the server."),
                    @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad request error"),
                    @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Information not found")
            })
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto userRequest, @RequestHeader("Authorization") String bearerToken) {
        return createUserService.createUser(userRequest, bearerToken);
    }

    @GetMapping("/v1/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "get user by id", notes = "find user")
    @ApiResponses(
            {
                    @ApiResponse(code = HttpServletResponse.SC_OK, message = "getUserById successfully"),
                    @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "There was an unexpected error on the server."),
                    @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad request error"),
                    @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Information not found")
            })
    public FindUserResponseDto getUserById(@PathVariable @NonNull int id) {
        return findUserService.getUserById(id);
    }

    @DeleteMapping("/v1/user/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ApiOperation(value = "A user is eliminated by id", notes = "A user is eliminated by id.")
    @ApiResponses(
            {
                    @ApiResponse(code = HttpServletResponse.SC_ACCEPTED, message = "Delete user successfully."),
                    @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "There was an unexpected error on the server."),
                    @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Error data"),
                    @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Information not found")
            })
    public void deleteUser(@PathVariable @NonNull int id) {
        deleteUserService.deleteUser(id);
    }

    @PatchMapping("/v1/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "A user is updated", notes = "The user is updated.")
    @ApiResponses(
            {
                    @ApiResponse(code = HttpServletResponse.SC_OK, message = "updateUserById successfully"),
                    @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "There was an unexpected error on the server."),
                    @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad request error"),
                    @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Information not found")
            })
    public UpdateUserResponseDto updateUserById(@PathVariable @NonNull int id, @RequestBody UpdateUserRequestDto updateUserRequestDto, @RequestHeader("Authorization") String bearerToken) {
        return updateUserService.updateUserById(id, updateUserRequestDto, bearerToken);
    }
}
