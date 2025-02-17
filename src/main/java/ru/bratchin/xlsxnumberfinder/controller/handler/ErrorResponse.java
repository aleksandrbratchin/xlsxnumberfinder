package ru.bratchin.xlsxnumberfinder.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Класс для формирования ответа об ошибке.
 * Содержит информацию об ошибке, времени ее возникновения и HTTP-статусе.
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {

    /**
     * Сообщение об ошибке, поясняющее причину сбоя.
     */
    private String message;

    /**
     * Время возникновения ошибки.
     */
    private LocalDateTime timestamp;

    /**
     * HTTP-статус, связанный с ошибкой.
     */
    private HttpStatus status;

    /**
     * Конструктор для создания ответа с текущей временной меткой.
     *
     * @param message сообщение об ошибке
     * @param status HTTP-статус ошибки
     */
    public ErrorResponse(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
