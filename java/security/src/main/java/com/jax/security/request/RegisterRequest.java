package com.jax.security.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username is not empty")
    private String username;

    @NotBlank(message = "Password is not empty")
    private String password;

    @Email(message = "Email is invalid")
    private String email;

}
