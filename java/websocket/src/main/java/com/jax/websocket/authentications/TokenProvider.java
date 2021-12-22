package com.jax.websocket.authentications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenProvider {

    private final TokenKeyProvider tokenKeyProvider;

    @Autowired
    public TokenProvider(TokenKeyProvider tokenKeyProvider) {
        this.tokenKeyProvider = tokenKeyProvider;
    }

    @Value("${authentication.token.jwt.expire}")
    private int tokenTimeout;

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);


}
