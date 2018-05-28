package com.altarix.artifacttest2.json.response;

import com.altarix.artifacttest2.pojo.Department;
import com.altarix.artifacttest2.pojo.Employee;

public class DepartmentInfo {
    private Department department;
    private Employee boss;
    private int cntEmployee;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public int getCntEmployee() {
        return cntEmployee;
    }

    public void setCntEmployee(int cntEmployee) {
        this.cntEmployee = cntEmployee;
    }
}
