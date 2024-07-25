package ru.diploma.inflate_server.auth.dto;


public record UserDTOForSingIn(
        String username,
        String password) {
}