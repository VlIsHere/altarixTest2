package com.altarix.artifacttest2.pojo;

public class SalaryFund implements Domain {
    private long idDepartment;
    private long fundOfDep;

    public SalaryFund(){}

    public SalaryFund(long idDepartment, long fundOfDep) {
        this.idDepartment = idDepartment;
        this.fundOfDep = fundOfDep;
    }


    public long getId() {
        return idDepartment;
    }

    public void setId(long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public long getFundOfDep() {
        return fundOfDep;
    }

    public void setFundOfDep(long fundOfDep) {
        this.fundOfDep = fundOfDep;
    }
}
