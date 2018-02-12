package com.chinapex.nexus.service.impl;

import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.model.Event;
import com.chinapex.nexus.model.EventGroup;
import com.chinapex.nexus.model.Organization;
import com.chinapex.nexus.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * created by pengmingguo on 2/10/18
 */
@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private OrganizationRepository orgRepo;

    @Override
    public Collection<Event> getEvents(String orgName) {
        return null;
    }

    @Override
    public Event createEvent() {
        return null;
    }

    @Override
    public Collection<EventGroup> getGroups(String orgName) {
        Organization org = orgRepo.findByName(orgName);
        return org.getEventGroups();
    }
}
