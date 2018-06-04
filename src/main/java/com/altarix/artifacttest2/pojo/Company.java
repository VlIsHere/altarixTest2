package com.altarix.artifacttest2.pojo;


public class Company implements Domain {
    private long id;
    private String nameCompany;

    public Company(){}

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }
}
