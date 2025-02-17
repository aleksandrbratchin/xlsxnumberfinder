package ru.bratchin.xlsxnumberfinder.exception;

/**
 * Исключение, возникающее при ошибке чтения XLSX-файла.
 */
public class XlsxReadException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением.
     */
    public XlsxReadException(String message) {
        super(message);
    }

    /**
     * Создает новое исключение с указанным сообщением и причиной.
     */
    public XlsxReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
