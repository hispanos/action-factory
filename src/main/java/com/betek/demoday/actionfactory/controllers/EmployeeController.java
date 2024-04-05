package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.services.EmployeeService;
import com.betek.demoday.actionfactory.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public CustomResponse<Employee> saveEmployee(@RequestBody Employee employee) {
        try {
            return CustomResponse.success(employeeService.saveEmployee(employee));
        } catch (ApiException e) {
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @PutMapping("/{idEmployee}")
    public CustomResponse<Employee> updateEmployee(@PathVariable("idEmployee") Long idEmployee, @RequestBody Employee employee) {
        try {
            employee.setId(idEmployee);
            return CustomResponse.success(employeeService.updateEmployee(employee));
        } catch (ApiException e) {
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @GetMapping("/")
    public CustomResponse<List<Employee>> getAllEmployees() {
        return CustomResponse.success(employeeService.getAllEmployees());
    }
}
