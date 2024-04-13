package com.betek.demoday.actionfactory.models;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class ValidDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int validationID;
    private int imei;
    private String state;
    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private int score;
    private Date validationDate;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private long validatorID;

    public ValidDevice(int validationID, int imei, String state, Supplier supplier, int score, Date validationDate, Employee employee, long validatorID) {
        this.validationID = validationID;
        this.imei = imei;
        this.state = state;
        this.supplier = supplier;
        this.score = score;
        this.validationDate = validationDate;
        this.employee = employee;
        this.validatorID = validatorID;
    }

    public ValidDevice() {
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

    public Date getValidationDate() {
        return validationDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public long getValidatorID() {
        return validatorID;
    }
}
