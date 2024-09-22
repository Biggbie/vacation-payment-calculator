package ru.plevda.vacation.payment.calculator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.plevda.vacation.payment.calculator.controller.VacationCalculatorController;
import ru.plevda.vacation.payment.calculator.model.VacationPaymentCalculatorResponse;
import ru.plevda.vacation.payment.calculator.service.VacationCalculatorService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VacationCalculatorControllerTest {
    @Autowired
    private VacationCalculatorController controller;


    @Test
    public void testCalculateVacationSalary_WithAllParameters_ReturnsBadRequest() {
        VacationCalculatorController controller = new VacationCalculatorController();
        Double averageSalary = 50000.0;
        Integer vacationDays = 10;
        LocalDate startVacationDate = LocalDate.of(2024, 3, 1);
        LocalDate endVacationDate = LocalDate.of(2024, 3, 10);
        ResponseEntity<VacationPaymentCalculatorResponse> responseEntity = controller.calculateVacationSalary(averageSalary, vacationDays, startVacationDate, endVacationDate);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCalculateVacationSalary_WithOnlyAverageSalary_ReturnsBadRequest() {
        VacationCalculatorController controller = new VacationCalculatorController();
        Double averageSalary = 50000.0;
        ResponseEntity<VacationPaymentCalculatorResponse> responseEntity = controller.calculateVacationSalary(averageSalary, null, null, null);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCalculateVacationSalary_WithVacationDaysAndStartVacationDate_ReturnsBadRequest() {
        VacationCalculatorController controller = new VacationCalculatorController();
        Integer vacationDays = 10;
        LocalDate startVacationDate = LocalDate.of(2024, 3, 1);
        ResponseEntity<VacationPaymentCalculatorResponse> responseEntity = controller.calculateVacationSalary(null, vacationDays, startVacationDate, null);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCalculateVacationSalary_WithVacationDays_ReturnsValidResponse() {
        VacationCalculatorController controller = new VacationCalculatorController();
        controller.setVacationCalculatorService(new VacationCalculatorService());
        Double averageSalary = 50000.0;
        Integer vacationDays = 10;
        ResponseEntity<VacationPaymentCalculatorResponse> responseEntity = controller.calculateVacationSalary(averageSalary, vacationDays, null, null);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getCalculatedVacationSalary());
        assertEquals("14032,26", responseEntity.getBody().getCalculatedVacationSalary());
    }

    @Test
    public void testCalculateVacationSalary_WithStartAndEndVacationDate_ReturnsValidResponse() {
        VacationCalculatorController controller = new VacationCalculatorController();
        controller.setVacationCalculatorService(new VacationCalculatorService());
        Double averageSalary = 50000.0;
        LocalDate startVacationDate = LocalDate.of(2024, 3, 1);
        LocalDate endVacationDate = LocalDate.of(2024, 3, 10);
        ResponseEntity<VacationPaymentCalculatorResponse> responseEntity = controller.calculateVacationSalary(averageSalary, null, startVacationDate, endVacationDate);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getCalculatedVacationSalary());
        assertEquals("10875", responseEntity.getBody().getCalculatedVacationSalary());
    }
}