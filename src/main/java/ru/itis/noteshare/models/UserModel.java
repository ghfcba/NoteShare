package ru.itis.noteshare.models;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class UserModel {
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private LocalDateTime registeredAt;
    private String role;
}
