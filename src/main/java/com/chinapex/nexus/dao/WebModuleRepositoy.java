package com.chinapex.nexus.dao;

import com.chinapex.nexus.model.WebModule;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * created by pengmingguo on 2/14/18
 */
@Repository
public interface WebModuleRepositoy extends PagingAndSortingRepository<WebModule,Integer> {

}
