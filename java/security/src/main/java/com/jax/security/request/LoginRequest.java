package com.jax.security.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "Username is not empty")
    private String username;
    @NotBlank(message = "Password it not empty")
    private String password;
}
