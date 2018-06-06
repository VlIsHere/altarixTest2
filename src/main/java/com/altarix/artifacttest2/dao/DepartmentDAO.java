package com.altarix.artifacttest2.dao;

import com.altarix.artifacttest2.pojo.Department;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface DepartmentDAO extends DAO {

    boolean insert(@Param("department")Department department);

    boolean update(@Param("idOld") long id, @Param("departmentNew") Department department);

    boolean updateParentDep(@Param("idDepChild") Long idDepChild,@Param("idParent") Long idParent);

    ArrayList<Department> getChilds(@Param("idRoot") long idRoot);

    ArrayList<Long> getAllIds();

    Department getByName(@Param("nameDep") String nameDep);

    long getCntDeps();
}
