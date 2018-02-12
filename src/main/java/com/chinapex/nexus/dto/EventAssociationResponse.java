package com.chinapex.nexus.dto;

import com.chinapex.nexus.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * created by pengmingguo on 2/11/18
 */
@Getter
@Setter
public class EventAssociationResponse {

    private Integer id;

    private String groupName;

    private Collection<EventDto> eventDtos;

}
