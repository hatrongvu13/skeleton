package com.jax.websocket.services;

import com.jax.websocket.request.LoginRequest;
import com.jax.websocket.response.Token;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    Token login(LoginRequest loginRequest);
}
