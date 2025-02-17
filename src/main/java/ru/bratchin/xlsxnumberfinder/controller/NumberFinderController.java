package ru.bratchin.xlsxnumberfinder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bratchin.xlsxnumberfinder.service.NthMaxFinderService;
import ru.bratchin.xlsxnumberfinder.service.XlsxReaderService;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "XLSX Number Finder", description = "API для поиска N-го максимального числа")
public class NumberFinderController {

    private final XlsxReaderService readerService;
    private final NthMaxFinderService nthMaxFinderService;

    @GetMapping("/nth-max")
    @Operation(summary = "Получить N-е максимальное число")
    public ResponseEntity<Integer> findNthLargest(
            @RequestParam @NotBlank(message = "Путь к файлу обязателен") String filePath,
            @RequestParam @Min(value = 1, message = "N должно быть положительным") Integer n
    ) {
        List<Integer> numbers = readerService.readNumbersFromXlsx(filePath);
        Integer nthMax = nthMaxFinderService.findNthMax(numbers, n);
        return ResponseEntity.ok(nthMax);
    }
}
