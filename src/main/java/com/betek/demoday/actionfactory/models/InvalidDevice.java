package com.betek.demoday.actionfactory.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class InvalidDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int validationID;
    private int imei;
    private String state;
    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private int score;
    private Date loadingDate;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private long validatorID;

    public InvalidDevice(int validationID, int imei, String state, Supplier supplier, int score, Date loadingDate, Employee employee, long validatorID) {
        this.validationID = validationID;
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

    public int getImei() {
        return imei;
    }

    public String getState() {
        return state;
    }

    public Supplier getSupplier() {
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
}
