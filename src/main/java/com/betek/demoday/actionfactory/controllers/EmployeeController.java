package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.services.EmployeeService;
import com.betek.demoday.actionfactory.models.responses.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Controller", description = "Controlador para gestionar las operaciones de los empleados")
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @Operation(summary = "Crear un empleado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado creado."),
            @ApiResponse(responseCode = "400", description = "El id del rol del empleado debe ser 1 o 2.", content = @Content),
            @ApiResponse(responseCode = "400", description = "El email ya está registrado.", content = @Content),
    })
    public CustomResponse<Employee> saveEmployee(@RequestBody Employee employee) {
        try {
            return CustomResponse.success(employeeService.saveEmployee(employee));
        } catch (ApiException e) {
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @PutMapping("/{idEmployee}")
    @Operation(summary = "Actualizar un empleado por su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado creado."),
            @ApiResponse(responseCode = "400", description = "El id del rol del empleado debe ser 1 o 2.", content = @Content),
            @ApiResponse(responseCode = "400", description = "El empleado con este id no existe.", content = @Content),
    })
    public CustomResponse<Employee> updateEmployee(@PathVariable("idEmployee") Long idEmployee, @RequestBody Employee employee) {
        try {
            employee.setId(idEmployee);
            return CustomResponse.success(employeeService.updateEmployee(employee));
        } catch (ApiException e) {
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los empleados")
    public CustomResponse<List<Employee>> getAllEmployees() {
        return CustomResponse.success(employeeService.getAllEmployees());
    }

}
