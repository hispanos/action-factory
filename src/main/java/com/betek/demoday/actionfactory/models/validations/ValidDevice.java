package com.betek.demoday.actionfactory.models.validations;

import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.models.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
public class ValidDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int validationID;
    private String imei;
    private String state;

    private String supplier;
    private int score;
    private Date loadingDate;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private long validatorID;

    public ValidDevice(int validationID, String imei, String state, String supplier, int score, Date loadingDate, Employee employee, long validatorID) {
        this.validationID = validationID;
        this.imei = imei;
        this.state = state;
        this.supplier = supplier;
        this.score = score;
        this.loadingDate = loadingDate;
        this.employee = employee;
        this.validatorID = validatorID;
    }

    public ValidDevice() {
    }

    public int getValidationID() {
        return validationID;
    }

    public void setValidationID(int validationID) {
        this.validationID = validationID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getValidatorID() {
        return validatorID;
    }

    public void setValidatorID(long validatorID) {
        this.validatorID = validatorID;
    }
}
