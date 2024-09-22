package ru.plevda.vacation.payment.calculator.service;

import org.junit.jupiter.api.Test;
import ru.plevda.vacation.payment.calculator.model.VacationPaymentCalculatorResponse;
import ru.plevda.vacation.payment.calculator.service.VacationCalculatorService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VacationCalculatorServiceTest {

    @Test
    public void testCalculate_NotNull() {
        VacationCalculatorService calculatorService = new VacationCalculatorService();
        Double averageSalary = 50000.0;
        Integer vacationDays = 10;
        VacationPaymentCalculatorResponse response = calculatorService.calculate(averageSalary, vacationDays);
        assertNotNull(response);
        assertNotNull(response.getCalculatedVacationSalary());
    }

    @Test
    public void testCalculateWithHolidays_NotNull() {
        VacationCalculatorService calculatorService = new VacationCalculatorService();
        Double averageSalary = 50000.0;
        LocalDate startVacationDate = LocalDate.of(2024, 3, 1);
        LocalDate endVacationDate = LocalDate.of(2024, 3, 10);
        VacationPaymentCalculatorResponse response = calculatorService.calculateWithHolidays(averageSalary, startVacationDate, endVacationDate);
        assertNotNull(response);
        assertNotNull(response.getCalculatedVacationSalary());
    }

    @Test
    public void testCalculate_CorrectValue() {
        VacationCalculatorService calculatorService = new VacationCalculatorService();
        Double averageSalary = 50000.0;
        Integer vacationDays = 10;
        VacationPaymentCalculatorResponse response = calculatorService.calculate(averageSalary, vacationDays);
        assertNotNull(response);
        assertEquals("14032,26", response.getCalculatedVacationSalary());
    }

    @Test
    public void testCalculateWithHolidays_CorrectValue() {
        VacationCalculatorService calculatorService = new VacationCalculatorService();
        Double averageSalary = 50000.0;
        LocalDate startVacationDate = LocalDate.of(2024, 3, 1);
        LocalDate endVacationDate = LocalDate.of(2024, 3, 10);
        VacationPaymentCalculatorResponse response = calculatorService.calculateWithHolidays(averageSalary, startVacationDate, endVacationDate);
        assertNotNull(response);
        assertEquals("10875", response.getCalculatedVacationSalary());
    }
}