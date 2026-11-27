package ru.itis.noteshare.dto;

import lombok.Builder;
import lombok.Getter;
import ru.itis.noteshare.enums.Difficulity;

import java.time.LocalDate;

@Builder
@Getter
public class ScoreDto {
    private String title;
    private String composerName;
    private String difficulity;
    private LocalDate date;
    private Long scoreId;
}
