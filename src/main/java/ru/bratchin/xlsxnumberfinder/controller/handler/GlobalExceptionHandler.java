package ru.bratchin.xlsxnumberfinder.controller.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bratchin.xlsxnumberfinder.exception.XlsxReadException;

/**
 * Глобальный обработчик исключений для приложения.
 * Этот класс предоставляет единый формат ответа на различные типы ошибок
 * с использованием корректных HTTP-статусов.
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает ошибки валидации параметров метода.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Ошибка валидации: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Ошибка валидации: " + ex.getMessage()));
    }

    /**
     * Обрабатывает ошибки открытия XLSX-файла.
     */
    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOperationException(InvalidOperationException ex) {
        log.error("Ошибка открытия файла XLSX: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Ошибка открытия XLSX: " + ex.getMessage()));
    }

    /**
     * Обрабатывает ошибки некорректных аргументов.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Ошибка аргументов: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Ошибка аргументов: " + ex.getMessage()));
    }

    /**
     * Обрабатывает ошибки чтения XLSX-файла.
     */
    @ExceptionHandler(XlsxReadException.class)
    public ResponseEntity<ErrorResponse> handleXlsxReadException(XlsxReadException ex) {
        log.error("Ошибка при чтении XLSX: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Ошибка при чтении XLSX: " + ex.getMessage()));
    }

    /**
     * Обрабатывает ошибки отсутствия обязательных параметров запроса.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestParameter(MissingServletRequestParameterException ex) {
        log.error("Отсутствует обязательный параметр: {}", ex.getParameterName(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Отсутствует обязательный параметр: " + ex.getParameterName()));
    }

    /**
     * Обрабатывает непредвиденные внутренние ошибки сервера.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        log.error("Внутренняя ошибка сервера: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера. Пожалуйста, попробуйте позже."));
    }
}
