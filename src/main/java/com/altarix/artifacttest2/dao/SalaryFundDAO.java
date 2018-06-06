package com.altarix.artifacttest2.dao;

import com.altarix.artifacttest2.pojo.SalaryFund;
import org.apache.ibatis.annotations.Param;

public interface SalaryFundDAO extends DAO {

    boolean insert(@Param("salaryFund")SalaryFund salaryFund);

    boolean update(@Param("idOld") long idOld,@Param("salaryFund")SalaryFund salaryFund);
}
