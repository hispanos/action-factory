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
    private Long id;

    private Long imei;
    private String estado;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private int puntaje;
    private Date fechaValidacion;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Long cedula;

    // Constructor por defecto
    public ValidDevice() {
    }

    // Constructor con todos los campos
    public ValidDevice(Long imei, String estado, Supplier supplier, int puntaje, Date fechaValidacion, Employee employee, Long cedula) {
        this.imei = imei;
        this.estado = estado;
        this.supplier = supplier;
        this.puntaje = puntaje;
        this.fechaValidacion = fechaValidacion;
        this.employee = employee;
        this.cedula = cedula;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }
}
