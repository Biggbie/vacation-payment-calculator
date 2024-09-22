package ru.plevda.vacation.payment.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationPaymentCalculatorResponse {
    private String outputMessage;
    private String calculatedVacationSalary;
}
