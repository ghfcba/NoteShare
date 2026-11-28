package ru.itis.noteshare.models;

import lombok.*;

@Setter
@Getter
@Builder
public class ComposerModel {
    private Long composerId;
    private String fullName;
    private String country;

    public ComposerModel(String fullName, String country) {
        this.fullName = fullName;
        this.country = country;
    }

    public ComposerModel(Long composerId, String fullName, String country) {
        this.composerId = composerId;
        this.fullName = fullName;
        this.country = country;
    }

}
