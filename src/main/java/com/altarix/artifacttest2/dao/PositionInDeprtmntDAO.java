package com.altarix.artifacttest2.dao;

import com.altarix.artifacttest2.pojo.PositionInDeprtmnt;
import org.apache.ibatis.annotations.Param;

public interface PositionInDeprtmntDAO extends DAO {
    boolean insert(@Param("posInDeprtmnt") PositionInDeprtmnt posInDeprtmnt);

    boolean update(@Param("idOld") long id, @Param("posInDeprtmnt") PositionInDeprtmnt posInDeprtmnt);
}
