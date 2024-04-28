package com.betek.demoday.actionfactory.models.validations;

import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.models.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
public class InvalidDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int validationID;
    private Long imei;
    private String state;

    private String supplier;
    private int score;
    private Date loadingDate;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private long validatorID;

    public InvalidDevice(Long imei, String state, String supplier, int score, Date loadingDate, Employee employee, long validatorID) {
        this.imei = imei;
        this.state = state;
        this.supplier = supplier;
        this.score = score;
        this.loadingDate = loadingDate;
        this.employee = employee;
        this.validatorID = validatorID;
    }

    public InvalidDevice() {
    }

    public int getValidationID() {
        return validationID;
    }

    public Long getImei() {
        return imei;
    }

    public String getState() {
        return state;
    }

    public String getSupplier() {
        return supplier;
    }

    public int getScore() {
        return score;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public long getValidatorID() {
        return validatorID;
    }

    public void setValidationID(int validationID) {
        this.validationID = validationID;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setValidatorID(long validatorID) {
        this.validatorID = validatorID;
    }
}


