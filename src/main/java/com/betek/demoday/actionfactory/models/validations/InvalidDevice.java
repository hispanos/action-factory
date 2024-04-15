package com.betek.demoday.actionfactory.models.validations;

import com.betek.demoday.actionfactory.models.Employee;
import com.betek.demoday.actionfactory.models.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}


