package ru.bratchin.xlsxnumberfinder.service;

import org.junit.jupiter.api.Test;
import ru.bratchin.xlsxnumberfinder.exception.XlsxReadException;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class XlsxReaderServiceTest {

    private final XlsxReaderService service = new XlsxReaderService();

    @Test
    void shouldReadNumbersFromXlsx() throws IOException {
        List<Integer> result = service.readNumbersFromXlsx("src/test/resources/valid.xlsx");
        assertThat(result).isNotEmpty().containsExactly(5, 10, 15);
    }

    @Test
    void shouldThrowWhenFileNotFound() {
        assertThatThrownBy(() -> service.readNumbersFromXlsx("src/test/resources/nonexistent.xlsx"))
                .isInstanceOf(XlsxReadException.class)
                .hasMessageContaining("Ошибка открытия файла");
    }

    @Test
    void shouldThrowWhenRowMissing() {
        assertThatThrownBy(() -> service.readNumbersFromXlsx("src/test/resources/missingRow.xlsx"))
                .isInstanceOf(XlsxReadException.class)
                .hasMessageContaining("Ошибка: Отсутствует строка");
    }

    @Test
    void shouldThrowWhenCellHasInvalidValue() {
        assertThatThrownBy(() -> service.readNumbersFromXlsx("src/test/resources/invalidCell.xlsx"))
                .isInstanceOf(XlsxReadException.class)
                .hasMessageContaining("Ошибка: Ячейка содержит нечисловое значение");
    }
}