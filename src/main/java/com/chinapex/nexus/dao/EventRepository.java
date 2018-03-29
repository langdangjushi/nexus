package com.chinapex.nexus.dao;

import com.chinapex.nexus.model.Event;
import com.chinapex.nexus.model.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/** created by pengmingguo on 2/22/18 */
public interface EventRepository extends CrudRepository<Event, Integer> {
  Event findByNameAndOrganization(String eventName, Organization org);

  boolean existsByNameAndOrganization(String eventName, Organization org);
}
