package ru.itis.noteshare.models;

import lombok.*;

@Setter
@Getter
@Builder
public class ComposerModel {
    private Long composerId;
    private String fullName;
    private String country;
}
