package com.jax.security.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TokenUser {

	private String username;

	private String email;

	private List<String> scopes;

}
