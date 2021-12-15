package com.jax.security.authentications;

import com.jax.security.DTO.TokenUser;
import com.jax.security.utils.EncryptionUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenProvider {

    @Autowired
    TokenKeyProvider tokenKeyProvider;

    @Value("${authentication.token.jwt.expire}")
    private int tokenTimeout;

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Value("${authentication.token.encrypt.password}")
    private String encryptPassword;

    @Value("${authentication.token.encrypt.salt}")
    private String salt;


    @SneakyThrows
    public UserPrincipal getUserPrincipalFromJWT(String token) {

        SecretKey SK = EncryptionUtil.getKeyFromPassword(encryptPassword, salt);


        Claims claims = this.decodeJWT(token);
        @SuppressWarnings("unchecked")
        List<GrantedAuthority> authorities = ((List<String>) claims.get("scopes")).stream().map((scope) -> new SimpleGrantedAuthority(scope)).collect(Collectors.toList());

        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setAuthorities(authorities);
        userPrincipal.setId(claims.getSubject());
        userPrincipal.setTokenUser(new TokenUser());

        if (claims.get("username") != null) {
            userPrincipal.getTokenUser().setUsername(EncryptionUtil.decryptWithPrefixIV((String) claims.get("username"), SK));
        }

        if (claims.get("email") != null) {
            userPrincipal.getTokenUser().setEmail(EncryptionUtil.decryptWithPrefixIV((String) claims.get("email"), SK));
        }

        return userPrincipal;
    }

    public Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(tokenKeyProvider.getPublicKey())
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    @SneakyThrows
    public String issueToken(UserPrincipal userPrincipal) {

//		UUID tokenUUID = UUID.randomUUID();

        SecretKey SK = EncryptionUtil.getKeyFromPassword(encryptPassword, salt);
        byte[] iv = EncryptionUtil.getRandomNonce();
//		String algorithm = "AES/CBC/PKCS5Padding";


        Claims claims = Jwts.claims().setSubject(userPrincipal.getId());

        claims.put("scopes", userPrincipal.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        if (userPrincipal.getTokenUser().getUsername() != null) {
            claims.put("username", EncryptionUtil.encryptWithPrefixIV(userPrincipal.getTokenUser().getUsername(), SK, iv));
        }
        if (userPrincipal.getTokenUser().getEmail() != null) {
            claims.put("email", EncryptionUtil.encryptWithPrefixIV(userPrincipal.getTokenUser().getEmail(), SK, iv));
        }

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.RS256, tokenKeyProvider.getPrivateKey())
                .compressWith(CompressionCodecs.GZIP)
                .setExpiration(new Date(System.currentTimeMillis() + tokenTimeout * 1000))
                .compact();

        return token;
    }

}
