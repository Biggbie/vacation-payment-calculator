package ru.plevda.vacation.payment.calculator.util;

import java.time.LocalDate;
import java.util.List;

public class PublicHolidays {
    private static final int CURRENT_YEAR = LocalDate.now().getYear();

    public static final List<LocalDate> HOLIDAYS = List.of(
            LocalDate.of(CURRENT_YEAR, 1, 1),  // 1 января - Новый год
            LocalDate.of(CURRENT_YEAR, 3, 8),  // 8 марта - Международный женский день
            LocalDate.of(CURRENT_YEAR, 2, 23), // 23 февраля - День защитника Отечества
            LocalDate.of(CURRENT_YEAR, 5, 1),  // 1 мая - Праздник весны и труда
            LocalDate.of(CURRENT_YEAR, 5, 9),  // 9 мая - День Победы
            LocalDate.of(CURRENT_YEAR, 6, 12), // 12 июня - День России
            LocalDate.of(CURRENT_YEAR, 11, 4)   // 4 ноября - День народного единства
    );
}
