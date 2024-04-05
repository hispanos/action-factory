package com.betek.demoday.actionfactory.services;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        if (employee == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Employee cannot be null");
        }
        if (employee.getRole().getId() != 1 && employee.getRole().getId() != 2) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Employee role id must be 1 or 2");
        }
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Employee with this email already exists");
        }
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        if (employee == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Employee cannot be null");
        }
        if (employee.getRole().getId() != 1 && employee.getRole().getId() != 2) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Employee role id must be 1 or 2");
        }
        if (!employeeRepository.existsById(employee.getId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Employee with this id does not exist");
        }
        return employeeRepository.save(employee);
    }


}
