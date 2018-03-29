package com.chinapex.nexus.dto;

import lombok.Getter;
import lombok.Setter;

/** created by pengmingguo on 2/11/18 */
@Getter
@Setter
public class EventGroupDto {
  private Integer id;

  // groupName 和 name 是一样的，因为和前端接口的字段以前有多个，暂时不改动，先兼容
  private String groupName;
  private String name;
}
