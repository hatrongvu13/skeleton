package com.jax.security.controllers;

import com.jax.security.DTO.TokenUser;
import com.jax.security.authentications.UserPrincipal;
import com.jax.security.request.LoginRequest;
import com.jax.security.request.RegisterRequest;
import com.jax.security.response.TokenResponse;
import com.jax.security.services.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(tags = "Authentication Controller")
@RestController
@ControllerAdvice
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = 3600)
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Login with username and password")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register new user account")
    public TokenResponse register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "Get info of current user login")
    public TokenUser info(@ApiIgnore @AuthenticationPrincipal UserPrincipal currentUser) {
        return authenticationService.info(currentUser);
    }
}
