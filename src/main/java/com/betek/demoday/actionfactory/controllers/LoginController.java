package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.dto.LoginDTO;
import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.responses.CustomResponse;
import com.betek.demoday.actionfactory.models.responses.LoginResponse;
import com.betek.demoday.actionfactory.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Controlador de Login", description = "Controlador para autenticar a los empleados")
@RestController
@RequestMapping("api/login")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @Operation(summary = "Autenticar a un empleado.")
    public CustomResponse<LoginResponse> login(@RequestBody LoginDTO loginRequest) {
        try {
            return CustomResponse.success(loginService.login(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (ApiException e) {
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }
}
