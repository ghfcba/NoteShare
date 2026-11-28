package ru.itis.noteshare.enums;

import lombok.Getter;
import ru.itis.noteshare.validation.ValidationException;

@Getter
public enum Difficulity {
    EASY("easy", "Лёгкая"),
    MEDIUM("medium", "Средняя"),
    HARD("hard", "Сложная");

    private final String value;
    private final String label;

    Difficulity(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Difficulity fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("Сложность не указана");
        }

        String normalized = value.trim();
        for (Difficulity difficulty : values()) {
            if (difficulty.value.equalsIgnoreCase(normalized)
                    || difficulty.name().equalsIgnoreCase(normalized)) {
                return difficulty;
            }
        }

        throw new ValidationException("Сложность должна быть одной из: easy, medium, hard");
    }
}
