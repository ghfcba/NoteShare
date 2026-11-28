package ru.itis.noteshare.validation;

/**
 * Исключение для ошибок валидации входных данных.
 * Сообщение показывается пользователю, поэтому текст должен быть понятным.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
