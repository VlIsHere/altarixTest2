package com.altarix.artifacttest2.pojo;

import java.sql.Date;
import java.util.Objects;

public class Department implements Domain {
    private long id;
    private long idCompany;
    private Long idRootDepartment;
    private Date dateOfCreating;
    private String nameDepartment;

    public Long getIdRootDepartment() {
        return idRootDepartment;
    }

    public void setIdRootDepartment(Long idRootDepartment) {
        this.idRootDepartment = idRootDepartment;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                idCompany == that.idCompany &&
                Objects.equals(idRootDepartment, that.idRootDepartment) &&
                Objects.equals(dateOfCreating, that.dateOfCreating) &&
                Objects.equals(nameDepartment, that.nameDepartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCompany, idRootDepartment, dateOfCreating, nameDepartment);
    }
}
