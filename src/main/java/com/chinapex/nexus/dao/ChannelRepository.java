package com.chinapex.nexus.dao;

import com.chinapex.nexus.model.Channel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * created by pengmingguo on 2/10/18
 */
@Repository
public interface ChannelRepository extends CrudRepository<Channel,Integer>{

}
