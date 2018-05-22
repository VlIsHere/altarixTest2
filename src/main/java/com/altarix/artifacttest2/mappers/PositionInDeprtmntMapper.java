package com.altarix.artifacttest2.mappers;

import com.altarix.artifacttest2.models.domains.PositionInDeprtmnt;
import org.apache.ibatis.annotations.Param;

public interface PositionInDeprtmntMapper extends Mapper {
    boolean insert(@Param("posInDeprtmnt") PositionInDeprtmnt posInDeprtmnt);

    boolean update(@Param("idOld") long id, @Param("posInDeprtmnt") PositionInDeprtmnt posInDeprtmnt);
}
