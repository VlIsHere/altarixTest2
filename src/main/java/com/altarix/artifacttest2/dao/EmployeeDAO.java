package com.altarix.artifacttest2.dao;

import com.altarix.artifacttest2.models.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface EmployeeDAO extends DAO {
    boolean insert(@Param("employee") Employee employee);

    boolean update(@Param("idOld") long id, @Param("employee") Employee employee);

    ArrayList<Employee> getByIdDeprtmnt(@Param("idDep") long id);

    Employee getBossOfDep(@Param("idDep") long id);

    Integer getCntEmplByDep(@Param("idDep") long id);

    long getFondMoney(@Param("idDep") long id);

    Integer getMaxSalary(@Param("idDep") long id);

    ArrayList<Employee> getMans(@Param(value = "cSex") char cSex);
}
