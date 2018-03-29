package com.chinapex.nexus.dto;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

/**
 * created by pengmingguo on 2/11/18
 */
@Getter
@Setter
public class EventAssociationResponse {

    private Integer id;

    private String groupName;

    private Collection<EventDto> events;

}
