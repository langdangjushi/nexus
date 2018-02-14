package com.chinapex.nexus.dto;

import java.util.Collection;
import lombok.Data;

/** created by pengmingguo on 2/14/18 */
@Data
public class EventAndGroupDto {

  private Collection<EventDto> events;

  private Collection<EventGroupDto> groups;

  private Collection<Relation> relations;

  @Data
  public static class Relation {
    private Integer groupId;
    private Integer eventId;
  }
}
