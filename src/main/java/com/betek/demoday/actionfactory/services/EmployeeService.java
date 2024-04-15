package com.betek.demoday.actionfactory.services;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('administrator')")
    public Employee saveEmployee(Employee employee) {
        if (employee == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El empleado no puede ser nulo");
        }
        if (employee.getRole().getId() != 1 && employee.getRole().getId() != 2) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El id del rol del empleado debe ser 1 o 2");
        }
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El email ya está registrado");
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @PreAuthorize("hasRole('administrator')")
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

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


}
