package ru.plevda.vacation.payment.calculator.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Vacation {
    private Double averageSalary;
    private Integer vacationDays;
    private LocalDate startVacationDate;
    private LocalDate endVacationDate;
}
