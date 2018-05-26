package com.altarix.artifacttest2.dao;

import com.altarix.artifacttest2.models.pojo.Department;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface DepartmentDAO extends DAO {

    boolean insert(@Param("department")Department department);

    boolean update(@Param("idOld") long id, @Param("departmentNew") Department department);

    ArrayList<Department> getChilds(@Param("idRoot") long idRoot);
}
