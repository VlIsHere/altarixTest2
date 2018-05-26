package com.altarix.artifacttest2.models.pojo;

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

//    private Employee(EmployeeBuilder empBuilder) {
//        this.idEmployee = empBuilder.idEmployee;
//        this.idDepartment = empBuilder.idDepartment;
//        this.idPosition = empBuilder.idPosition;
//        this.idCompany = empBuilder.idCompany;
//        this.surname = empBuilder.surname;
//        this.name = empBuilder.name;
//        this.patronymic = empBuilder.patronymic;
//        this.sex = empBuilder.sex;
//        this.dateBirth = empBuilder.dateBirth;
//        this.phoneNumber = empBuilder.phoneNumber;
//        this.email = empBuilder.email;
//        this.employmentDate = empBuilder.employmentDate;
//        this.dateOfDismissal = empBuilder.dateOfDismissal;
//        this.salary = empBuilder.salary;
//        this.isChief = empBuilder.isChief;
//    }

    @Override
    public long getId() {
        return idEmployee;
    }

    public long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public long getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(long idPosition) {
        this.idPosition = idPosition;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Date getDateOfDismissal() {
        return dateOfDismissal;
    }

    public void setDateOfDismissal(Date dateOfDismissal) {
        this.dateOfDismissal = dateOfDismissal;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isChief() {
        return isChief;
    }

    public void setChief(boolean chief) {
        isChief = chief;
    }

    public void setIdEmployee(long idEmployee) {
        this.idEmployee = idEmployee;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", idDepartment=" + idDepartment +
                ", idPosition=" + idPosition +
                ", idCompany=" + idCompany +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", sex=" + sex +
                ", dateBirth=" + dateBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", employmentDate=" + employmentDate +
                ", dateOfDismissal=" + dateOfDismissal +
                ", salary=" + salary +
                ", isChief=" + isChief +
                '}';
    }

    //    static class EmployeeBuilder{
//        private long idEmployee;
//        private long idDepartment;
//        private long idPosition;
//        private long idCompany;
//        private String surname;
//        private String name;
//        private String patronymic;
//        private char sex;
//        private Date dateBirth;
//        private String phoneNumber;
//        private String email;
//        private Date employmentDate;
//        private Date dateOfDismissal;
//        private int salary;
//        private boolean isChief;
//
//        public Employee build(){
//            return new Employee(this);
//        }
//
//        public EmployeeBuilder setIdEmployee(long idEmployee) {
//            this.idEmployee = idEmployee;
//            return this;
//        }
//
//        public EmployeeBuilder setIdDepartment(long idDepartment) {
//            this.idDepartment = idDepartment;
//            return this;
//        }
//
//        public EmployeeBuilder setIdPosition(long idPosition) {
//            this.idPosition = idPosition;
//            return this;
//        }
//
//        public EmployeeBuilder setIdCompany(long idCompany) {
//            this.idCompany = idCompany;
//            return this;
//        }
//
//        public EmployeeBuilder setSurname(String surname) {
//            this.surname = surname;
//            return this;
//        }
//
//        public EmployeeBuilder setName(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public EmployeeBuilder setPatronymic(String patronymic) {
//            this.patronymic = patronymic;
//            return this;
//        }
//
//        public EmployeeBuilder setSex(char sex) {
//            this.sex = sex;
//            return this;
//        }
//
//        public EmployeeBuilder setDateBirth(Date dateBirth) {
//            this.dateBirth = dateBirth;
//            return this;
//        }
//
//        public EmployeeBuilder setPhoneNumber(String phoneNumber) {
//            this.phoneNumber = phoneNumber;
//            return this;
//        }
//
//        public EmployeeBuilder setEmail(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public EmployeeBuilder setEmploymentDate(Date employmentDate) {
//            this.employmentDate = employmentDate;
//            return this;
//        }
//
//        public EmployeeBuilder setDateOfDismissal(Date dateOfDismissal) {
//            this.dateOfDismissal = dateOfDismissal;
//            return this;
//        }
//
//        public EmployeeBuilder setSalary(int salary) {
//            this.salary = salary;
//            return this;
//        }
//
//        public EmployeeBuilder setChief(boolean chief) {
//            isChief = chief;
//            return this;
//        }
//    }
}
