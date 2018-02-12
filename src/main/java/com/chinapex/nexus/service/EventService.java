package com.chinapex.nexus.service;

import com.chinapex.nexus.model.Event;
import com.chinapex.nexus.model.EventGroup;

import java.util.Collection;

/**
 * created by pengmingguo on 2/10/18
 */
public interface EventService {

    Collection<Event> getEvents(String orgName);

    Event createEvent();

    Collection<EventGroup> getGroups(String orgName);


}
