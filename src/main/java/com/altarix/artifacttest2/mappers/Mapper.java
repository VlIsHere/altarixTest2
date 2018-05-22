package com.altarix.artifacttest2.mappers;

import com.altarix.artifacttest2.models.domains.Domain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@org.apache.ibatis.annotations.Mapper
public interface Mapper {

   // @Select("select * from company")
    List getAll();

    Domain getByID(@Param("id") long id);

    boolean delete(@Param("id") long id);

    boolean deleteAll();
}
