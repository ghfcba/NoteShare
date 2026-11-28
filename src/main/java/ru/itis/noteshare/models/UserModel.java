package ru.itis.noteshare.models;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private Timestamp registeredAt;
    private String role;

    public UserModel(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
