package ru.itis.noteshare.models;

import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
public class ScoreModel {
    private Long scoreId;
    private String title;
    private Long userId;
    private Long composerId;
    private String filePath;
    private String difficulty;
    private String moderationStatus;
    private LocalDateTime uploadedAt;
}
