package com.jax.security.services.impl;

import com.jax.security.DTO.TokenUser;
import com.jax.security.authentications.TokenProvider;
import com.jax.security.authentications.UserPrincipal;
import com.jax.security.entities.User;
import com.jax.security.repositories.UserRepository;
import com.jax.security.request.LoginRequest;
import com.jax.security.request.RegisterRequest;
import com.jax.security.response.TokenResponse;
import com.jax.security.services.AuthenticationService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private TokenProvider tokenProvider;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private DozerBeanMapper mapper;

    @Autowired
    public AuthenticationServiceImpl(TokenProvider tokenProvider,
                                     UserRepository userRepository,
                                     PasswordEncoder encoder,
                                     DozerBeanMapper mapper) {
        super();
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);

        if (Objects.isNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown account, please check again !");
        }

        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Username or password is not matches !");
        }
        return new TokenResponse(this.authority(user));
    }

    @Transactional
    @Override
    public TokenResponse register(RegisterRequest registerRequest) {
        User user = mapper.map(registerRequest, User.class);
        user.setScopes(new ArrayList<>());
        user.setPassword(encoder.encode(registerRequest.getPassword()));

        user = userRepository.save(user);

        return new TokenResponse(this.authority(user));
    }

    private String authority(User user) {
        TokenUser tokenUser = mapper.map(user, TokenUser.class);
        return tokenProvider.issueToken(UserPrincipal.create(tokenUser));
    }
}
