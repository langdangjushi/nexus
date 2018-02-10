package com.chinapex.nexus.dao;

import com.chinapex.nexus.model.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * created by pengmingguo on 2/8/18
 */
@Repository
public interface OrganizationRepository extends CrudRepository<Organization,Integer>{

    Organization findByName(String name);

    boolean existsByName(String name);
}
