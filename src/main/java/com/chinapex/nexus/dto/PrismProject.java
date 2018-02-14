package com.chinapex.nexus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/** created by pengmingguo on 2/13/18 */
@Getter
@Setter
public class PrismProject {
  private Integer pk;
  private String name;
  private Collection<PrismBaseDto> pixels;
  private Collection<PrismBaseDto> containers;
  private Collection<PrismBaseDto> apis;
}
