package com.altarix.artifacttest2.models.domains;

import java.sql.Date;

public class Employee implements Domain {
    private long idEmployee;
    private long idDepartment;
    private long idPosition;
    private long idCompany;
    private String surname;
    private String name;
    private String patronymic;
    private char sex;
    private Date dateBirth;
    private String phoneNumber;
    private String email;
    private Date employmentDate;
    private Date dateOfDismissal;
    private int salary;
    private boolean isChief;

    public Employee(){}

    private Employee(EmployeeBuilder empBuilder) {
        this.idEmployee = empBuilder.idEmployee;
        this.idDepartment = empBuilder.idDepartment;
        this.idPosition = empBuilder.idPosition;
        this.idCompany = empBuilder.idCompany;
        this.surname = empBuilder.surname;
        this.name = empBuilder.name;
        this.patronymic = empBuilder.patronymic;
        this.sex = empBuilder.sex;
        this.dateBirth = empBuilder.dateBirth;
        this.phoneNumber = empBuilder.phoneNumber;
        this.email = empBuilder.email;
        this.employmentDate = empBuilder.employmentDate;
        this.dateOfDismissal = empBuilder.dateOfDismissal;
        this.salary = empBuilder.salary;
        this.isChief = empBuilder.isChief;
    }

    public long getIdEmployee() {
        return idEmployee;
    }

    public long getIdDepartment() {
        return idDepartment;
    }

    public long getIdPosition() {
        return idPosition;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public char getSex() {
        return sex;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public Date getDateOfDismissal() {
        return dateOfDismissal;
    }

    public int getSalary() {
        return salary;
    }

    public boolean isChief() {
        return isChief;
    }

    static class EmployeeBuilder{
        private long idEmployee;
        private long idDepartment;
        private long idPosition;
        private long idCompany;
        private String surname;
        private String name;
        private String patronymic;
        private char sex;
        private Date dateBirth;
        private String phoneNumber;
        private String email;
        private Date employmentDate;
        private Date dateOfDismissal;
        private int salary;
        private boolean isChief;

        public Employee build(){
            return new Employee(this);
        }

        public EmployeeBuilder setIdEmployee(long idEmployee) {
            this.idEmployee = idEmployee;
            return this;
        }

        public EmployeeBuilder setIdDepartment(long idDepartment) {
            this.idDepartment = idDepartment;
            return this;
        }

        public EmployeeBuilder setIdPosition(long idPosition) {
            this.idPosition = idPosition;
            return this;
        }

        public EmployeeBuilder setIdCompany(long idCompany) {
            this.idCompany = idCompany;
            return this;
        }

        public EmployeeBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public EmployeeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder setPatronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public EmployeeBuilder setSex(char sex) {
            this.sex = sex;
            return this;
        }

        public EmployeeBuilder setDateBirth(Date dateBirth) {
            this.dateBirth = dateBirth;
            return this;
        }

        public EmployeeBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public EmployeeBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public EmployeeBuilder setEmploymentDate(Date employmentDate) {
            this.employmentDate = employmentDate;
            return this;
        }

        public EmployeeBuilder setDateOfDismissal(Date dateOfDismissal) {
            this.dateOfDismissal = dateOfDismissal;
            return this;
        }

        public EmployeeBuilder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder setChief(boolean chief) {
            isChief = chief;
            return this;
        }
    }
}
