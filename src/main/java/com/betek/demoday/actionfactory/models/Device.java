package com.betek.demoday.actionfactory.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class Device {
    @Id
    private Long imei;
    private String state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private int score;
    private LocalDate validationDate;

    public Device(Long imei, String state, Supplier supplier, int score, LocalDate validationDate) {
        this.imei = imei;
        this.state = state;
        this.supplier = supplier;
        this.score = score;
        this.validationDate = validationDate;
    }

    public Long getImei() {
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

    public LocalDate getValidationDate() {
        return validationDate;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setValidationDate(LocalDate validationDate) {
        this.validationDate = validationDate;
    }
}
