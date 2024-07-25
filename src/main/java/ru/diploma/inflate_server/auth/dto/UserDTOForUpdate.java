package ru.diploma.inflate_server.auth.dto;


public record UserDTOForUpdate(
        String username,
        String oldPassword,
        String newPassword) {
}