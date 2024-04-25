package com.betek.demoday.actionfactory.models.responses;

import com.betek.demoday.actionfactory.models.Employee;

public class LoginResponse {
    private String token;
    private Employee user;

    public LoginResponse() {
    }

    public LoginResponse(String token, Employee employee) {
        this.token = token;
        this.user = employee;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee employee) {
        this.user = employee;
    }
}
