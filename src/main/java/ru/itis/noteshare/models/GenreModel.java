package ru.itis.noteshare.models;

import lombok.*;

@Setter
@Getter
@Builder
public class GenreModel {
    private Long genreId;
    private String name;
}
