package com.altarix.artifacttest2.dao;

import com.altarix.artifacttest2.models.pojo.Domain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@org.apache.ibatis.annotations.DAO
public interface DAO {

   // @Select("select * from company")
    List getAll();

    Domain getByID(@Param("id") long id);

    boolean delete(@Param("id") long id);

    boolean deleteAll();
}
