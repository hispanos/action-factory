package com.betek.demoday.actionfactory.models.validations;

import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.models.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class ValidDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long IMEI;
    private String Estado;
    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private int Puntaje;
    private Date fechaValidacion;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee empoyee;
    private Long cedula;
}
