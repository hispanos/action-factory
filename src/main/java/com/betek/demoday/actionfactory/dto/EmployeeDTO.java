package com.betek.demoday.actionfactory.dto;

import com.betek.demoday.actionfactory.utils.StatusEmployee;
import jakarta.persistence.*;

import java.time.LocalDate;

public class EmployeeDTO {
    private String name;
    private String email;
    private String password;
    private Long roleId;
    private LocalDate hiringDate;
    @Enumerated(EnumType.STRING)
    private StatusEmployee state;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String email, String password, Long roleId, LocalDate hiringDate, StatusEmployee state) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.hiringDate = hiringDate;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public StatusEmployee getState() {
        return state;
    }

    public void setState(StatusEmployee state) {
        this.state = state;
    }

}
