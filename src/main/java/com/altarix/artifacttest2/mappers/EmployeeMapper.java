package com.altarix.artifacttest2.mappers;

import com.altarix.artifacttest2.models.domains.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper extends Mapper {
    boolean insert(@Param("employee") Employee employee);

    boolean update(@Param("idOld") long id, @Param("employee") Employee employee);

}
