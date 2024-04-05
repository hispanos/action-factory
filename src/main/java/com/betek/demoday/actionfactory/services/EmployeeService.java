package com.betek.demoday.actionfactory.services;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        if (employee == null) {
            throw new ApiException(400, "Employee cannot be null");
        }
        if (employee.getRole().getId() != 1 && employee.getRole().getId() != 2) {
            throw new ApiException(400, "Employee role id must be 1 or 2");
        }
        return employeeRepository.save(employee);
    }


}
