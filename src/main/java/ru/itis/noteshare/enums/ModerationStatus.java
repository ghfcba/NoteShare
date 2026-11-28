package ru.itis.noteshare.enums;

import ru.itis.noteshare.validation.ValidationException;

/**
 * Значения соответствуют CHECK (moderation_status IN ('pending', 'approved', 'rejected'))
 * в схеме.
 */
public enum ModerationStatus {
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private final String value;

    ModerationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ModerationStatus fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("Статус модерации не указан");
        }

        String normalized = value.trim();
        for (ModerationStatus status : values()) {
            if (status.value.equalsIgnoreCase(normalized)
                    || status.name().equalsIgnoreCase(normalized)) {
                return status;
            }
        }

        throw new ValidationException("Недопустимый статус модерации: " + normalized);
    }
}
