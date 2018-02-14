package com.chinapex.nexus.dao;

import com.chinapex.nexus.model.WebModule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * created by pengmingguo on 2/14/18
 */
@Repository
public interface WebModuleRepositoy extends CrudRepository<WebModule,Integer>{

}
