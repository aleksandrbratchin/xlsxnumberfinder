package ru.bratchin.xlsxnumberfinder.service;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.bratchin.xlsxnumberfinder.exception.XlsxReadException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для чтения чисел из XLSX-файла.
 */
@Service
public class XlsxReaderService {

    /**
     * Считывает целые числа из первого столбца XLSX-файла.
     *
     * @param filePath Путь к XLSX-файлу
     * @return Список считанных чисел
     * @throws XlsxReadException При ошибках чтения файла или данных
     */
    public List<Integer> readNumbersFromXlsx(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(filePath)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            for (int i = 0; i <= rowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    throw new XlsxReadException("Ошибка: Отсутствует строка " + i);
                }
                XSSFCell cell = row.getCell(0);
                try {
                    numbers.add((int) cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    throw new XlsxReadException("Ошибка: Ячейка содержит нечисловое значение в строке " + i, e);
                } catch (NullPointerException e) {
                    throw new XlsxReadException("Ошибка: Отсутствует значение в ячейке строки " + i, e);
                }
            }
        } catch (IOException e) {
            throw new XlsxReadException("Ошибка при чтении файла: " + e.getMessage(), e);
        } catch (InvalidOperationException e) {
            throw new XlsxReadException("Ошибка открытия файла XLSX: " + e.getMessage(), e);
        }
        return numbers;
    }
}
