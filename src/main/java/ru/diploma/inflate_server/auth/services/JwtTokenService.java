package ru.diploma.inflate_server.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;
import ru.diploma.inflate_server.auth.domain.User;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.services.WorkerService;


import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.*;


@Component
public class JwtTokenService implements Serializable {

    @Value("${jwt.token.validity}")
    private long JWT_TOKEN_VALIDITY;

    @Value("${jwt.secret}")
    private String secret;
    private final WorkerService workerService;
    private final String ROLE_PREFIX = "ROLE_";
    private final String ROLE = "role";
    private final String WORKER_ID = "worker_id";
    private final String STORAGE_WORKER_ID = "storage_worker_id";
    private final String DEPARTMENT = "department";

    public JwtTokenService(WorkerService workerService) {
        this.workerService = workerService;
    }


    // generate new token
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ROLE, user.getRole());
        claims.put(WORKER_ID, user.getId());
        Worker worker = workerService.getWorkerById(user.getId());
        Long storageWorkerId = workerService.getStorageWorkerByDepartment(worker.getDepartment()).getId();
        claims.put(STORAGE_WORKER_ID, storageWorkerId);
        claims.put(DEPARTMENT, worker.getDepartment());
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) //* 1000 to turn millis into seconds
                .signWith(getSigningKey())
                .compact();
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().decryptWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
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
