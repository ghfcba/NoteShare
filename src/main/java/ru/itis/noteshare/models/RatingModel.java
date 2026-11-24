package ru.itis.noteshare.models;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class RatingModel {
    private Long ratingId;
    private Long userId;
    private Long scoreId;
    private int rating;
    private LocalDateTime ratedAt;
}
