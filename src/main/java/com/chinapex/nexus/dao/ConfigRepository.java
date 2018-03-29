package com.chinapex.nexus.dao;

import com.chinapex.nexus.model.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * created by pengmingguo on 2/28/18
 */
@Repository
public interface ConfigRepository extends CrudRepository<Config,Integer>{

}
