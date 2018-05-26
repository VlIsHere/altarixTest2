package com.altarix.artifacttest2.models.pojo;

public class Company implements Domain {
    private long idCompany;
    private String nameCompany;

    public Company(){}

    @Override
    public long getId() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }
}
