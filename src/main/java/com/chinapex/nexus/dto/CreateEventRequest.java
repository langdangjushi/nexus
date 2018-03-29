package com.chinapex.nexus.dto;

import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * created by pengmingguo on 2/11/18
 */
@Getter
@Setter
public class CreateEventRequest {
  @Pattern(regexp = "^实时事件|非实时事件$")
  private String eventType;

  private PrismProjectResponse project;
}
