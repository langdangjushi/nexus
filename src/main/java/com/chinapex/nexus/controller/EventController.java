package com.chinapex.nexus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chinapex.nexus.dao.EventRepository;
import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.dto.CreateEventRequest;
import com.chinapex.nexus.dto.CreatePrismTagRequest;
import com.chinapex.nexus.dto.EventAndGroupDto;
import com.chinapex.nexus.dto.EventAndGroupDto.Relation;
import com.chinapex.nexus.dto.EventAssociationResponse;
import com.chinapex.nexus.dto.EventDto;
import com.chinapex.nexus.dto.EventGroupDto;
import com.chinapex.nexus.dto.LoginToken;
import com.chinapex.nexus.dto.PrismContainer;
import com.chinapex.nexus.dto.PrismTrigger;
import com.chinapex.nexus.exception.CallPrismException;
import com.chinapex.nexus.model.Event;
import com.chinapex.nexus.model.EventGroup;
import com.chinapex.nexus.model.Organization;
import com.chinapex.nexus.service.PrismHttpService;
import com.chinapex.nexus.util.Msg;
import com.chinapex.nexus.util.TokenUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import okhttp3.ResponseBody;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import retrofit2.Response;

/** created by pengmingguo on 2/10/18 */
@RestController
@RequestMapping("/api/v1/")
public class EventController {
  private static Logger logger = LoggerFactory.getLogger(EventController.class);

  @Autowired private OrganizationRepository orgRepo;

  @Autowired private EventRepository eventRepo;

  @Autowired private PrismHttpService prismHttpService;

  @RequestMapping("eventView/alldata")
  public Msg getEventView() {
    return null;
  }

  @GetMapping("eventmanage/group/association")
  public Msg association() {
    LoginToken token = TokenUtil.getToken();
    Organization org = orgRepo.findByName(token.getOrgName());
    Collection<EventGroup> eventGroups = org.getEventGroups();
    HashSet<Integer> groupedEventIds = new HashSet<>();
    List<EventAssociationResponse> result = new LinkedList<>();
    if (CollectionUtils.isNotEmpty(eventGroups)) {
      result =
          eventGroups
              .stream()
              .map(
                  g -> {
                    EventAssociationResponse response = new EventAssociationResponse();
                    response.setId(g.getId());
                    response.setGroupName(g.getName());
                    List<EventDto> eventDtos = new LinkedList<>();
                    if (CollectionUtils.isNotEmpty(g.getEvents())) {
                      g.getEvents()
                          .forEach(
                              e -> {
                                groupedEventIds.add(e.getId());
                                EventDto eventDto = new EventDto();
                                eventDto.setId(e.getId());
                                eventDto.setEventName(e.getName());
                                eventDto.setEventType(e.getType());
                                eventDto.setSelected(e.getSelected());
                                eventDtos.add(eventDto);
                              });
                    }
                    response.setEvents(eventDtos);
                    return response;
                  })
              .collect(Collectors.toList());
    }

    // 未分组的 事件
    List<EventDto> nogroupEvent = new LinkedList<>();
    if (CollectionUtils.isNotEmpty(org.getEvents())) {
      nogroupEvent =
          org.getEvents()
              .stream()
              .filter(e -> !groupedEventIds.contains(e.getId()))
              .map(
                  e -> {
                    EventDto eventDto = new EventDto();
                    eventDto.setId(e.getId());
                    eventDto.setEventName(e.getName());
                    eventDto.setEventType(e.getType());
                    eventDto.setSelected(e.getSelected());
                    return eventDto;
                  })
              .collect(Collectors.toList());
    }
    EventAssociationResponse nogroup = new EventAssociationResponse();
    nogroup.setId(0);
    nogroup.setGroupName("未分组");
    nogroup.setEvents(nogroupEvent);
    result.add(nogroup);
    return Msg.ok().data(result);
  }

