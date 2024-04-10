package com.betek.demoday.actionfactory.models;

import com.opencsv.bean.CsvBindByName;

public class CsvModel {
    @CsvBindByName(column = "Index")
    private String index;

    @CsvBindByName(column = "User Id")
    private String userId;

    @CsvBindByName(column = "First Name")
    private String firstName;

    @CsvBindByName(column = "Last Name")
    private String lastName;

    @CsvBindByName(column = "Sex")
    private String sex;

    @CsvBindByName(column = "Email")
    private String email;

    @CsvBindByName(column = "Phone")
    private String phone;

    @CsvBindByName(column = "Date of birth")
    private String dateOfBirth;

    @CsvBindByName(column = "Job Title")
    private String jobTitle;



}
