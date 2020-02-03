package ru.ryazan.senatorshop.web.pagination;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PaginationNumbers {
    private int delta;
    List<Integer> range;
    List<Integer> rangeWithDots;

    public PaginationNumbers() {
        this.delta = 3;

    }

    public List<Integer> getPaginationListNumbers(int cPage, int lPage) {
        int left = cPage - delta;
        int right = cPage + delta + 1;
        range = new ArrayList<>();
        rangeWithDots = new ArrayList<>();
        int line = 0;

        for (int i = 1; i <= lPage; i++) {
            if (i == 1 || i == lPage || i >= left && i <= right) {
                range.add(i);
            }
        }

        for (int i : range) {
            if (line > 0) {
                if ((i - line) == 2) {
                    rangeWithDots.add(line + 1);
                } else if ((i - line) != 1) {
                    rangeWithDots.add(-1);
                }
            }
            rangeWithDots.add(i);
            line = i;
        }
        return rangeWithDots;
    }
}
