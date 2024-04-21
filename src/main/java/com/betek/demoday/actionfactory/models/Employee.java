package com.betek.demoday.actionfactory.models;

import com.betek.demoday.actionfactory.utils.StatusEmployee;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private LocalDate hiringDate;
    private LocalDateTime lastAccess;
    @Enumerated(EnumType.STRING)
    private StatusEmployee state;

    public Employee() {
    }

    public Employee(Long id, String name, String email, String password, Role role, LocalDate hiringDate, LocalDateTime lastAccess, StatusEmployee state) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.hiringDate = hiringDate;
        this.lastAccess = lastAccess;
        this.state = state;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public StatusEmployee getState() {
        return state;
    }

    public void setState(StatusEmployee state) {
        this.state = state;
    }
}
