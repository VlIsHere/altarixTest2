package com.altarix.artifacttest2.dao;


import com.altarix.artifacttest2.pojo.Company;
import org.apache.ibatis.annotations.Param;

public interface CompanyDAO extends DAO {

    boolean insert(@Param("company")Company company);

    boolean update(@Param("idOld") long id, @Param("companyNew") Company companyNew);

}
