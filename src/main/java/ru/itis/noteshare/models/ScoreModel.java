package ru.itis.noteshare.models;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreModel {
    private Long scoreId;
    private String title;
    private Long userId;
    private Long composerId;
    private String filePath;
    private String difficulty;
    private String moderationStatus;
    private Timestamp uploadedAt;



    public ScoreModel(String title, String filePath, String difficulty) {
        this.title = title;
        this.filePath = filePath;
        this.difficulty = difficulty;
    }
}
