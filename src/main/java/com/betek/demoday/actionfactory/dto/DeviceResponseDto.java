package com.betek.demoday.actionfactory.dto;

import com.betek.demoday.actionfactory.models.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Date;

public class DeviceResponseDto {
    private int validationID;
    private Long imei;
    private Boolean isValid;
    private String state;

    private String supplier;
    private int score;
    private Date loadingDate;
    private Employee employee;
    private long validatorID;

    public DeviceResponseDto() {
    }

    public DeviceResponseDto(int validationID, Boolean isValid, Long imei, String state, String supplier, int score, Date loadingDate, Employee employee, long validatorID) {
        this.validationID = validationID;
        this.imei = imei;
        this.state = state;
        this.supplier = supplier;
        this.score = score;
        this.loadingDate = loadingDate;
        this.employee = employee;
        this.validatorID = validatorID;
        this.isValid = isValid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public int getValidationID() {
        return validationID;
    }

    public void setValidationID(int validationID) {
        this.validationID = validationID;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
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
