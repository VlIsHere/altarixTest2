package com.altarix.artifacttest2.mappers;

import com.altarix.artifacttest2.models.domains.Domain;

import java.util.ArrayList;

public interface DAOMapper {

    ArrayList<Domain> getAll();

    Domain getByID(long id);

    boolean insert(Domain domain);

    boolean delete(long id);

    boolean deleteAll();
}
