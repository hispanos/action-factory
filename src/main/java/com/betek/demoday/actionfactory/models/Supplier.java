package com.betek.demoday.actionfactory.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    private Long id;
    private String name;
    private String address;
    private String telephoneNumber;
    private String email;
    private String webSite;
    private String sectorIndustry;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Device> devices;

    public Supplier(Long idSupplier, String name, String address, String telephoneNumber, String email, String webSite, String sectorIndustry, LocalDate registrationDate) {
        this.id = idSupplier;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.webSite = webSite;
        this.sectorIndustry = sectorIndustry;
        this.registrationDate = registrationDate;
    }

    public Long getIdSupplier() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getSectorIndustry() {
        return sectorIndustry;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setIdSupplier(Long idSupplier) {
        this.id = idSupplier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public void setSectorIndustry(String sectorIndustry) {
        this.sectorIndustry = sectorIndustry;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
