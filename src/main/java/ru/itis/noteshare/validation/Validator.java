package ru.itis.noteshare.validation;

import java.util.regex.Pattern;


public final class Validator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 16;

    private Validator() {
    }

    public static String requireText(String value, String fieldName, int maxLength) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " не может быть пустым");
        }

        String trimmed = value.trim();
        if (trimmed.length() > maxLength) {
            throw new ValidationException(
                    fieldName + " не должен превышать " + maxLength + " символов"
            );
        }

        return trimmed;
    }

    public static String requireEmail(String value) {
        String email = requireText(value, "Email", 100);

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Некорректный формат email");
        }

        return email;
    }

    public static String requirePassword(String value) {
        if (value == null || value.isEmpty()) {
            throw new ValidationException("Пароль не может быть пустым");
        }

        if (value.length() < PASSWORD_MIN_LENGTH) {
            throw new ValidationException(
                    "Пароль должен содержать минимум " + PASSWORD_MIN_LENGTH + " символов"
            );
        }

        if (value.length() > PASSWORD_MAX_LENGTH) {
            throw new ValidationException("Пароль не должен превышать " + PASSWORD_MAX_LENGTH + " символов");
        }

        return value;
    }

    public static Long requireId(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " не указан");
        }

        try {
            return Long.valueOf(value.trim());
        } catch (NumberFormatException e) {
            throw new ValidationException(fieldName + " должен быть числом");
        }
    }
}
