package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.models.Supplier;
import com.betek.demoday.actionfactory.services.EmployeeService;
import com.betek.demoday.actionfactory.models.responses.CustomResponse;
import com.betek.demoday.actionfactory.dto.EmployeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public CustomResponse<Employee> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            return CustomResponse.success(employeeService.saveEmployee(employeeDTO));
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
    public CustomResponse<Employee> updateEmployee(@PathVariable("idEmployee") Long idEmployee, @RequestBody EmployeeDTO employeeDTO) {
        try {
            return CustomResponse.success(employeeService.updateEmployee(idEmployee, employeeDTO));
        } catch (ApiException e) {
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los empleados")
    public CustomResponse<List<Employee>> getAllEmployees() {
        return CustomResponse.success(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un empleado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado."),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado.", content = @Content),
    })
    public CustomResponse<Employee> getSupplierById(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return CustomResponse.success(employee);
        } catch (ApiException e) {
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor eliminado."),
            @ApiResponse(responseCode = "400", description = "Proveedor no encontrado.", content = @Content)
    })
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id){
        try {
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.noContent().build();
        }catch (ApiException e){
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

}
