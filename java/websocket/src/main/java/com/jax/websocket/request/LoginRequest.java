package com.jax.websocket.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Username is not empty")
    private String username;

    @NotBlank(message = "Password is not empty")
    private String password;
}
