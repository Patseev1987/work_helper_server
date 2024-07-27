package ru.diploma.inflate_server.auth.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.diploma.inflate_server.auth.dto.JwtTokenResponse;
import ru.diploma.inflate_server.auth.dto.UserDTOForSingIn;
import ru.diploma.inflate_server.auth.dto.UserDTOForSingUp;
import ru.diploma.inflate_server.auth.dto.UserDTOForUpdate;
import ru.diploma.inflate_server.auth.services.JwtTokenService;
import ru.diploma.inflate_server.auth.services.LoginService;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.WorkerType;

import java.time.LocalDate;


@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenService jwtTokenService;
    private final LoginService loginService;
    private final String TOKEN_PREFIX = "Bearer ";
    private final String PASSWORD_UPDATE = "Password updated";

    //login
    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody UserDTOForSingIn userDTO) {
        var user = loginService.login(userDTO).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new JwtTokenResponse(TOKEN_PREFIX + jwtTokenService.generateToken(user)));
    }

    //register new user
    @PostMapping("/sign-up")
    public ResponseEntity<JwtTokenResponse> singUp(@RequestBody UserDTOForSingUp userDTO) {
        var newUser = loginService.register(userDTO);
        return ResponseEntity.ok(
                new JwtTokenResponse(TOKEN_PREFIX + jwtTokenService.generateToken(newUser)));
    }

    //change password
    @PutMapping("/update")
    public ResponseEntity<String> updatePassword(@RequestBody UserDTOForUpdate userDTO) {
        loginService.updatePassword(userDTO);
        return ResponseEntity.ok(PASSWORD_UPDATE);
    }

    @GetMapping("/test")
    public ResponseEntity<UserDTOForSingUp> test() {
        return ResponseEntity.ok(new UserDTOForSingUp(
                        "b",
                        "1",
                        "Bogdan",
                        "Patseev",
                        "Andreevich",
                        WorkerType.WORKER,
                        LocalDate.now(),
                        Department.DEPARTMENT_19
                )
        );
    }
}
