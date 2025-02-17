package ru.bratchin.xlsxnumberfinder.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NthMaxFinderServiceTest {

    private final NthMaxFinderService service = new NthMaxFinderService();

    @Test
    public void findNthMax_ReturnsCorrectValue() {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        Integer result = service.findNthMax(numbers, 3);
        assertThat(result).isEqualTo(30);
    }

    @Test
    public void findNthMax_ThrowsExceptionForInvalidN() {
        List<Integer> numbers = Arrays.asList(5, 15, 25);
        assertThatThrownBy(() -> service.findNthMax(numbers, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Некорректные входные данные");
    }

    @Test
    public void findNthMax_ReturnsNullForSingleValue() {
        List<Integer> numbers = Collections.singletonList(100);
        Integer result = service.findNthMax(numbers, 1);
        assertThat(result).isEqualTo(100);
    }

    @Test
    public void findNthMax_ThrowsExceptionForEmptyList() {
        List<Integer> numbers = Collections.emptyList();
        assertThatThrownBy(() -> service.findNthMax(numbers, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}