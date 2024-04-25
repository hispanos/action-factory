package com.betek.demoday.actionfactory.services;

import com.betek.demoday.actionfactory.dto.EmployeeDTO;
import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.models.Role;
import com.betek.demoday.actionfactory.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    BCryptPasswordEncoder passwordEncoder;
    RoleService roleService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = getEmployeeFromDTO(employeeDTO);
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

    public Employee updateEmployee(Long idEmployee, EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Employee cannot be null");
        }
        Employee employee = getEmployeeFromDTO(employeeDTO);
        employee.setId(idEmployee);
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

    //Get Employee from EmployeeDTO
    public Employee getEmployeeFromDTO(EmployeeDTO employeeDTO) {
        Role role = roleService.getRoleById(employeeDTO.getRoleId());
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setRole(role);
        employee.setHiringDate(employeeDTO.getHiringDate());
        employee.setState(employeeDTO.getState());
        return employee;
    }
}
