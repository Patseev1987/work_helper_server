package ru.diploma.inflate_server.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;
import ru.diploma.inflate_server.auth.domain.User;


import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.*;

@Component
public class JwtTokenService implements Serializable {

    @Value("${jwt.token.validity}")
    private long JWT_TOKEN_VALIDITY;

    @Value("${jwt.secret}")
    private String secret;

    private final String ROLE_PREFIX = "ROLE_";
    private final String ROLE = "role";



    // generate new token
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ROLE, user.getRole());
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) //* 1000 to turn millis into seconds
                .signWith(getSigningKey())
                .compact();
    }


    //check auth
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();


        String username = claims.getSubject();
        String role = claims.get(ROLE, String.class);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    //encode token with secret key
    private SecretKey getSigningKey() {
        byte[] keyBytes = Sha512DigestUtils.sha(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
