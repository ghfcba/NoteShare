package ru.itis.noteshare.models;

import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreModel {
    private Long scoreId;
    private String title; //
    private Long userId; //
    private Long composerId; //
    private String filePath; //
    private String difficulty; //
    private String moderationStatus;
    private LocalDateTime uploadedAt;



    public ScoreModel(String title, String filePath, String difficulty) {
        this.title = title;
        this.filePath = filePath;
        this.difficulty = difficulty;
    }
}
