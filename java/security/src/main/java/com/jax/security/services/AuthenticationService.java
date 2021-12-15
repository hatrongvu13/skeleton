package com.jax.security.services;

import com.jax.security.request.LoginRequest;
import com.jax.security.request.RegisterRequest;
import com.jax.security.response.TokenResponse;

public interface AuthenticationService {
    TokenResponse login(LoginRequest loginRequest);

    TokenResponse register(RegisterRequest registerRequest);
}
