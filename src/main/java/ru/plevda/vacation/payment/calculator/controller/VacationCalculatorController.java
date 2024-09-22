package ru.plevda.vacation.payment.calculator.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.plevda.vacation.payment.calculator.model.VacationPaymentCalculatorResponse;
import ru.plevda.vacation.payment.calculator.service.VacationCalculatorService;

import java.time.LocalDate;

@Setter
@RestController
public class VacationCalculatorController {

    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<VacationPaymentCalculatorResponse> calculateVacationSalary(@RequestParam(name = "averageSalary") Double averageSalary,
                                                                                    @RequestParam(required = false, name = "vacationDays") Integer vacationDays,
                                                                                    @RequestParam(required = false, name = "startVacationDate") LocalDate startVacationDate,
                                                                                    @RequestParam(required = false, name = "endVacationDate") LocalDate endVacationDate) {
        if (averageSalary != null && vacationDays != null && startVacationDate != null && endVacationDate != null) {
            return ResponseEntity.badRequest().build();
        }
        if (vacationDays != null && (startVacationDate != null || endVacationDate != null)) {
            return ResponseEntity.badRequest().build();
        }
        if (startVacationDate != null && endVacationDate != null) {
            return ResponseEntity.ok(vacationCalculatorService.calculateWithHolidays(averageSalary, startVacationDate, endVacationDate));
        } else {
            if (vacationDays == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(vacationCalculatorService.calculate(averageSalary, vacationDays));
        }
    }
}