package ru.itis.noteshare.models;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class DownloadModel {
    private Long downloadId;
    private Long userId;
    private Long scoreId;
    private LocalDateTime downloadedAt;
}
