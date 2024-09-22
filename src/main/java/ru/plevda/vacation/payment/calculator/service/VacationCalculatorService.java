package ru.plevda.vacation.payment.calculator.service;

import org.springframework.stereotype.Service;
import ru.plevda.vacation.payment.calculator.model.VacationPaymentCalculatorResponse;
import ru.plevda.vacation.payment.calculator.model.VacationPaymentCalculatorResponse;
import ru.plevda.vacation.payment.calculator.util.PublicHolidays;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.IntStream;

@Service
public class VacationCalculatorService {

    private static final int MONTHS_IN_YEAR = 12;
    private static final double NDFL_FACTOR = 0.87;
    //Дополнительно введем константу, учитывающую вычет 13% НДФЛ
    private static final String message = ("Сумма в рублях, с вычетом НДФЛ");

    public VacationPaymentCalculatorResponse calculate(Double averageSalary, Integer vacationDays) {
        // Обычный расчет
        YearMonth currentYear = YearMonth.now();
        int totalDaysInYear = IntStream.rangeClosed(1, MONTHS_IN_YEAR)
                .map(month -> currentYear.lengthOfMonth())
                .sum();
        double averageDaysInMonth = (double) totalDaysInYear / MONTHS_IN_YEAR;
        double calculatedVacationSalary = (averageSalary / averageDaysInMonth) * vacationDays*NDFL_FACTOR;
        String formattedSalary = new DecimalFormat("#.##").format(calculatedVacationSalary);
        return new VacationPaymentCalculatorResponse(message, formattedSalary);
    }

    public VacationPaymentCalculatorResponse calculateWithHolidays(Double averageSalary, LocalDate startVacationDate, LocalDate endVacationDate) {
        // Расчет с учетом праздничных дней в указанном промежутке дат
        int workingDaysInVacation = 0;
        for (LocalDate date = startVacationDate; !date.isAfter(endVacationDate); date = date.plusDays(1)) {
            if (isWorkingDay(date)) {
                workingDaysInVacation++;
            }
        }
        double averageSalaryPerDay = averageSalary / countWorkingDaysInMonth(startVacationDate.getYear(), startVacationDate.getMonthValue());
        double totalVacationSalary = averageSalaryPerDay * workingDaysInVacation*NDFL_FACTOR;
        String formattedSalary = new DecimalFormat("#.##").format(totalVacationSalary);
        return new VacationPaymentCalculatorResponse(message, formattedSalary);
    }

    private int countWorkingDaysInMonth(int year, int month) {
        // Рассчитываем количество рабочих дней в месяце, с которого начинается отпуск
        int workingDays = 0;
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            // Проверяем, является ли день рабочим
            if (isWorkingDay(date)) {
                workingDays++;
            }
        }
        return workingDays;
    }

    private boolean isWorkingDay(LocalDate date) {
        // Проверяем, является ли указанный день рабочим (не выходным и не праздничным)
        return date.getDayOfWeek().getValue() < 6 && !PublicHolidays.HOLIDAYS.contains(date);
    }
}
