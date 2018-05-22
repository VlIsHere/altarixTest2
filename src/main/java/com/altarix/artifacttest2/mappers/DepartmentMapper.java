package com.altarix.artifacttest2.mappers;

import com.altarix.artifacttest2.models.domains.Department;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper extends Mapper {

    boolean insert(@Param("department")Department department);

    boolean update(@Param("idOld") long id, @Param("departmentNew") Department department);
}
