package com.jax.websocket.services.impl;

import com.jax.websocket.request.LoginRequest;
import com.jax.websocket.response.Token;
import com.jax.websocket.services.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public Token login(LoginRequest loginRequest) {
        return null;
    }
}
