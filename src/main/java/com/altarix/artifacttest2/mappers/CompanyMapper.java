package com.altarix.artifacttest2.mappers;


import com.altarix.artifacttest2.models.domains.Company;
import org.apache.ibatis.annotations.Param;

public interface CompanyMapper extends Mapper {

    boolean insert(@Param("company")Company company);

    boolean update(@Param("idOld") long id, @Param("companyNew") Company companyNew);

}
