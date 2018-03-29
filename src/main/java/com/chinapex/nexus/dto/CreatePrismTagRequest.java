package com.chinapex.nexus.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/** created by pengmingguo on 2/16/18 */
@Setter
@Getter
public class CreatePrismTagRequest {

  private static Map<String, Object> dummyMap = Collections.singletonMap("dummy", "dummy");

  private String name;

  private String event_type;

  // default to ""
  private String event_name = "";

  private Collection<String> rules;

  // default to ...
  private Map<String, Object> attributes = dummyMap;

  private Map<String, Object> ids = dummyMap;
}
