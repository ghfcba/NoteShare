package ru.itis.noteshare.models;

import lombok.*;

@Setter
@Getter
@Builder
public class ScoreGenreModel {
    private Long scoreId;
    private Long genreId;
}
