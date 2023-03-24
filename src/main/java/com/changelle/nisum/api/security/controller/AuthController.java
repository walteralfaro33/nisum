package com.changelle.nisum.api.security.controller;


import com.changelle.nisum.api.security.model.JwtRequest;
import com.changelle.nisum.api.security.model.JwtResponse;
import com.changelle.nisum.api.security.service.UserService;
import com.changelle.nisum.api.security.utility.JWTUtility;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-nisum/")
public class AuthController {

    private JWTUtility jwtUtility;

    private AuthenticationManager authenticationManager;

    private UserService userService;

    public AuthController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/v1/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }
}