  @GetMapping("eventmanage/groupandevent")
  public Msg groupAndEvent() {
    LoginToken token = TokenUtil.getToken();
    Organization org = orgRepo.findByName(token.getOrgName());
    Collection<EventGroup> eventGroups = org.getEventGroups();
    Collection<Event> events = org.getEvents();
    LinkedList<Relation> relations = new LinkedList<>();
    List<EventDto> eventDtos = new LinkedList<>();
    if (CollectionUtils.isNotEmpty(events)) {
      eventDtos =
          events
              .stream()
              .map(
                  e -> {
                    EventDto dto = new EventDto();
                    dto.setId(e.getId());
                    dto.setEventName(e.getName());
                    dto.setEventType(e.getType());
                    dto.setCreatedTime(e.getCreatedTime());
                    List<String> groupNames = new LinkedList<>();
                    if (CollectionUtils.isNotEmpty(e.getGroups())) {
                      groupNames =
                          e.getGroups()
                              .stream()
                              .map(
                                  o -> {
                                    Relation relation = new Relation();
                                    relation.setEventId(e.getId());
                                    relation.setGroupId(o.getId());
                                    relations.add(relation);
                                    return e.getName();
                                  })
                              .collect(Collectors.toList());
                    }
                    dto.setGroups(groupNames);
                    return dto;
                  })
              .collect(Collectors.toList());
    }
    List<EventGroupDto> eventGroupDtos = new LinkedList<>();
    if (CollectionUtils.isNotEmpty(eventGroups)) {
      eventGroupDtos =
          eventGroups
              .stream()
              .map(
                  e -> {
                    EventGroupDto eventGroupDto = new EventGroupDto();
                    eventGroupDto.setId(e.getId());
                    eventGroupDto.setName(e.getName());
                    return eventGroupDto;
                  })
              .collect(Collectors.toList());
    }
    EventAndGroupDto result = new EventAndGroupDto();
    result.setEvents(eventDtos);
    result.setGroups(eventGroupDtos);
    result.setRelations(relations);
    return Msg.ok().data(result);
  }

  @PostMapping("eventmanage/event/create")
  public Msg createEvent(@RequestBody CreateEventRequest request) {
    LoginToken token = TokenUtil.getToken();
    logger.info(
        "create user={} event {}",
        token.getUserName(),
        JSON.toJSONString(request, SerializerFeature.PrettyFormat));

    Organization org = orgRepo.findByName(token.getOrgName());
    //    if(request.getEventType().equals(Event.REALTIME))

    return null;
  }

  private void saveRealtimeEvent(CreateEventRequest request, Organization org)
      throws CallPrismException {
    Integer projectId = request.getProject().getProjectId();
    for (PrismContainer c : request.getProject().getContainers()) {
      List<String> ruleIds =
          c.getTriggers()
              .stream()
              .map(t -> t.getTriggerId().toString())
              .collect(Collectors.toList());
      CreatePrismTagRequest tagRequest = new CreatePrismTagRequest();
      tagRequest.setEvent_name(
          c.getContainerId() + DigestUtils.md5Hex(ruleIds.toString()).substring(0, 10));
      tagRequest.setEvent_type(Event.REALTIME);
      tagRequest.setRules(ruleIds);
      Response<ResponseBody> prismResponse = null;
      String prismResponseBody = null;
      try {
        prismResponse =
            prismHttpService
                .createTag(org.getPrismToken(), projectId, c.getContainerId(), tagRequest)
                .execute();
        prismResponseBody = prismResponse.body().string();
      } catch (IOException e) {
        // ignore it
      }
      if (prismResponse == null || prismResponse.code() >= 400)
        throw new CallPrismException("call prism error!" + prismResponseBody);
      // call prism no error
      String tagNumber = JSON.parseObject(prismResponseBody).getString("tag_number");
      for (PrismTrigger trigger : c.getTriggers()) {
        String eventName =
            trigger.getTriggerName()
                + " "
                + request.getProject().getProjectName()
                + "/"
                + c.getContainerName();
        boolean eventExists = eventRepo.existsByNameAndOrganization(eventName, org);
        if (eventExists) { // 这个事件已经存在，打印日志

          continue;
        }
        Event event = new Event();
        event.setName(eventName);
        event.setType(Event.REALTIME);
        event.setOrganization(org);
        eventRepo.save(event);
      }
    }
  }
}
