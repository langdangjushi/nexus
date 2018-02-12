package com.chinapex.nexus.controller;

import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.dto.EventAssociationResponse;
import com.chinapex.nexus.dto.EventDto;
import com.chinapex.nexus.dto.LoginToken;
import com.chinapex.nexus.model.EventGroup;
import com.chinapex.nexus.model.Organization;
import com.chinapex.nexus.util.Msg;
import com.chinapex.nexus.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by pengmingguo on 2/10/18
 */
@RestController
@RequestMapping("/api/v1/")
public class EventController {

    @Autowired
    private OrganizationRepository orgRepo;

    @RequestMapping("eventView/alldata")
    public Msg getEventView() {

        return null;
    }

    @GetMapping("eventmanage/group/association")
    public Msg association() {
        LoginToken token = TokenUtil.getToken();
        Organization org = orgRepo.findByName(token.getOrgName());
        Collection<EventGroup> eventGroups = org.getEventGroups();
        List<EventAssociationResponse> result = eventGroups.stream().map(g -> {
            EventAssociationResponse response = new EventAssociationResponse();
            response.setId(g.getId());
            response.setGroupName(g.getName());
            List<EventDto> eventDtos = g.getEvents().stream().map(e -> {
                EventDto eventDto = new EventDto();
                eventDto.setId(e.getId());
                eventDto.setEventName(e.getName());
                eventDto.setEventType(e.getType());
                eventDto.setSelected(e.getSelected());
                return eventDto;
            }).collect(Collectors.toList());
            response.setEventDtos(eventDtos);
            return response;
        }).collect(Collectors.toList());
        return Msg.ok().data(result);
    }

//    @GetMapping("eventmanage/groupandevent")
//    public
}
