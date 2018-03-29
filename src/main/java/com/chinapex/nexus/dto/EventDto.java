package com.chinapex.nexus.dto;

import java.util.Collection;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/** created by pengmingguo on 2/11/18 */
@Getter
@Setter
public class EventDto {
  private Integer id;
  private String eventName;
  private String eventType;

  private Date createdTime;

  private Collection<String> groups;

  // 用户上次是否在网页里选中
  private Boolean selected;
}
