package com.altarix.artifacttest2.models.pojo;

import java.sql.Date;

public class Department implements Domain {
    private long idDepartment;
    private long idCompany;
    private long idRootDepartment;
    private Date dateOfCreating;
    private String nameDepartment;

    public long getIdRootDepartment() {
        return idRootDepartment;
    }

    public void setIdRootDepartment(long idRootDepartment) {
        this.idRootDepartment = idRootDepartment;
    }

    @Override
    public long getId() {
        return idDepartment;
    }

    public void setIdDepartment(long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public Date getDateOfCreating() {
        return dateOfCreating;
    }

    public void setDateOfCreating(Date dateOfCreating) {
        this.dateOfCreating = dateOfCreating;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

}
