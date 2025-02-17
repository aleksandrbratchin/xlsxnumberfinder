package ru.bratchin.xlsxnumberfinder.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NthMaxFinderService {

    /**
     * Находит N-ое максимальное число из списка целых чисел.
     *
     * @param nums Список целых чисел
     * @param n Порядковый номер максимального значения
     * @return N-ое максимальное число или null, если оно отсутствует
     * @throws IllegalArgumentException Если входные данные некорректны (например, список пуст или N недопустимо)
     */
    public Integer findNthMax(List<Integer> nums, int n) {
        if (nums == null || nums.size() < n || n <= 0) {
            throw new IllegalArgumentException("Некорректные входные данные");
        }

        List<Integer> top = new ArrayList<>(Collections.nCopies(n, null));

        for (int num : nums) {
            for (int i = 0; i < n; i++) {
                if (top.get(i) == null || num > top.get(i)) {
                    for (int j = n - 1; j > i; j--) {
                        top.set(j, top.get(j - 1));
                    }
                    top.set(i, num);
                    break;
                }
            }
        }

        return top.get(n - 1);
    }
}
