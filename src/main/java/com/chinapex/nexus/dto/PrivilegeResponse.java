package com.chinapex.nexus.dto;

import lombok.Getter;
import lombok.Setter;

/** created by pengmingguo on 4/2/18 */
@Getter
@Setter
public class PrivilegeResponse {

  private String name;
  private Integer resourceId;
  private Integer action;
}
