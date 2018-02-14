package com.chinapex.nexus.dao;

import com.chinapex.nexus.model.DataResourceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** created by pengmingguo on 2/13/18 */
@Repository
public interface DataResourceTypeRepository extends CrudRepository<DataResourceType, Integer> {}
